package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@ExtendWith(MockitoExtension.class)
public class ParceiroNegocioTest {

    private final String NOME = "AgroFertil Ltda";
    private final String INSCRICAO_FISCAL = "154474-114";
    private final String ENDERECO = "Rua dos Calvos, 71";
    private final String TELEFONE = "4335256025";

    @Mock
    private ParceiroNegocioRepository repository;

    @InjectMocks
    private ParceiroNegocioService service;

    @Test
    public void inserirParceiroNegocio() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        ParceiroNegocioDto result = service.salvar(dto);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getId(), result.getId());
        verify(repository).save(any(ParceiroNegocio.class));
    }

    @Test
    public void quandoCriadoRetornaSucesso() {
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

    }
    // @Test
    // public void inserirParceiroNegocioComNomeNulo() {
    // ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
    //
    // // Configura o comportamento do repositório para retornar o
    // parceiroNegocio
    // when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);
    //
    // // Configura o DTO com o nome nulo
    // ParceiroNegocioDto dto = criarParceiroNegocioDto();
    // dto.setNome(null);
    //
    // // Testa se a exceção é propagada corretamente ao tentar salvar o DTO
    // assertThrows(ConstraintViolationException.class, () -> {
    // service.salvar(dto);
    // });
    // }

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
        dto.setNome(NOME);
        dto.setEndereco(ENDERECO);
        dto.setInscricaoFiscal(INSCRICAO_FISCAL);
        dto.setTelefone(TELEFONE);
        return dto;
    }
}
