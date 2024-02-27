package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class CalcularListaItemService {

    private final NotaFiscalRepository repository;

    public CalcularListaItemService(NotaFiscalRepository repository) {
        this.repository = repository;
    }

    public void adicionarItem(ItemNotaFiscal item) {
        List<ItemNotaFiscal> itens = item.getNotaFiscal().getItens();
        if (itens == null) {
            itens = new ArrayList<>();
            item.getNotaFiscal().setItens(itens);
        }

        if (!itens.contains(item)) {
            itens.add(item);
        }
        atualizarValorTotal(item.getNotaFiscal());
    }

    public void removerItem(ItemNotaFiscal item, NotaFiscal notaFiscal) {
        List<ItemNotaFiscal> itens = notaFiscal.getItens();
        List<ItemNotaFiscal> listaFiltrada = itens.stream().filter((i) -> i.getId() != item.getId()).collect(Collectors.toList());
        notaFiscal.setItens(listaFiltrada);
        atualizarValorTotal(notaFiscal);
    }

    public void atualizarValorTotal(NotaFiscal notaFiscal) {
        BigDecimal valor_total = BigDecimal.ZERO;

        for (ItemNotaFiscal item : notaFiscal.getItens()) {
            BigDecimal valorTotaltem = item.getValorTotal();
            if (valorTotaltem != null) {
                valor_total = valor_total.add(valorTotaltem);
            }
        }
        notaFiscal.setValorTotal(valor_total);
        repository.save(notaFiscal);
    }

}
