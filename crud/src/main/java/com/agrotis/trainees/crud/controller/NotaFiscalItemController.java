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

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.service.NotaFiscalItemService;

@RequestMapping("notas-fiscais/items")
@RestController
public class NotaFiscalItemController {

    private final NotaFiscalItemService service;

    public NotaFiscalItemController(NotaFiscalItemService service) {
        super();
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody NotaFiscalItemDto item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(item));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("/por-nota/{nota}")
    public ResponseEntity<List<NotaFiscalItem>> listarPorNota(@PathVariable Integer nota) {
        return ResponseEntity.ok().body(service.listarPorNota(nota));
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody NotaFiscalItem item) {
        return ResponseEntity.ok().body(service.atualizar(item));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
}
