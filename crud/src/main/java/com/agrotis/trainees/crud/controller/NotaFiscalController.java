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

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@RequestMapping("notas-fiscais")
@RestController
public class NotaFiscalController {

	@Autowired
	private NotaFiscalService service;
	
	@PostMapping
    public ResponseEntity<?> inserir(@RequestBody NotaFiscal nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(nota));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody NotaFiscal nota) {
        return ResponseEntity.ok().body(service.atualizar(nota));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
	
}
