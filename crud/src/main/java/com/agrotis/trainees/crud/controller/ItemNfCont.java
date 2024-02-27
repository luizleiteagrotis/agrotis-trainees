package com.agrotis.trainees.crud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.trainees.crud.dto.ItemNfDto;
import com.agrotis.trainees.crud.service.ItemNotaFiscalService;

@RequestMapping("notas/itens")
@RestController
public class ItemNfCont {

    @Autowired
    private ItemNotaFiscalService service;

    @PostMapping
    public ResponseEntity<?> inserir(@Valid @RequestBody ItemNfDto dto) {
        ItemNfDto itemSalvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(itemSalvo));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody ItemNfDto dto) {
        return ResponseEntity.ok().body(service.atualizar(dto));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) throws NotFoundException {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }

}
