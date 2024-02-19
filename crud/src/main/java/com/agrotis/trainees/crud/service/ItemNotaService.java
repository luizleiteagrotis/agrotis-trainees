package com.agrotis.trainees.crud.service;

import java.lang.System.Logger;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.NotaFiscalC;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ItemNotaRepository;

import dto.CabecalhoDto;
import dto.ItemDto;
import utilidades.DtoUtilidades;




@Service

public class ItemNotaService {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(ItemNotaService.class);
    
    private final ItemNotaRepository repository;
    private final ProdutoService produtoService;
    private final NotaFiscalC notaFiscalCService;
    private final ParceiroNegocioService parceiroNegocioService;
    
    
    public  ItemNotaService(ItemNotaRepository repository, ProdutoService produtoService, NotaFiscalC notaFiscalCService, ParceiroNegocioService parceiroNegocioService) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.notaFiscalCService = notaFiscalCService;
        this.parceiroNegocioService = parceiroNegocioService;
                        
       
        }
    
    public ItemNota salvar(ItemDto dto) {
        ItemNota entidade = DtoUtilidades.converteParaEntidade(dto);
        
        NotaFiscalC notaFiscalC = entidade.getNotaFiscalC();
        CabecalhoDto cabecalhoDtoSalvo = NotaFiscalC.salvar(DtoUtilidades.converteParaDto(notaFiscalC));
        entidade.setNotaFiscalC(DtoUtilidades.converteParaEntidade(cabecalhoDtoSalvo));
        
        calcularValorTotal(entidade);
        atualizarEstoque(entidade);
        
        entidade = repository.save(entidade);
        
        return repository.save(entidade);
    }
    
    public List<ItemNota> buscarTodos() {
        return repository.findAll();
        
                        
    }

    public ItemNota buscarPoiId(Integer id) {
        return (ItemNota) repository.findAll();
        
    }
    @Transactional
    public ItemDto atualizar1(Integer id, ItemDto notaFiscalItemDto) {
        ItemNota itemNotaExistente = repository.findById(id)
                .orElseThrow();

        atualizarItemNota(itemNotaExistente, notaFiscalItemDto);

        ItemNota itemNotaAtualizada = repository.save(itemNotaExistente);
        return DtoUtilidades.converteParaDto(itemNotaAtualizada);
        
        
    }
    
    private void atualizarItemNota(ItemNota itemNota, ItemDto notaFiscalItemDto) {
        NotaFiscalC notaFiscalC = notaFiscalItemDto.getNotaFiscalC();
        itemNota.setNotaFiscalC(notaFiscalC);
        itemNota.setPrecoUnitario(notaFiscalItemDto.getPrecoUnitario());
        itemNota.setProduto(notaFiscalItemDto.getProduto());
        itemNota.setQuantidade(notaFiscalItemDto.getQuantidade());
        itemNota.setValorTotal(notaFiscalItemDto.getValorTotal());
    
        
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

    private void adicionarValorTotalCabecalho(ItemNota item) {
     NotaFiscalC cabecalho = item.getNotaFiscalC();
     Double valorTotalItem = item.getValorTotal();
     cabecalho.setValorTotal(valorTotalItem);
    }

    
}
 
  




