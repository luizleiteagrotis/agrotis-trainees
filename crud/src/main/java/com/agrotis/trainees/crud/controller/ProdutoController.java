package com.agrotis.trainees.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.service.ProdutoTipoService;

@RequestMapping("produtos")
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoTipoService service;

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(produtoDto));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ProdutoDto produtoDto) {
        produtoDto.setId(id);
        return ResponseEntity.ok().body(service.atualizar(produtoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
}
