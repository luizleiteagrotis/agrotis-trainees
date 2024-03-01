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

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.exception.NotaFiscalExcecao;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@RequestMapping("notas-fiscais")
@RestController
public class NotaFiscalController {

    private final NotaFiscalService service;

    public NotaFiscalController(NotaFiscalService service) {
        super();
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody NotaFiscalDto notaFiscalDto) throws NotaFiscalExcecao {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(notaFiscalDto));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() throws NotaFiscalExcecao {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> buscarPorTipoNotaFiscal(@PathVariable String tipo) throws NotaFiscalExcecao {
        return ResponseEntity.ok().body(service.buscarPorTipoNotaFiscal(tipo));
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<?> buscarPorNumero(@PathVariable int numero) throws NotaFiscalExcecao {
        return ResponseEntity.ok().body(service.buscarPorNumero(numero));
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<?> buscarPorData(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data)
                    throws NotaFiscalExcecao {
        return ResponseEntity.ok().body(service.buscarPorData(data));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@RequestBody NotaFiscalDto notaFiscalDto, @PathVariable int id) throws NotaFiscalExcecao {
        return ResponseEntity.ok().body(service.atualizar(notaFiscalDto, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable int id) {
        service.deletarPorId(id);
        return ResponseEntity.ok().body(null);
    }

}
