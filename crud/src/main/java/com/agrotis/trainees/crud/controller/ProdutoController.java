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

import com.agrotis.trainees.crud.dto.produto.ProdutoAtualizacaoDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private ProdutoService produtoService;

	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody ProdutoCadastroDto dto, UriComponentsBuilder uriBuilder) {
		ProdutoRetornoDto listagemDto = produtoService.salvar(dto);
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(listagemDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoRetornoDto> buscarPorId(@PathVariable(name = "id") Long id) {
		ProdutoRetornoDto dto = produtoService.buscarPor(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoRetornoDto>> listarTodos(Pageable pageable) {
		Page<ProdutoRetornoDto> pagina = produtoService.listarTodos(pageable);
		return ResponseEntity.ok(pagina);
	}
}
