package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItemService.class);

    private final NotaFiscalItemRepository repository;
    private final NotaFiscalService notaFiscalService;
    private final ProdutoTipoService produtoTipoService;

    public NotaFiscalItemService(NotaFiscalItemRepository repository, NotaFiscalService notaFiscalService,
                    ProdutoTipoService produtoTipoService) {
        this.repository = repository;
        this.notaFiscalService = notaFiscalService;
        this.produtoTipoService = produtoTipoService;
    }

    @Transactional
    public NotaFiscalItem salvar(NotaFiscalItem notaFiscalItem) {
        calcularValorTotal(notaFiscalItem);
        Double valorTotalItem = notaFiscalItem.getValorTotal();
        LOG.info("Valor Total do Item antes de salvar: {}", valorTotalItem);

        try {
            NotaFiscalItem savedItem = repository.save(notaFiscalItem);
            repository.flush();

            LOG.info("Item salvo no banco de dados: {}", savedItem);

            NotaFiscal notaFiscal = savedItem.getNotaFiscal();
            if (notaFiscal != null) {
                notaFiscalService.atualizarValorTotal(notaFiscal.getId(), valorTotalItem);
            }

            return savedItem;
        } catch (Exception e) {
            LOG.error("Erro ao salvar Nota Fiscal Item: {}", e.getMessage());
            throw e;
        }
    }

    public Optional<NotaFiscalItem> buscarPorId(Integer id) {
        try {
            return repository.findById(id);
        } catch (DataAccessException e) {
            handleDataAccessException("Erro ao buscar Nota Fiscal Item por ID", e);
            return Optional.empty();
        }
    }

    public List<NotaFiscalItem> buscarTodos() {
        try {
            return repository.findAll();
        } catch (DataAccessException e) {
            handleDataAccessException("Erro ao buscar todos os Itens de Nota Fiscal", e);
            return List.of();
        }
    }

    @Transactional
    public void deletarPorId(Integer id) {
        try {
            repository.findById(id).ifPresent(item -> {
                Double valorTotalItem = item.getValorTotal();
                notaFiscalService.atualizarValorTotal(item.getNotaFiscal().getId(), -valorTotalItem);
                repository.deleteById(id);
            });
        } catch (DataAccessException e) {
            handleDataAccessException("Erro ao deletar Nota Fiscal Item por ID", e);
        }
    }

    private void calcularValorTotal(NotaFiscalItem notaFiscalItem) {
        Integer quantidade = notaFiscalItem.getQuantidade();
        Double precoUnitario = notaFiscalItem.getPreco_unitario();
        if (quantidade != null && precoUnitario != null) {
            Double valorTotal = quantidade * precoUnitario;
            notaFiscalItem.setValorTotal(valorTotal);
        }
    }

    public void controlarEstoque(NotaFiscalItem notaFiscalItem) {
        Integer quantidade = notaFiscalItem.getQuantidade();
        Produto produto = notaFiscalItem.getProduto();
        notaFiscalItem.getNotaFiscal().getNotaFiscalTipo();
        if (notaFiscalItem.getNotaFiscal().getNotaFiscalTipo() != NotaFiscalTipo.SAIDA) {
            produto.setEstoque(produto.getEstoque() + quantidade);
        } else {
            produto.setEstoque(produto.getEstoque() - quantidade);
        }
        produtoTipoService.salvar(produto);
    }

    private void handleDataAccessException(String message, DataAccessException e) {
        LOG.error("{}: {}", message, e.getMessage());
    }

}
