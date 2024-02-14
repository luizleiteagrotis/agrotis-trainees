package com.agrotis.trainees.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@RestController
@RequestMapping("/parceiros-negocio")
public class ParceiroNegocioController {

    private final ParceiroNegocioService negocioService;

    public ParceiroNegocioController(ParceiroNegocioService negocioService) {
        this.negocioService = negocioService;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ParceiroNegocioDto dto) {
        return ResponseEntity.ok(negocioService.salvar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(negocioService.buscarPorId(id));
    }

    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(negocioService.buscarPorNome(nome));
    }

    @GetMapping("/listarTodos/{listar}")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(negocioService.listarTodos());
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        negocioService.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }

}
