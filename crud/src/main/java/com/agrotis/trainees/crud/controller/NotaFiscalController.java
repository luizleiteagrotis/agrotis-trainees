package com.agrotis.trainees.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@RequestMapping("notas-fiscais")
@RestController
public class NotaFiscalController {

    private final NotaFiscalService notaService;

    public NotaFiscalController(NotaFiscalService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody NotaFiscalDto nota) {
        return ResponseEntity.ok(notaService.salvar(nota));
    }

}
