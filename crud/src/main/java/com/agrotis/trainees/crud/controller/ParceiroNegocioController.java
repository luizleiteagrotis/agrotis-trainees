package com.agrotis.trainees.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(service.salvar(parceiroNegocio));
    }

}
