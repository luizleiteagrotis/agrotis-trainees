package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItemService.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;

    @Autowired
    public NotaFiscalItemService(NotaFiscalItemRepository repository, ProdutoService produtoService, NotaFiscalService notaFiscalService) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
    }

//    @Transactional
//    public NotaFiscalItem salvar(NotaFiscalItem entidade) {
//        adicionarOuAtualizarItemNotaFiscal(entidade);
//        return repository.save(entidade);
//    }
    
    public NotaFiscalItemDto salvar(NotaFiscalItemDto dto) {
    	NotaFiscalItem entidade = converterParaEntidade(dto);
    	repository.save(entidade);
    	LOG.info("Salvo item {}", entidade.getId());
    	return converterParaDto(entidade);
    }

    public NotaFiscalItemDto buscarPorId(Integer id) throws NotFoundException{
    	NotaFiscalItem entidade = repository.findById(id).orElseThrow(() -> new NotFoundException());
        return converterParaDto(entidade);
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Item da nota fiscal deletado com sucesso");
    }

    public List<NotaFiscalItemDto> listarTodos() {
    	List<NotaFiscalItem> entidades = repository.findAll();
    	return entidades.stream().map(entidade -> converterParaDto(entidade)).collect(Collectors.toList());
    }
    
    public NotaFiscalItem inserir(@Valid NotaFiscalItemDto dto) {
    	NotaFiscalItem entidade = converterParaEntidade(dto);
    	return repository.save(entidade);
    }
    
    public NotaFiscalItemDto atualizar(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = converterParaEntidade(dto);
        return converterParaDto(repository.save(entidade));
    }
    
    public static NotaFiscalItemDto converterParaDto(NotaFiscalItem entidade) {
		NotaFiscalItemDto dto = new NotaFiscalItemDto();
		dto.setId(entidade.getId());
		dto.setNotaFiscal(entidade.getNotaFiscal());
		dto.setProduto(entidade.getProduto());
		dto.setQuantidade(entidade.getQuantidade());
		dto.setPrecoUnitario(entidade.getPrecoUnitario());
		dto.setValorTotal(entidade.getValorTotal()); 
		
		return dto;
	}
    
    public static NotaFiscalItem converterParaEntidade(NotaFiscalItemDto dto) {
		NotaFiscalItem entidade = new NotaFiscalItem();
		entidade.setId(dto.getId());
		entidade.setNotaFiscal(dto.getNotaFiscal());
		entidade.setProduto(dto.getProduto());
		entidade.setQuantidade(dto.getQuantidade());
		entidade.setPrecoUnitario(dto.getPrecoUnitario());
		entidade.setValorTotal(dto.getValorTotal()); 
		
		return entidade;
	}
    
//    public NotaFiscalItem atualizar(NotaFiscalItem entidade) {
//        if (entidade.getId() == null) {
//            throw new CrudException("Obrigatório preencher o id do item da nota fiscal.");
//        }
//        if (StringUtils.isEmpty(entidade.getProduto())) {
//            throw new CrudException("Obrigatório preencher o nome do produto.");
//        }
//        return repository.save(entidade);
//    }
//    
//    public void adicionarOuAtualizarItemNotaFiscal(NotaFiscalItem item) {
//        if (item != null && item.getNotaFiscal() != null && item.getProduto() != null) {
//            atualizarValorTotalNotaFiscal(item);
//            controlarEstoque(item);
//        } else {
//            throw new CrudException("O item da nota fiscal, nota fiscal e produto devem ser fornecidos");
//        }
//    }
//
//    private void atualizarValorTotalNotaFiscal(NotaFiscalItem item) {
//        NotaFiscal notaFiscal = item.getNotaFiscal();
//        double valorTotalItem = calcularValorTotalItem(item);
//        double novoValorTotal = notaFiscal.getValorTotal() + valorTotalItem;
//        notaFiscal.setValorTotal(novoValorTotal);
//        notaFiscalService.salvar(notaFiscal);
//    }
//
//    private double calcularValorTotalItem(NotaFiscalItem item) {
//        return item.getQuantidade() * item.getPrecoUnitario();
//    }
//
//    private void controlarEstoque(NotaFiscalItem item) {
//        Produto produto = item.getProduto();
//        int quantidade = item.getQuantidade();
//
//        if (item.getNotaFiscal().getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
//            if (produto.getEstoque() < quantidade) {
//                throw new CrudException("Estoque insuficiente para o produto: " + produto.getNome());
//            }
//            produto.setEstoque(produto.getEstoque() - quantidade);
//        } else {
//            produto.setEstoque(produto.getEstoque() + quantidade);
//        }
//
//        produtoService.salvar(produto);
//    }
    
    
}