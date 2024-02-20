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

import javax.validation.Valid;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.service.ParceiroNegocioTipoService;

@RequestMapping("parceiros")
@RestController
public class ParceiroNegocioController {

    @Autowired
    private ParceiroNegocioTipoService service;

    @PostMapping
    public ResponseEntity<?> inserir(@Valid @RequestBody ParceiroNegocio parceiro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(parceiro));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParceiroNegocio> atualizar(@PathVariable Integer id, @RequestBody ParceiroNegocio parceiroNegocio) {
        ParceiroNegocio parceiroAtualizado = service.atualizar(id, parceiroNegocio);
        return new ResponseEntity<>(parceiroAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }

}