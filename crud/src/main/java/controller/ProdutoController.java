package controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
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

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.ProdutoService;

import dto.ProdutoDto;

@RequestMapping("notas-fiscais/produtos")
@RestController

public class ProdutoController {
    
    private final ProdutoService service;
    
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produto));
        
    }
    @GetMapping("/por-descricao")
    public ResponseEntity<?> buscarPorDescricao(@RequestBody Map<String, String> requestBody) {
        String descricao = requestBody.get("descricao");
        return ResponseEntity.ok().body(service.buscarPorDescricao(descricao));
        
    }
    @PutMapping
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ProdutoDto dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        service.deletarPorId(id);;
        return ResponseEntity.noContent().build();
    }
}
