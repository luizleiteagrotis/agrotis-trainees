package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.TipoNota;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.utils.ValidacaoUtils;

@Service
public class ItemNotaService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaService.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoService produtoService;
    private final CabecalhoNotaService cabecalhoNotaService;

    public ItemNotaService(NotaFiscalItemRepository repository, ProdutoService produtoService,
                    CabecalhoNotaService cabecalhoNotaService) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.cabecalhoNotaService = cabecalhoNotaService;
    }

    public ItemNota salvar(ItemNota entidade) {
        calcularValorTotal(entidade);
        adicionarValorTotalCabecalho(entidade);
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

    private void atualizarEstoque(ItemNota itemNota) {
        Produto produto = itemNota.getProduto();
        Integer quantidade = itemNota.getQuantidade();
        TipoNota notaFiscalTipo = itemNota.getCabecalhoNota().getNotaFiscalTipo();
        Integer quantidadeProduto = produto.getQuantidadeEstoque();

        ValidacaoUtils.validarQuantidadeNaoNegativa(quantidade);

        if (notaFiscalTipo == TipoNota.SAIDA) {
            ValidacaoUtils.validarQuantidadeEstoqueSuficiente(quantidadeProduto, quantidade);
        }

        if (notaFiscalTipo == TipoNota.ENTRADA) {
            produto.setQuantidadeEstoque(quantidadeProduto + quantidade);
        } else {
            produto.setQuantidadeEstoque(quantidadeProduto - quantidade);
        }

        produtoService.salvar(produto);
    }

    private void adicionarValorTotalCabecalho(ItemNota item) {
        CabecalhoNota cabecalho = item.getCabecalhoNota();
        Double valorTotalItem = item.getValorTotal();
        cabecalho.setValorTotal(valorTotalItem);
        cabecalhoNotaService.salvar(cabecalho);

    }

}
