package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

public class ParceiroNegocioTest {

    private final Integer ID_INSERIDO = 1;
    private final String NOME = "AgroFertil Ltda";
    private final String INSCRICAO_FISCAL = "154474-114";
    private final String ENDERECO = "Rua dos Calvos, 71";
    private final String TELEFONE = "4335256025";

    @Mock
    private ParceiroNegocioRepository repository;

    @InjectMocks
    private ParceiroNegocioService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void inserirParceiroNegocio() {
        // Arrange
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        // Act
        ParceiroNegocioDto result = service.salvar(dto);

        // Assert
        assertNotNull(result);
    }

    private ParceiroNegocio criarParceiroNegocio() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome(NOME);
        entidade.setEndereco(ENDERECO);
        entidade.setInscricaoFiscal(INSCRICAO_FISCAL);
        entidade.setTelefone(TELEFONE);
        return entidade;
    }

    private ParceiroNegocioDto criarParceiroNegocioDto() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(ID_INSERIDO);
        dto.setNome(NOME);
        dto.setEndereco(ENDERECO);
        dto.setInscricaoFiscal(INSCRICAO_FISCAL);
        dto.setTelefone(TELEFONE);
        return dto;
    }
}
