package com.agrotis.trainees.crud.service.parceiro.busca;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.mapper.parceiro.ParceiroMapper;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;

@ExtendWith(MockitoExtension.class)
class ParceiroNegocioBuscaServiceImplTest {

	@Mock
	private ParceiroRepository parceiroRepository;
	
	@Mock
	private ParceiroMapper parceiroMapper;
	
	@InjectMocks
	private ParceiroNegocioBuscaServiceImpl buscarServiceImpl;
	
	@Test
	public void deveRetornarRetornoDtoQuandoEncontrarIdParceiro() {
		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
		final long ID_PARCEIRO = 1L;
		ParceiroRetornoDto retornoEsperado = new ParceiroRetornoDto();
		when(parceiroRepository.buscarPor(ID_PARCEIRO)).thenReturn(parceiroNegocio);
		when(parceiroMapper.converterParaDto(parceiroNegocio)).thenReturn(retornoEsperado);
		
		ParceiroRetornoDto retornoDto = buscarServiceImpl.buscarPor(ID_PARCEIRO);
		
		assertThat(retornoDto, is(sameInstance(retornoEsperado)));
	}
}
