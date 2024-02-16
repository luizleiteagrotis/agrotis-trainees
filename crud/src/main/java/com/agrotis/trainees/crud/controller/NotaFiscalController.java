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

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@RequestMapping("notas-fiscais")
@RestController
public class NotaFiscalController {

    private final NotaFiscalService service;

    public NotaFiscalController(NotaFiscalService service) {
        super();
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody NotaFiscalDto notaFiscalDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(notaFiscalDto));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<?> buscarPorNumero(@PathVariable String numero) {
        return ResponseEntity.ok().body(service.buscarPorNumero(numero));
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody NotaFiscalDto notaFiscalDto) {
        return ResponseEntity.ok().body(service.atualizar(notaFiscalDto));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
}
