package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.helper.Validador;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;

@Service
public class ItemNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);
    private final ItemNotaFiscalRepository repository;
    private final NotaFiscalService notaFiscalService;
    private final ControleEstoque controleEstoque;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository, NotaFiscalService notaFiscalService,
                    ControleEstoque controleEstoque) {
        super();
        this.repository = repository;
        this.notaFiscalService = notaFiscalService;
        this.controleEstoque = controleEstoque;
    }

    public ItemNotaFiscal salvar(ItemNotaFiscal itemNotaFiscal) {
        if (controleEstoque.controlarQuantidadeEstoque(itemNotaFiscal) < 0) {
            return null;
        }
        if (repository.findByNotaFiscalAndProduto(itemNotaFiscal.getNotaFiscal(), itemNotaFiscal.getProduto()).isEmpty()) {
            itemNotaFiscal.setValorTotal(calcularValorTotal(itemNotaFiscal.getQuantidade(), itemNotaFiscal.getPrecoUnitario()));
            ItemNotaFiscal itemNota = repository.save(itemNotaFiscal);

            // verifica tabela da nota fiscal
            notaFiscalService.persistirValorTotal(itemNotaFiscal.getNotaFiscal().getId());
            LOG.info("Item cadastrado com sucesso");
            return itemNota;
        } else {
            LOG.error("Já existe este item cadastrado no banco de dados");
            return null;
        }

    }

    public ItemNotaFiscal buscarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um item com este id {}", id);
            return null;
        });
    }

    public List<ItemNotaFiscal> buscarPorProduto(Produto produto) {
        return repository.findByProduto(produto);
    }

    public List<ItemNotaFiscal> buscarPorNotaFiscal(NotaFiscal notaFiscal) {
        return repository.findByNotaFiscal(notaFiscal);
    }

    public List<ItemNotaFiscal> buscarPorQuantidade(double quantidade) {
        return repository.findByQuantidade(quantidade);
    }

    public List<ItemNotaFiscal> buscarPorPreco(double preco) {
        return repository.findByPrecoUnitario(preco);
    }

    public List<ItemNotaFiscal> buscarPorValorTotal(double valor) {
        return repository.findByValorTotal(valor);
    }

    public List<ItemNotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public ItemNotaFiscal atualizar(ItemNotaFiscal item, int id) {
        ItemNotaFiscal itemAtualizar = buscarPorId(id);
        if (itemAtualizar != null) {
            if (item.getNotaFiscal() != null && Validador.existeNotaFiscalPorId(item.getNotaFiscal().getId())) {
                itemAtualizar.setNotaFiscal(item.getNotaFiscal());
            }
            if (item.getQuantidade() != null && item.getQuantidade().doubleValue() > 0) {
                itemAtualizar.setQuantidade(item.getQuantidade());
            }

            if (item.getProduto() != null && Validador.existeProdutoPorId(item.getProduto().getId())) {
                itemAtualizar.setProduto(item.getProduto());
            }

            if (item.getPrecoUnitario() != null && item.getPrecoUnitario().doubleValue() > 0) {
                itemAtualizar.setPrecoUnitario(item.getPrecoUnitario());
            }

            itemAtualizar.setValorTotal(calcularValorTotal(item.getQuantidade(), item.getPrecoUnitario()));
            LOG.info("Item Atualizado");

            repository.save(itemAtualizar);

            notaFiscalService.persistirValorTotal(itemAtualizar.getNotaFiscal().getId());
            return itemAtualizar;
        } else {
            LOG.error("O item que deseja atualiza, não existe.");
            return null;
        }

    }

    public void deletarPorId(int id) {

        if (buscarPorId(id) != null) {
            ItemNotaFiscal itemNotaFiscal = buscarPorId(id);
            repository.deleteById(id);
            notaFiscalService.persistirValorTotal(itemNotaFiscal.getNotaFiscal().getId());
            LOG.info("Deletado com sucesso");
        } else {
            LOG.info("Registro não encontrado");
        }

    }

    public static BigDecimal calcularValorTotal(BigDecimal quantidade, BigDecimal precoUnitario) {
        return precoUnitario.multiply(quantidade);

    }

}
