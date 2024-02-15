package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
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
    public NotaFiscalItem salvar(NotaFiscalItemDto notaFiscalItemDto) {
        NotaFiscalItem notaFiscalItem = convertDtoToEntity(notaFiscalItemDto);
        calcularValorTotal(notaFiscalItem);
        controlarEstoque(notaFiscalItem);
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

    public Iterable<NotaFiscalItem> buscarTodos() {
        return repository.findAll();
    }

    private void calcularValorTotal(NotaFiscalItem notaFiscalItem) {
        Integer quantidade = notaFiscalItem.getQuantidade();
        Double precoUnitario = notaFiscalItem.getPreco_unitario();
        if (quantidade != null && precoUnitario != null) {
            Double valorTotal = quantidade * precoUnitario;
            notaFiscalItem.setValorTotal(valorTotal);
        }
    }

    private void controlarEstoque(NotaFiscalItem notaFiscalItem) {
        Integer quantidade = notaFiscalItem.getQuantidade();
        Produto produto = notaFiscalItem.getProduto();
        NotaFiscalTipo tipoNotaFiscal = notaFiscalItem.getNotaFiscal().getNotaFiscalTipo();
        if (tipoNotaFiscal != NotaFiscalTipo.SAIDA) {
            produto.setEstoque(produto.getEstoque() + quantidade);
        } else {
            produto.setEstoque(produto.getEstoque() - quantidade);
        }
        produtoTipoService.salvar(produto);
    }

    private NotaFiscalItem convertDtoToEntity(NotaFiscalItemDto notaFiscalItemDto) {
        NotaFiscalItem notaFiscalItem = new NotaFiscalItem();
        notaFiscalItem.setQuantidade(notaFiscalItemDto.getQuantidade());
        notaFiscalItem.setPreco_unitario(notaFiscalItemDto.getPreco_unitario());
        return notaFiscalItem;
    }
}
