package com.agrotis.trainees.crud.helper;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Component
public class Validador {
    private ParceiroNegocioRepository parceiroNegocioRepository;
    private NotaFiscalRepository notaFiscalRepository;
    private ProdutoRepository produtoRepository;

    Validador(ParceiroNegocioRepository parceiroNegocioRepository, NotaFiscalRepository notaFiscalRepository,
                    ProdutoRepository produtoRepository) {
        super();
        this.parceiroNegocioRepository = parceiroNegocioRepository;
        this.notaFiscalRepository = notaFiscalRepository;
        this.produtoRepository = produtoRepository;
    }

    public boolean existeParceiroPorId(int id) {
        return parceiroNegocioRepository.findById(id).orElseGet(() -> {
            return null;
        }) != null;
    }

    public boolean existeNotaFiscalPorId(int id) {
        return notaFiscalRepository.findById(id).orElseGet(() -> {
            return null;
        }) != null;
    }

    public boolean existeProdutoPorId(int id) {
        return produtoRepository.findById(id).orElseGet(() -> {
            return null;
        }) != null;
    }

}
