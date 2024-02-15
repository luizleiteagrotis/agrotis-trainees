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

import java.util.Map;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@RequestMapping("notas-fiscais/parceiros")
@RestController
public class ParceiroNegocioController {

    private final ParceiroNegocioService service;

    public ParceiroNegocioController(ParceiroNegocioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody ParceiroNegocioDto parceiroNegocioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(parceiroNegocioDto));
    }

    @GetMapping("/por-nome")
    public ResponseEntity<?> buscarPorNome(@RequestBody Map<String, String> requestBody) {
        String nome = requestBody.get("nome");
        return ResponseEntity.ok().body(service.buscarPorNome(nome));
    }

    @GetMapping("/por-inscricao")
    public ResponseEntity<?> buscarPorInscricao(@RequestBody Map<String, String> requestBody) {
        String inscricao = requestBody.get("inscricao");
        return ResponseEntity.ok().body(service.buscarPorInscricaoFiscal(inscricao));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody ParceiroNegocio parceiroNegocio) {
        return ResponseEntity.ok().body(service.atualizar(parceiroNegocio));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
}
