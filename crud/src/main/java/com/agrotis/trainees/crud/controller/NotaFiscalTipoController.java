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

import com.agrotis.trainees.crud.dto.NotaFiscalTipoDto;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;

@RequestMapping("notas-fiscais/tipos")
@RestController
public class NotaFiscalTipoController {

    private final NotaFiscalTipoService service;

    public NotaFiscalTipoController(NotaFiscalTipoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody NotaFiscalTipoDto notaFiscalTipoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(notaFiscalTipoDto));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(service.buscarPorNome(nome));
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody NotaFiscalTipoDto notaFiscalTipoDto) {
        return ResponseEntity.ok().body(service.atualizar(notaFiscalTipoDto));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
}
