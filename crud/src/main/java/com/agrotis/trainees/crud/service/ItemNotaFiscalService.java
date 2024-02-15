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

        ItemNotaFiscal itemNotaFiscal = itemNotaFiscalConversor.converter(itemNotaFiscalDto);
        NotaFiscal notaFiscal = notaFiscalService.verificarPorId(itemNotaFiscalDto.getNotaFiscal().getId());
        itemNotaFiscal.setNotaFiscal(notaFiscal);
        Produto produto = produtoService.verificarPorId(itemNotaFiscalDto.getProduto().getId());
        itemNotaFiscal.setProduto(produto);
        if (repository.findByNotaFiscalAndProduto(itemNotaFiscal.getNotaFiscal(), itemNotaFiscal.getProduto()).isEmpty()) {
            if (controleEstoque.controlarQuantidadeEstoque(itemNotaFiscal) < 0) {
                return null;
            }
            itemNotaFiscal.setValorTotal(calcularValorTotal(itemNotaFiscal.getQuantidade(), itemNotaFiscal.getPrecoUnitario()));
            repository.save(itemNotaFiscal);

            // verifica tabela da nota fiscal
            notaFiscalService.persistirValorTotal(itemNotaFiscal.getNotaFiscal().getId());
            LOG.info("Item cadastrado com sucesso");

            return itemNotaFiscalConversor.converter(itemNotaFiscal);
        } else {
            LOG.error("Já existe este item cadastrado no banco de dados");
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

        ItemNotaFiscal itemAtualizar = verificarPorId(id);

        /*
         * if (itemAtualizar != null) { if (item.getNotaFiscal() != null &&
         * Validador.existeNotaFiscalPorId(item.getNotaFiscal().getId())) {
         * itemAtualizar.setNotaFiscal(item.getNotaFiscal()); } if
         * (item.getQuantidade() != null && item.getQuantidade().doubleValue() >
         * 0) { double estoque = itemAtualizar.getProduto().getEstoque(); double
         * quantidade = itemAtualizar.getQuantidade().doubleValue(); Produto
         * produto = itemAtualizar.getProduto(); if
         * (itemAtualizar.getNotaFiscal().getTipo().equalsIgnoreCase("entrada"))
         * { estoque -= quantidade;
         * itemAtualizar.setQuantidade(item.getQuantidade()); estoque +=
         * itemAtualizar.getQuantidade().doubleValue(); produto =
         * itemAtualizar.getProduto(); } else if
         * (itemAtualizar.getNotaFiscal().getTipo().equalsIgnoreCase("saida") &&
         * controleEstoque.verificarQuantidade(estoque, quantidade)) { estoque
         * += quantidade; itemAtualizar.setQuantidade(item.getQuantidade());
         * estoque -= itemAtualizar.getQuantidade().doubleValue(); produto =
         * itemAtualizar.getProduto(); } produto.setEstoque(estoque);
         * produtoRepository.save(produto); }
         * 
         * if (item.getProduto() != null &&
         * Validador.existeProdutoPorId(item.getProduto().getId())) {
         * itemAtualizar.setProduto(item.getProduto()); }
         * 
         * if (item.getPrecoUnitario() != null &&
         * item.getPrecoUnitario().doubleValue() > 0) {
         * itemAtualizar.setPrecoUnitario(item.getPrecoUnitario()); }
         * 
         * itemAtualizar.setValorTotal(calcularValorTotal(item.getQuantidade(),
         * item.getPrecoUnitario())); LOG.info("Item Atualizado");
         * 
         * repository.save(itemAtualizar);
         * notaFiscalService.persistirValorTotal(itemAtualizar.getNotaFiscal().
         * getId()); return itemNotaFiscalConversor.converter(itemAtualizar); }
         * else { LOG.error("O item que deseja atualiza, não existe."); return
         * null; }
         */
        return null;

    }

    public void deletarPorId(int id) {

        if (verificarPorId(id) != null) {
            ItemNotaFiscal itemNotaFiscal = verificarPorId(id);
            repository.deleteById(id);
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
