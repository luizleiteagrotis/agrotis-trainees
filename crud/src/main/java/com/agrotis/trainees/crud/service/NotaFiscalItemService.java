package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscalItem;
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

    public NotaFiscalItem salvar(NotaFiscalItem notaFiscalItem) {
        calcularValorTotal(notaFiscalItem);
        Double valorTotalItem = notaFiscalItem.getValorTotal();
        LOG.info("Valor Total do Item antes de salvar: {}", valorTotalItem);

        NotaFiscalItem savedItem = repository.save(notaFiscalItem);

        LOG.info("Item salvo no banco de dados: {}", savedItem);

        // Atualize o Valor Total da Nota Fiscal
        notaFiscalService.atualizarValorTotal(savedItem.getNotaFiscal().getId(), valorTotalItem);

        return savedItem;
    }

    public Optional<NotaFiscalItem> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<NotaFiscalItem> buscarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        Optional<NotaFiscalItem> optionalItem = repository.findById(id);
        if (optionalItem.isPresent()) {
            NotaFiscalItem item = optionalItem.get();
            Double valorTotalItem = item.getValorTotal();

            // Atualiza o Valor Total da Nota Fiscal antes de excluir o item
            notaFiscalService.atualizarValorTotal(item.getNotaFiscal().getId(), -valorTotalItem);

            repository.deleteById(id);
        }
    }

    public void calcularValorTotal(NotaFiscalItem notaFiscalItem) {
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
        if (notaFiscalItem.getNotaFiscal().getNotaFiscalTipo() != notaFiscalItem.getNotaFiscal().getNotaFiscalTipo().SAIDA) {
            produto.setEstoque(produto.getEstoque() + quantidade);
        } else {
            produto.setEstoque(produto.getEstoque() - quantidade);

        }
        produtoTipoService.salvar(produto);
    }
}