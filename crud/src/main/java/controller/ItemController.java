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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.service.ItemNotaService;

import dto.ItemNotaDto;

@RestController
@RequestMapping
public class ItemController {
    
    private final  ItemNotaService service;
    
    @Autowired
    public ItemController(ItemNotaService service) {
        this.service = service;
        
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ItemNotaDto itemNota) {
        ItemNota itemSalvo = service.salvar(itemNota);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemSalvo.getId()).toUri();
        
        return ResponseEntity.created(uri).body(itemSalvo);
        
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPoiId(id));
        
    }
    @GetMapping
    public ResponseEntity<?> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
        
    }
    @PutMapping("{id}")
    public ResponseEntity<?> atualizar1(@PathVariable Integer id, @RequestBody ItemNotaDto dto) {
        return ResponseEntity.ok(service.atualizar1(id, dto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
   
}






