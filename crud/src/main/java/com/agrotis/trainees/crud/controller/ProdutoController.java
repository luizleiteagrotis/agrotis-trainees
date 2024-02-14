package com.agrotis.trainees.crud.controller;

import org.springframework.format.annotation.DateTimeFormat;
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

import java.time.LocalDate;

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
    public ResponseEntity<?> salvar(@RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produtoDto));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    // @GetMapping("/fabricante")
    // public ResponseEntity<?> buscarPorFabricante(@RequestBodyParceiroNegocio
    // parceiro) {
    // return ResponseEntity.ok().body(service.buscarPorFornecedor(parceiro));
    // }

    @GetMapping("/data-fabricacao/{dataFabricacao}")
    public ResponseEntity<?> buscarPorDataFabricacao(
                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFabricacao) {
        return ResponseEntity.ok().body(service.buscarPorDataFabricacao(dataFabricacao));
    }

    @GetMapping("/data-validade/{dataValidade}")
    public ResponseEntity<?> buscarPorDataValidade(
                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataValidade) {
        return ResponseEntity.ok().body(service.buscarPorDataValidade(dataValidade));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(service.buscarPorNome(nome));
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.ok().body(service.atualizar(produtoDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable int id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }

}
