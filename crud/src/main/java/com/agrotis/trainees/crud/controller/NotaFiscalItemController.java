package com.agrotis.trainees.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.service.NotaFiscalItemService;

@RestController
@RequestMapping("notas-fiscais/itens")
public class NotaFiscalItemController {

    private NotaFiscalItemService notaFiscalItemService;

    @Autowired
    public NotaFiscalItemController(NotaFiscalItemService notaFiscalItemService) {
        this.notaFiscalItemService = notaFiscalItemService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody NotaFiscalItemDto itemDto, UriComponentsBuilder uriBuilder) {
        NotaFiscalItem notaFiscalItem = notaFiscalItemService.salvar(itemDto);
        URI uri = uriBuilder.path("/notas-fiscais/itens/{id}").buildAndExpand(notaFiscalItem.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscalItem> buscarPorId(@PathVariable(name = "id") Integer id) {
        NotaFiscalItem notaFiscalItem = notaFiscalItemService.buscarPorId(id)
                        .orElseThrow(() -> new NotFoundException("Nota Fiscal Item não encontrado para o ID: " + id));
        return ResponseEntity.ok(notaFiscalItem);
    }

    @GetMapping
    public ResponseEntity<Iterable<NotaFiscalItem>> listarTodos() {
        Iterable<NotaFiscalItem> itens = notaFiscalItemService.buscarTodos();
        return ResponseEntity.ok(itens);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable(name = "id") Integer id) {
        notaFiscalItemService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
