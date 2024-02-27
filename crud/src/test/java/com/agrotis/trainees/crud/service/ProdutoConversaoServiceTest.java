package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
        entidade.setDescricao("Descrição");
        entidade.setDataFabricacao(LocalDate.of(2023, 5, 10));
        entidade.setDataValidade(LocalDate.of(2024, 5, 10));
        entidade.setEstoque(100);
        entidade.setCustoMedio(BigDecimal.valueOf(50.00));

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setNome("Parceiro");
        entidade.setFabricante(parceiro);

        ProdutoConversaoService produtoConversaoService = new ProdutoConversaoService();
        ProdutoDto dto = produtoConversaoService.converterParaDto(entidade);

        assertEquals(entidade.getId(), dto.getId());
        assertEquals(entidade.getDescricao(), dto.getDescricao());

        assertEquals(entidade.getFabricante().getId(), dto.getFabricante().getId());
        assertEquals(entidade.getFabricante().getNome(), dto.getFabricante().getNome());

        assertEquals(entidade.getDataFabricacao(), dto.getDataFabricacao());
        assertEquals(entidade.getDataValidade(), dto.getDataValidade());
        assertEquals(entidade.getEstoque(), dto.getEstoque());
        assertEquals(entidade.getCustoMedio(), dto.getCustoMedio());
    }

    @Test
    @DisplayName("Teste para o método converterParaEntidade")
    public void testeConverterEntidade() {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(1);
        dto.setDescricao("Descrição");
        dto.setDataFabricacao(LocalDate.of(2023, 5, 10));
        dto.setDataValidade(LocalDate.of(2024, 5, 10));
        dto.setEstoque(100);
        dto.setCustoMedio(BigDecimal.valueOf(50.00));

        ParceiroNegocioDto parceiro = new ParceiroNegocioDto();
        parceiro.setId(1);
        parceiro.setNome("Parceiro");
        dto.setFabricante(parceiro);

        ProdutoConversaoService produtoConversaoService = new ProdutoConversaoService();
        Produto entidade = produtoConversaoService.converterParaEntidade(dto);

        assertEquals(dto.getId(), entidade.getId());
        assertEquals(dto.getDescricao(), entidade.getDescricao());
        assertEquals(dto.getFabricante().getId(), entidade.getFabricante().getId());
        assertEquals(dto.getFabricante().getNome(), entidade.getFabricante().getNome());
        assertEquals(dto.getDataFabricacao(), entidade.getDataFabricacao());
        assertEquals(dto.getDataValidade(), entidade.getDataValidade());
        assertEquals(dto.getEstoque(), entidade.getEstoque());
        assertEquals(dto.getCustoMedio(), entidade.getCustoMedio());
    }
}
