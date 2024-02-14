package com.agrotis.trainees.crud.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@RestController
@RequestMapping("/parceiros")
public class ParceiroNegocioController {

	private ParceiroNegocioService parceiroService;
	
	@Autowired
	public ParceiroNegocioController(ParceiroNegocioService parceiroService) {
		this.parceiroService = parceiroService;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody ParceiroCadastroDto dto, UriComponentsBuilder uriBuilder) {
		ParceiroRetornoDto listagemDto = parceiroService.salvar(dto);
		URI uri = uriBuilder.path("/parceiros/{id}").buildAndExpand(listagemDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
