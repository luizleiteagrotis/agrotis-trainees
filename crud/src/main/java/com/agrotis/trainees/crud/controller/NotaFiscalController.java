package com.agrotis.trainees.crud.controller;

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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@RestController
@RequestMapping("notas-fiscais")
public class NotaFiscalController {

    private NotaFiscalService notaFiscalService;

    @Autowired
    public NotaFiscalController(NotaFiscalService notaFiscalService) {
        this.notaFiscalService = notaFiscalService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody NotaFiscalDto cadastroDto, UriComponentsBuilder uriBuilder) {
        NotaFiscal notaFiscal = notaFiscalService.salvar(cadastroDto);
        URI uri = uriBuilder.path("/notas-fiscais/{id}").buildAndExpand(notaFiscal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscal> buscarPorId(@PathVariable(name = "id") Integer id) {
        NotaFiscal notaFiscal = notaFiscalService.buscarPorId(id);
        return ResponseEntity.ok(notaFiscal);
    }

    @GetMapping
    public ResponseEntity<List<NotaFiscal>> listarTodos() {
        List<NotaFiscal> notasFiscais = notaFiscalService.listarTodos();
        return ResponseEntity.ok(notasFiscais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaFiscal> atualizar(@PathVariable(name = "id") Integer id, @RequestBody NotaFiscal cadastroDto) {
        NotaFiscal notaFiscalAtualizada = notaFiscalService.atualizar(id, cadastroDto);
        return ResponseEntity.ok(notaFiscalAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable(name = "id") Integer id) {
        notaFiscalService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
