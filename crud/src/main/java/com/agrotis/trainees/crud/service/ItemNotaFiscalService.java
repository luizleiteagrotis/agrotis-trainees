package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import com.agrotis.trainees.crud.convert.ItemNotaFiscalConversor;
import com.agrotis.trainees.crud.dto.ItemNotaFiscalDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ItemNotaFiscalException;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ItemNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);
    private final ItemNotaFiscalRepository repository;
    private final NotaFiscalService notaFiscalService;
    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;
    private final ControleEstoque controleEstoque;
    private final ItemNotaFiscalConversor itemNotaFiscalConversor;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository, NotaFiscalService notaFiscalService,
                    ControleEstoque controleEstoque, ItemNotaFiscalConversor itemNotaFiscalConversor, ProdutoService produtoService,
                    ProdutoRepository produtoRepository) {
        super();
        this.repository = repository;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
        this.controleEstoque = controleEstoque;
        this.itemNotaFiscalConversor = itemNotaFiscalConversor;
        this.produtoRepository = produtoRepository;
    }

    public ItemNotaFiscalDto salvar(ItemNotaFiscalDto itemNotaFiscalDto) {

        try {
            ItemNotaFiscal itemNotaFiscal = itemNotaFiscalConversor.converter(itemNotaFiscalDto);
            itemNotaFiscal.setNotaFiscal(notaFiscalService.verificarPorId(itemNotaFiscalDto.getNotaFiscal().getId()));
            itemNotaFiscal.setProduto(produtoService.verificarPorId(itemNotaFiscalDto.getProduto().getId()));

            if (!repository.findByNotaFiscalAndProduto(itemNotaFiscal.getNotaFiscal(), itemNotaFiscal.getProduto()).isEmpty()) {
                throw new ItemNotaFiscalException("Falha ao salvar no banco: o item já está cadastrado nesta nota fiscal. ");
            }

            validar(itemNotaFiscal);
            if (!controleEstoque.controlarQuantidadeEstoque(itemNotaFiscal)) {
                return null;
            }

            itemNotaFiscal.setValorTotal(calcularValorTotal(itemNotaFiscal.getQuantidade(), itemNotaFiscal.getPrecoUnitario()));
            repository.save(itemNotaFiscal);
            notaFiscalService.persistirValorTotal(itemNotaFiscal.getNotaFiscal().getId());
            return itemNotaFiscalConversor.converter(itemNotaFiscal);
        } catch (ItemNotaFiscalException infe) {
            LOG.error(infe.getMessage());
            return null;
        }

    }

    public ItemNotaFiscalDto buscarPorId(int id) {
        ItemNotaFiscal entidade = repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um item com este id {}", id);
            return null;
        });

        return itemNotaFiscalConversor.converter(entidade);
    }

    // TO-DO
    public List<ItemNotaFiscal> buscarPorProduto(Produto produto) {
        return repository.findByProduto(produto);
    }

    // TO-DO
    public List<ItemNotaFiscal> buscarPorNotaFiscal(NotaFiscal notaFiscal) {
        return repository.findByNotaFiscal(notaFiscal);
    }

    public List<ItemNotaFiscalDto> buscarPorQuantidade(BigDecimal quantidade) {
        List<ItemNotaFiscal> itens = repository.findByQuantidade(quantidade);
        return itemNotaFiscalConversor.converter(itens);
    }

    public List<ItemNotaFiscalDto> buscarPorPreco(BigDecimal preco) {
        List<ItemNotaFiscal> itens = repository.findByPrecoUnitario(preco);
        return itemNotaFiscalConversor.converter(itens);
    }

    public List<ItemNotaFiscalDto> buscarPorValorTotal(BigDecimal valor) {
        List<ItemNotaFiscal> itens = repository.findByValorTotal(valor);
        return itemNotaFiscalConversor.converter(itens);
    }

    public List<ItemNotaFiscalDto> listarTodos() {
        List<ItemNotaFiscal> itens = repository.findAll();
        return itemNotaFiscalConversor.converter(itens);
    }

    public ItemNotaFiscalDto atualizar(ItemNotaFiscalDto item, int id) {
        try {
            ItemNotaFiscal itemNotaFiscal = itemNotaFiscalConversor.converter(item);
            ItemNotaFiscal itemNotaAtualizar = verificarPorId(id);

            if (itemNotaFiscal.getNotaFiscal() != null) {
                itemNotaAtualizar.setNotaFiscal(notaFiscalService.verificarPorId(itemNotaFiscal.getNotaFiscal().getId()));
            }
            if (itemNotaFiscal.getProduto() != null) {
                itemNotaAtualizar.setProduto(produtoService.verificarPorId(itemNotaAtualizar.getProduto().getId()));
            }
            if (itemNotaFiscal.getPrecoUnitario() != null) {
                itemNotaAtualizar.setPrecoUnitario(itemNotaFiscal.getPrecoUnitario());
            }
            if (itemNotaFiscal.getQuantidade() != null && itemNotaFiscal.getQuantidade().doubleValue() >= 0) {
                itemNotaAtualizar = controleEstoque.atualizarEstoque(itemNotaFiscal, itemNotaAtualizar);
                itemNotaAtualizar.setQuantidade(itemNotaFiscal.getQuantidade());
            }
            itemNotaAtualizar.setValorTotal(
                            calcularValorTotal(itemNotaAtualizar.getQuantidade(), itemNotaAtualizar.getPrecoUnitario()));
            validar(itemNotaAtualizar);
            repository.save(itemNotaAtualizar);
            notaFiscalService.persistirValorTotal(itemNotaAtualizar.getNotaFiscal().getId());
            return itemNotaFiscalConversor.converter(itemNotaAtualizar);
        } catch (ItemNotaFiscalException infe) {
            LOG.error(infe.getMessage());
            return null;
        }
    }

    private void validar(ItemNotaFiscal itemNotaFiscal) throws ItemNotaFiscalException {

        if (itemNotaFiscal.getPrecoUnitario().doubleValue() <= 0) {
            throw new ItemNotaFiscalException("Falha ao salvar no banco: o preço não pode ser menor ou igual a 0. ");
        }
        if (itemNotaFiscal.getQuantidade().doubleValue() <= 0) {
            throw new ItemNotaFiscalException("Falha ao salvar no banco: o preço não pode ser menor ou igual a 0. ");
        }

        if (itemNotaFiscal.getNotaFiscal() == null || itemNotaFiscal.getProduto() == null) {
            throw new ItemNotaFiscalException("Falha ao salvar no banco: a nota fiscal e o produto não podem ser nulo. ");

        }

    }

    public void deletarPorId(int id) {

        if (verificarPorId(id) != null) {
            ItemNotaFiscal itemNotaFiscal = verificarPorId(id);
            Produto produto = produtoService.verificarPorId(itemNotaFiscal.getProduto().getId());
            double quantidadeEstoque = itemNotaFiscal.getProduto().getEstoque();
            double quantidadeItem = itemNotaFiscal.getQuantidade().doubleValue();
            repository.deleteById(id);
            if (itemNotaFiscal.getNotaFiscal().getTipo().equalsIgnoreCase("entrada")) {
                quantidadeEstoque = controleEstoque.diminuirEstoque(quantidadeEstoque, quantidadeItem);

            } else if (itemNotaFiscal.getNotaFiscal().getTipo().equalsIgnoreCase("saida")) {
                quantidadeEstoque = controleEstoque.somarEstoque(quantidadeEstoque, quantidadeItem);
            }
            produto.setEstoque(quantidadeEstoque);
            produtoRepository.save(produto);
            notaFiscalService.persistirValorTotal(itemNotaFiscal.getNotaFiscal().getId());
            LOG.info("Deletado com sucesso");
        } else {
            LOG.info("Registro não encontrado");
        }

    }

    public ItemNotaFiscal verificarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um item com este id {}", id);
            return null;
        });
    }

    public static BigDecimal calcularValorTotal(BigDecimal quantidade, BigDecimal precoUnitario) {
        return precoUnitario.multiply(quantidade);

    }

}
