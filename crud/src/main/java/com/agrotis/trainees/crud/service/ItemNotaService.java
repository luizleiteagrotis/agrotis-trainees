package com.agrotis.trainees.crud.service;

import java.lang.System.Logger;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.NotaFiscalC;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ItemNotaRepository;




@Service

public class ItemNotaService {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(ItemNotaService.class);
    
    private final ItemNotaRepository repository;
    private final ProdutoService produtoService;
    
    public  ItemNotaService(ItemNotaRepository repository, ProdutoService produtoService) {
        this.repository = repository;
        this.produtoService = produtoService;
                        
       
        }
    
    public ItemNota salvar(ItemNota entidade) {
        calcularValorTotal(entidade);
        atualizarEstoque(entidade);
        return repository.save(entidade);
    }
    
    public List<ItemNota> buscarTodos() {
        return repository.findAll();
        
                        
    }

    public ItemNota buscarPoiId(Integer id) {
        return (ItemNota) repository.findAll();
        
    }
    
    public Optional<Object> atualizar(Integer id, ItemNota ItemNota) {
        return repository.findById(id).map(itemNotaExistente -> {
            itemNotaExistente.setNotaFiscal(ItemNota.getNotaFiscal());
            itemNotaExistente.setPrecoUnitario(ItemNota.getPrecoUnitario());
            itemNotaExistente.setProduto(ItemNota.getProduto());
            itemNotaExistente.setQuantidade(ItemNota.getQuantidade());
            itemNotaExistente.setValorTotal(ItemNota.getValorTotal());
            return repository.save(itemNotaExistente);
        });
    }

    public void calcularValorTotal(ItemNota ItemNota1) {
        Integer quantidade = ItemNota1.getQuantidade();
        Double precoUnitario = ItemNota1.getPrecoUnitario();
        if (quantidade != null && precoUnitario != null) {
            Double valorTotal = quantidade * precoUnitario;
            ItemNota1.setValorTotal(valorTotal);
            
        }
        
    }
    public void deletarPorId(Integer id) {
        repository.findById(id).map(entidade -> {
            repository.deleteById(id);
            ((org.slf4j.Logger) LOG).info("Deletado");
            return entidade;
        });

      
        }
    public void atualizarEstoque(ItemNota itemNota) {
        Produto produto = itemNota.getProduto();
        Integer quantidade = itemNota.getQuantidade();
        Integer quantidadeProduto = itemNota.getProduto().getQuantidade();

      
        produtoService.salvar(produto);
    }
}
 
  




