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

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;


@RequestMapping("/parceiros")
@RestController
public class ParceiroNegocioController {

	@Autowired
	private ParceiroNegocioService service;
	
	@PostMapping
    public ResponseEntity<?> inserir(@RequestBody ParceiroNegocio parceiro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(parceiro));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody ParceiroNegocio parceiro) {
        return ResponseEntity.ok().body(service.atualizar(parceiro));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
  
}
