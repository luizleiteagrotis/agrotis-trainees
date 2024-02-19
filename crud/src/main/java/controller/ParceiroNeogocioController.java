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

import com.agrotis.trainees.crud.service.ParceiroNegocioService;

import dto.ParceiroNegocioDto;

@RestController
@RequestMapping("/parceiros-negocio")
public class ParceiroNeogocioController {

    private ParceiroNegocioService service = null;

    @Autowired
    public void ParceiroNegocioController (ParceiroNegocioService service) {
        this.service = service; 
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ParceiroNegocioDto parceiroNegocio) {
        ParceiroNegocioDto parceiroNegocioSalvo = service.salvar(parceiroNegocio);

        URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(parceiroNegocioSalvo.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(parceiroNegocioSalvo);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    
    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String nome){
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }
    
    @GetMapping
    public ResponseEntity<?> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ParceiroNegocioDto dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }
    
}
    
    
    
    
    
    
    