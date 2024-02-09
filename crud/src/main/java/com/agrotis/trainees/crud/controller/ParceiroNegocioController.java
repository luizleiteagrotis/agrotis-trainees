package com.agrotis.trainees.crud.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@RestController
@RequestMapping("/parceiros-negocio")
public class ParceiroNegocioController {

    private final ParceiroNegocioService service;

    @Autowired
    public ParceiroNegocioController(ParceiroNegocioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ParceiroNegocioDto parceiroNegocio) {
        ParceiroNegocioDto parceiroNegocioSalvo = service.salvar(parceiroNegocio);

        URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(parceiroNegocioSalvo.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(parceiroNegocioSalvo);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    
    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<?> buscarPorId(@PathVariable String nome){
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }
    
    @GetMapping
    public ResponseEntity<?> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }
    
    

    
}
