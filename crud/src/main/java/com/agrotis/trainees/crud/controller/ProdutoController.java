package com.agrotis.trainees.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.service.ProdutoService;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ProdutoDto produto) {
        return ResponseEntity.ok(produtoService.salvar(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    /*
     * @GetMapping("/buscarPorNome/{nome}") public ResponseEntity<?>
     * buscarPeloNome(@PathVariable String nome) { return
     * ResponseEntity.ok(produtoService.buscarPeloNome(nome)); }
     */

    @GetMapping("/listarTodos/{listar}")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

}
