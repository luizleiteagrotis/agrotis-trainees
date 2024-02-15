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

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@RequestMapping("parceiros-negocio")
@RestController
public class ParceiroNegocioController {
    private final ParceiroNegocioService service;

    public ParceiroNegocioController(ParceiroNegocioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody ParceiroNegocioDto parceiroNegocioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(parceiroNegocioDto));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @GetMapping("/inscricao-fiscal/{inscricaoFiscal}")
    public ResponseEntity<?> buscarPorInscricaoFiscal(@PathVariable String inscricaoFiscal) {
        return ResponseEntity.ok().body(service.buscarPorInscricaoFiscal(inscricaoFiscal));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@RequestBody ParceiroNegocioDto parceiroNegocioDto, @PathVariable int id) {
        return ResponseEntity.ok().body(service.atualizar(parceiroNegocioDto, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable int id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
}
