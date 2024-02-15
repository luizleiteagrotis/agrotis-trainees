package com.agrotis.trainees.crud.controller;

import java.net.URI;

import javax.validation.Valid;

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

import com.agrotis.trainees.crud.dto.cabecalho.CabecalhoAtualizacaoDto;
import com.agrotis.trainees.crud.dto.cabecalho.CabecalhoCadastroDto;
import com.agrotis.trainees.crud.dto.cabecalho.CabecalhoRetornoDto;
import com.agrotis.trainees.crud.service.CabecalhoNotaService;

@RestController
@RequestMapping("notas-fiscais/cabecalhos")
public class CabecalhoController {
	
	private CabecalhoNotaService cabecalhoService;

	@Autowired
	public CabecalhoController(CabecalhoNotaService cabecalhoService) {
		this.cabecalhoService = cabecalhoService;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@Valid @RequestBody CabecalhoCadastroDto cadastroDto, UriComponentsBuilder uriBuilder) {
		CabecalhoRetornoDto retornoDto = cabecalhoService.salvar(cadastroDto);
		URI uri = uriBuilder.path("/cabecalhos/{id}").buildAndExpand(retornoDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("{id}") 
	public ResponseEntity<CabecalhoRetornoDto> buscarPorId(@PathVariable(name = "id") Long id) {
		CabecalhoRetornoDto retornoDto = cabecalhoService.buscar(id);
		return ResponseEntity.ok(retornoDto);
	}
	
	@GetMapping
	public ResponseEntity<Page<CabecalhoRetornoDto>> listarTodos(Pageable pageable) {
		Page<CabecalhoRetornoDto> retornoDto = cabecalhoService.listarTodos(pageable);
		return ResponseEntity.ok(retornoDto);
	}
	
	@PutMapping
	public ResponseEntity<CabecalhoRetornoDto> atualizar(@Valid @RequestBody CabecalhoAtualizacaoDto atualizacaoDto) {
		CabecalhoRetornoDto retornoDto = cabecalhoService.atualizar(atualizacaoDto);
		return ResponseEntity.ok(retornoDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletar(@PathVariable(name = "id") Long id) {
		cabecalhoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
