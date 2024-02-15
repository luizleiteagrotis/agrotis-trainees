package controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agrotis.trainees.crud.service.NotaFiscalCService;

import dto.CabecalhoDto;

@RestController
public class CabecalhoNotaController {

    private final NotaFiscalCService service;
    
    @Autowired
    public CabecalhoNotaController(NotaFiscalCService service) {
        this.service = service;
    }
 
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CabecalhoDto cabecalho) {
        CabecalhoDto cabecalhoSalvo = service.salvar(cabecalho);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cabecalhoSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(cabecalhoSalvo);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    @GetMapping
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody CabecalhoDto dto){
    return ResponseEntity.ok(service.atualizar(id, dto));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
    service.deleterPorId(id);
    return ResponseEntity.noContent().build();
    }
    
}
    

