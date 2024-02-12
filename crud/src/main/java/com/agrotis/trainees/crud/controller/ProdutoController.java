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

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.service.ProdutoService;

@RequestMapping("produtos")
@RestController
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        super();
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produtoDto));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<?> buscarPorInscricao(@PathVariable String descricao) {
        return ResponseEntity.ok().body(service.buscarPorDescricao(descricao));
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.ok().body(service.atualizar(produtoDto));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }

}
