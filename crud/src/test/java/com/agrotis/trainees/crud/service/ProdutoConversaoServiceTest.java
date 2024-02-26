package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

public class ProdutoConversaoServiceTest {

    @Test
    @DisplayName("Teste para o método converterParaDto")
    public void testeConverterDto() {
        Produto entidade = new Produto();
        entidade.setId(1);
        entidade.setNome("Produto");
        entidade.setDescricao("Descrição");
        entidade.setFabricante("Fabricante");
        entidade.setDataFabricacao(LocalDate.of(2023, 5, 10));
        entidade.setDataValidade(LocalDate.of(2024, 5, 10));
        entidade.setEstoque(100);

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setNome("Parceiro");
        entidade.setParceiroNegocio(parceiro);

        ProdutoConversaoService produtoConversaoService = new ProdutoConversaoService();
        ProdutoDto dto = produtoConversaoService.converterParaDto(entidade);

        assertEquals(entidade.getId(), dto.getId());
        assertEquals(entidade.getNome(), dto.getNome());
        assertEquals(entidade.getDescricao(), dto.getDescricao());
        assertEquals(entidade.getFabricante(), dto.getFabricante());
        assertEquals(entidade.getDataFabricacao(), dto.getDataFabricacao());
        assertEquals(entidade.getDataValidade(), dto.getDataValidade());
        assertEquals(entidade.getEstoque(), dto.getEstoque());

        assertEquals(entidade.getParceiroNegocio().getId(), dto.getParceiroNegocio().getId());
        assertEquals(entidade.getParceiroNegocio().getNome(), dto.getParceiroNegocio().getNome());
    }

    @Test
    @DisplayName("Teste para o método converterParaEntidade")
    public void testeConverterEntidade() {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(1);
        dto.setNome("Produto");
        dto.setDescricao("Descrição");
        dto.setFabricante("Fabricante");
        dto.setDataFabricacao(LocalDate.of(2023, 5, 10));
        dto.setDataValidade(LocalDate.of(2024, 5, 10));
        dto.setEstoque(100);

        ParceiroNegocioDto parceiro = new ParceiroNegocioDto();
        parceiro.setId(1);
        parceiro.setNome("Parceiro");
        dto.setParceiroNegocio(parceiro);

        ProdutoConversaoService produtoConversaoService = new ProdutoConversaoService();
        Produto entidade = produtoConversaoService.converterParaEntidade(dto);

        assertEquals(dto.getId(), entidade.getId());
        assertEquals(dto.getNome(), entidade.getNome());
        assertEquals(dto.getDescricao(), entidade.getDescricao());
        assertEquals(dto.getFabricante(), entidade.getFabricante());
        assertEquals(dto.getDataFabricacao(), entidade.getDataFabricacao());
        assertEquals(dto.getDataValidade(), entidade.getDataValidade());
        assertEquals(dto.getEstoque(), entidade.getEstoque());

        assertEquals(dto.getParceiroNegocio().getId(), entidade.getParceiroNegocio().getId());
        assertEquals(dto.getParceiroNegocio().getNome(), entidade.getParceiroNegocio().getNome());
    }

}
