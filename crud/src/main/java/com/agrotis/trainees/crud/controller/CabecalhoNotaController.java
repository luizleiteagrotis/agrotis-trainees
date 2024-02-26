package com.agrotis.trainees.crud.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.agrotis.trainees.crud.dtos.CabecalhoNotaDto;
import com.agrotis.trainees.crud.service.CabecalhoNotaService;
import com.agrotis.trainees.crud.service.impl.CabecalhoNotaServiceImpl;

@RestController
@RequestMapping("/cabecalhos")
public class CabecalhoNotaController {

    private final CabecalhoNotaService service;

    @Autowired
    public CabecalhoNotaController(CabecalhoNotaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CabecalhoNotaDto cabecalho) {
        CabecalhoNotaDto cabecalhoSalvo = service.salvar(cabecalho);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cabecalhoSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(cabecalhoSalvo);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody CabecalhoNotaDto dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
