package com.agrotis.trainees.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;

@RequestMapping("notas-fiscais/tipos")
@RestController
public class NotaFiscalTipoController {

    private final NotaFiscalTipoService service;

    public NotaFiscalTipoController(NotaFiscalTipoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody NotaFiscalTipo notaFiscalTipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(notaFiscalTipo));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody NotaFiscalTipo notaFiscalTipo) {
        return ResponseEntity.ok().body(service.atualizar(notaFiscalTipo));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
}
