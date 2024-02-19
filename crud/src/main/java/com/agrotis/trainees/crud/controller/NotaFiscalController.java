package com.agrotis.trainees.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import java.util.List;

import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@RequestMapping("notas-fiscais")
@RestController
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService service;

    @PostMapping
    public ResponseEntity<NotaFiscalDto> inserir(@Valid @RequestBody NotaFiscalDto dto) throws NotFoundException {
        NotaFiscalDto notaSalva = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(notaSalva);
    }

    @GetMapping
    public ResponseEntity<List<NotaFiscalDto>> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaFiscalDto> atualizar(@PathVariable Integer id, @RequestBody NotaFiscalDto dto)
                    throws NotFoundException {
        return ResponseEntity.ok().body(service.atualizar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().build();
    }
}
