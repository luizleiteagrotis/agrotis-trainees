package com.agrotis.trainees.crud.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.service.ProdutoService;
import com.agrotis.trainees.crud.service.impl.ProdutoServiceImpl;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private final ProdutoService service;
    
    @Autowired
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ProdutoDto produto) {
        ProdutoDto produtoSalvo = service.salvar(produto);

        URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(produtoSalvo.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(produtoSalvo);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscaPeloId(id));         
    }
    
    @GetMapping
    public ResponseEntity<?> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ProdutoDto dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
