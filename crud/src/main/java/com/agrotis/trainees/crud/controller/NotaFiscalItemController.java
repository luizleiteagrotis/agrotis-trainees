package com.agrotis.trainees.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.service.ItemNotaFiscalService;

@RequestMapping("notas-fiscais/item")
@RestController
public class NotaFiscalItemController {

    private final ItemNotaFiscalService itemService;

    public NotaFiscalItemController(ItemNotaFiscalService itemService) {
        this.itemService = itemService;

    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody NotaFiscalItemDto item) {

        NotaFiscalItemDto itemNota = itemService.salvar(item);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemNota.getId()).toUri();
        return ResponseEntity.created(uri).body(itemNota);
    }

    @GetMapping("/listarTodos/{list}")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(itemService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(itemService.buscarPorId(id));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        itemService.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody NotaFiscalItemDto dto) {
        return ResponseEntity.ok(itemService.update(id, dto));
    }

}
