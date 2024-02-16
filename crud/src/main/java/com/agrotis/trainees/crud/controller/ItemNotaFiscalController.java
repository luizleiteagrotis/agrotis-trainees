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

import com.agrotis.trainees.crud.dto.ItemNotaFiscalDto;
import com.agrotis.trainees.crud.service.ItemNotaFiscalService;

@RequestMapping("notas-fiscais/itens")
@RestController
public class ItemNotaFiscalController {

    private final ItemNotaFiscalService service;

    public ItemNotaFiscalController(ItemNotaFiscalService service) {
        super();
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody ItemNotaFiscalDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<?> buscarPorProduto(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.buscarPorProduto(id));
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody ItemNotaFiscalDto notaFiscalDto) {
        return ResponseEntity.ok().body(service.atualizar(notaFiscalDto));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
}
