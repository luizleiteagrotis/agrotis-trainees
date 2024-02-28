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

import java.math.BigDecimal;

import com.agrotis.trainees.crud.dto.ItemNotaFiscalDto;
import com.agrotis.trainees.crud.exception.ControleEstoqueException;
import com.agrotis.trainees.crud.exception.ItemNotaFiscalExcecao;
import com.agrotis.trainees.crud.service.ItemNotaFiscalService;

@RequestMapping("notas-fiscais/itens")
@RestController
public class ItemNotaFiscalController {
    ItemNotaFiscalService service;

    public ItemNotaFiscalController(ItemNotaFiscalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody ItemNotaFiscalDto itemNotaFiscalDto)
                    throws ItemNotaFiscalExcecao, ControleEstoqueException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(itemNotaFiscalDto));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @GetMapping("/quantidade/{quantidade}")
    public ResponseEntity<?> buscarPorQuantidade(@PathVariable BigDecimal quantidade) {
        return ResponseEntity.ok().body(service.buscarPorQuantidade(quantidade));
    }

    @GetMapping("/preco/{preco}")
    public ResponseEntity<?> buscarPorPreco(@PathVariable BigDecimal preco) {
        return ResponseEntity.ok().body(service.buscarPorPreco(preco));
    }

    @GetMapping("/valor-total/{valorTotal}")
    public ResponseEntity<?> buscarPorValorTotal(@PathVariable BigDecimal valorTotal) {
        return ResponseEntity.ok().body(service.buscarPorPreco(valorTotal));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizarPorId(@PathVariable int id, @RequestBody ItemNotaFiscalDto itemNotaFiscalDto) {
        return ResponseEntity.ok().body(service.atualizar(itemNotaFiscalDto, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable int id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }
}
