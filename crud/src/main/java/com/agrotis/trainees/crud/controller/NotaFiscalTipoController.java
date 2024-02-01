package com.agrotis.trainees.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;

@RequestMapping("notas-fiscais/tipos")
@RestController
public class NotaFiscalTipoController {
	
	private final NotaFiscalTipoService service;
	
	public NotaFiscalTipoController(NotaFiscalTipoService service){
		this.service = service;
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NotaFiscalTipo> inserir(@RequestBody NotaFiscalTipo notaFiscalTipo) {
        return ResponseEntity.ok().body(service.salvar(notaFiscalTipo));
    }
}
