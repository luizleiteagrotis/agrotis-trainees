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

import java.util.List;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@RequestMapping("notas-fiscais/notas")
@RestController
public class NotaFiscalController {

    private final NotaFiscalService service;

    public NotaFiscalController(NotaFiscalService service) {
        super();
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody NotaFiscalDto nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(nota));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping
    @RequestMapping("/listar-tipo/{idTipo}")
    public ResponseEntity<List<NotaFiscal>> listarPorIdTipo(@PathVariable Integer idTipo) {
        return ResponseEntity.ok().body(service.listarPorTipo(idTipo));
    }

    @GetMapping("/listar-numero/{numero}")
    public ResponseEntity<List<NotaFiscal>> listarPorNumero(@PathVariable Integer numero) {
        return ResponseEntity.ok().body(service.listarPorNumero(numero));
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody NotaFiscal nota) {
        return ResponseEntity.ok().body(service.atualizar(nota));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
}
