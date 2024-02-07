package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.TipoNota;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.service.exceptions.QuantidadeEmEstoqueException;

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
        calcularValorTotal(entidade);
        atualizarEstoque(entidade);
        return repository.save(entidade);
    }

    public List<ItemNota> buscarTodos() {
        return repository.findAll();
    }

    public ItemNota buscarPorId(Integer id) {
        return repository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
    }

    public ItemNota atualizar(Integer id, ItemNota notaFiscalItem) {
        return repository.findById(id).map(itemNotaExistente -> {
            itemNotaExistente.setCabecalhoNota(notaFiscalItem.getCabecalhoNota());
            itemNotaExistente.setPrecoUnitario(notaFiscalItem.getPrecoUnitario());
            itemNotaExistente.setProduto(notaFiscalItem.getProduto());
            itemNotaExistente.setQuantidade(notaFiscalItem.getQuantidade());
            itemNotaExistente.setValorTotal(notaFiscalItem.getValorTotal());
            return repository.save(itemNotaExistente);
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(entidade -> {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
            return entidade;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));

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
        TipoNota notaFiscalTipo = itemNota.getCabecalhoNota().getNotaFiscalTipo();
        Integer quantidadeProduto = itemNota.getProduto().getQuantidadeEstoque();

        if (notaFiscalTipo == TipoNota.ENTRADA) {
            produto.setQuantidadeEstoque(quantidadeProduto + quantidade);
        } else {
            if (quantidadeProduto - quantidade >= 0) {
                produto.setQuantidadeEstoque(quantidadeProduto - quantidade);
            } else {
                throw new QuantidadeEmEstoqueException("Não é possível remover mais itens do que o disponível em estoque.");
            }
        }
        produtoService.salvar(produto);
    }

}
