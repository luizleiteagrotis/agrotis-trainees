// ProdutoService.java
package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoTipoRepository;

@Service
public class ProdutoTipoService {

    private final ProdutoTipoRepository produtoTipoRepository;

    public ProdutoTipoService(ProdutoTipoRepository produtoTipoRepository) {
        this.produtoTipoRepository = produtoTipoRepository;
    }

    @Transactional
    public ProdutoDto salvar(ProdutoDto dto) {
        Produto entidade = converterParaEntidade(dto);
        Produto savedProduto = produtoTipoRepository.save(entidade);
        return converterParaDto(savedProduto);
    }

    public ProdutoDto buscarPorId(Integer id) {
        Produto entidade = produtoTipoRepository.findById(id).orElse(null);
        return converterParaDto(entidade);
    }

    public List<ProdutoDto> listarTodos() {
        List<Produto> entidades = produtoTipoRepository.findAll();
        return entidades.stream().map(this::converterParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        produtoTipoRepository.deleteById(id);
    }

    public ProdutoDto converterParaDto(Produto entidade) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setDescricao(entidade.getDescricao());
        dto.setEstoque(entidade.getEstoque());
        dto.setFabricante(entidade.getFabricante());
        dto.setDataFabricacao(entidade.getDataFabricacao());
        dto.setDataValidade(entidade.getDataValidade());
        return dto;
    }

    public Produto converterParaEntidade(ProdutoDto dto) {
        Produto entidade = new Produto();
        entidade.setId(dto.getId(), null);
        entidade.setDescricao(dto.getDescricao());
        entidade.setEstoque(dto.getEstoque());
        entidade.setFabricante(dto.getFabricante());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());
        return entidade;
    }
}
