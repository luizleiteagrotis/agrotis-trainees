package com.agrotis.trainees.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(notaService.buscarPorId(id));
    }

    @GetMapping("/listarTodos/{list}")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(notaService.listarTodos());
    }

    @DeleteMapping
    @RequestMapping("/deletar/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        notaService.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }

}
