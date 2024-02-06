package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class ItemNotaService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaService.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoService produtoService;

    public ItemNotaService(NotaFiscalItemRepository repository, ProdutoService produtoService) {
        this.repository = repository;
        this.produtoService = produtoService;
    }

    public ItemNota salvar(ItemNota entidade) {
        atualizarEstoque(entidade);
        return repository.save(entidade);
    }

    public List<ItemNota> buscarTodos() {
        return repository.findAll();
    }

    public ItemNota buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.info("Não foi possível encontrar a nota fiscal pelo ID {}", id);
            return null;
        });
    }

    public ItemNota atualizar(Integer id, ItemNota notaFiscalItem) {
        ItemNota byId = repository.findById(id).orElseGet(() -> {
            LOG.info("Não foi possível encontrar a nota fiscal pelo ID {}", id);
            return null;
        });
        return repository.save(byId);
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

    public void calcularValorTotal(ItemNota notaFiscalItem) {
        Integer quantidade = notaFiscalItem.getQuantidade();
        Double precoUnitario = notaFiscalItem.getPrecoUnitario();
        if (quantidade != null && precoUnitario != null) {
            Double valorTotal = quantidade * precoUnitario;
            notaFiscalItem.setValorTotal(valorTotal);
        }
    }

    public void atualizarEstoque(ItemNota itemNota) {
        Produto produto = itemNota.getProduto();
        Integer quantidade = itemNota.getQuantidade();
        TipoNota notaFiscalTipo = itemNota.getNotaFiscal().getNotaFiscalTipo();
        Integer quantidadeProduto = itemNota.getProduto().getQuantidadeEstoque();

        if (notaFiscalTipo == TipoNota.ENTRADA) {
            produto.setQuantidadeEstoque(quantidadeProduto + quantidade);
        } else {
            if (quantidadeProduto - quantidade >= 0) {
                produto.setQuantidadeEstoque(quantidadeProduto - quantidade);
            } else {
                throw new IllegalArgumentException("Não é possível remover mais itens do que o disponível em estoque.");
            }
        }
        produtoService.salvar(produto);
    }

}
