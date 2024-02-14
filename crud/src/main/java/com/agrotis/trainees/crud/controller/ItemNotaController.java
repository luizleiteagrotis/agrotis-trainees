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

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.service.ItemNotaService;

@RestController
@RequestMapping("notas-fiscais/itens")
public class ItemNotaController {
	
	private ItemNotaService itemService;
	
	@Autowired
	public ItemNotaController(ItemNotaService itemService) {
		this.itemService = itemService;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody ItemCadastroDto cadastroDto, UriComponentsBuilder uriBuilder) {
		ItemRetornoDto retornoDto = itemService.salvar(cadastroDto);
		URI uri = uriBuilder.path("/itens/{id}").buildAndExpand(retornoDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemRetornoDto> buscarPorId(@PathVariable(name = "id") Long id) {
		ItemRetornoDto retornoDto = itemService.buscar(id);
		return ResponseEntity.ok(retornoDto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ItemRetornoDto>> listarTodos(Pageable pageable) {
		Page<ItemRetornoDto> itens = itemService.buscarTodos(pageable);
		return ResponseEntity.ok(itens);
	}
	
	@PutMapping
	public ResponseEntity<ItemRetornoDto> atualizar(@RequestBody ItemAtualizacaoDto atualizacaoDto) {
		ItemRetornoDto retornoDto = itemService.atualizar(atualizacaoDto);
		return ResponseEntity.ok(retornoDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable(name = "id") Long id) {
		itemService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
