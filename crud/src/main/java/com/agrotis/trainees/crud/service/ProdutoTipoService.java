package com.agrotis.trainees.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoTipoRepository;

@Service
public class ProdutoTipoService {

    private final ProdutoTipoRepository produtoTipoRepository;

    @Autowired
    private ParceiroNegocioTipoService parceiroNegocioService;

    public ProdutoTipoService(ProdutoTipoRepository produtoTipoRepository, ParceiroNegocioTipoService parceiroNegocioService) {
        this.produtoTipoRepository = produtoTipoRepository;
        this.parceiroNegocioService = parceiroNegocioService;
    }

    @Transactional
    public ProdutoDto inserir(ProdutoDto dto) {
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

    public ProdutoDto atualizar(ProdutoDto dto) {
        Produto entidade = produtoTipoRepository.findById(dto.getId()).orElse(null);
        if (entidade != null) {
            entidade.setDescricao(dto.getDescricao());
            entidade.setEstoque(dto.getEstoque());
            entidade.setFabricante(dto.getFabricante());
            entidade.setDataFabricacao(dto.getDataFabricacao());
            entidade.setDataValidade(dto.getDataValidade());
            entidade = produtoTipoRepository.save(entidade);
            return converterParaDto(entidade);
        } else {
            return null;
        }

    }

    public ProdutoDto converterParaDto(Produto entidade) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setDescricao(entidade.getDescricao());
        dto.setEstoque(entidade.getEstoque());

        ParceiroNegocioDto fabricanteDto = parceiroNegocioService.converterParaDto(entidade.getFabricante());
        dto.setFabricante(fabricanteDto);

        dto.setDataFabricacao(entidade.getDataFabricacao());
        dto.setDataValidade(entidade.getDataValidade());
        return dto;
    }

    public Produto converterParaEntidade(ProdutoDto dto) {
        Produto entidade = new Produto();
        entidade.setId(dto.getId());
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setId(dto.getFabricante().getId());
        parceiroNegocio.setNome(dto.getFabricante().getNome());
        parceiroNegocio.setInscricaoFiscal(dto.getFabricante().getInscricaoFiscal());
        parceiroNegocio.setEndereco(dto.getFabricante().getEndereco());
        parceiroNegocio.setTelefone(dto.getFabricante().getTelefone());

        entidade.setFabricante(parceiroNegocio);

        entidade.setDescricao(dto.getDescricao());
        entidade.setEstoque(dto.getEstoque());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());

        return entidade;
    }
}