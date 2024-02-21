package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Component
public class ValorNotaFiscalService {

    private final NotaFiscalService service;
    private final NotaFiscalRepository repository;

    public ValorNotaFiscalService(NotaFiscalService service, NotaFiscalRepository repository) {
        super();
        this.service = service;
        this.repository = repository;
    }

    public void atualizarValorTotalNota(NotaFiscalItem item) {
        NotaFiscal nota = service.buscarPorId(item.getIdNota().getId());
        nota.setValorTotal(nota.getValorTotal().add(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade()))));

        nota = repository.save(nota);
    }
}
