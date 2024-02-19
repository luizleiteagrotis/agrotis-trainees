package com.agrotis.trainees.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.service.NotaFiscalItemService;

@RestController
@RequestMapping("notas-fiscais/itens")
public class NotaFiscalItemController {

    private final NotaFiscalItemService notaFiscalItemService;

    @Autowired
    public NotaFiscalItemController(NotaFiscalItemService notaFiscalItemService) {
        this.notaFiscalItemService = notaFiscalItemService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody NotaFiscalItemDto itemDto) {
        try {
            NotaFiscalItem notaFiscalItem = notaFiscalItemService.salvar(itemDto);
            URI location = new URI("/notas-fiscais/itens/" + notaFiscalItem.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar o item da nota fiscal: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            NotaFiscalItemDto notaFiscalItemDto = notaFiscalItemService.buscarPorId(id);
            return ResponseEntity.ok(notaFiscalItemDto);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<NotaFiscalItemDto>> listarTodos() {
        List<NotaFiscalItemDto> itens = notaFiscalItemService.listarTodos();
        return ResponseEntity.ok(itens);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        notaFiscalItemService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
