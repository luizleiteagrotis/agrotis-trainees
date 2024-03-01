package com.agrotis.trainees.crud.service.parceiro.atualizacao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.mapper.parceiro.ParceiroMapper;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.service.parceiro.cadastro.ParceiroNegocioCadastroRnException;

@ExtendWith(MockitoExtension.class)
class ParceiroNegocioAtualizacaoServiceImplTest {

	@Mock
	private AtualizadorParceiroRn atualizadorParceiroRn;
	
	@Mock
	private ParceiroRepository parceiroRepository;
	
	@Mock
	private ParceiroMapper parceiroMapper;
	
	@InjectMocks
	private ParceiroNegocioAtualizacaoServiceImpl atualizacaoServiceImpl;
	
	private ParceiroAtualizacaoDto atualizacaoDto;
	private ParceiroNegocio parceiro;
	private ParceiroRetornoDto retornoDto;
	
	@BeforeEach
	public void setUp() {
		atualizacaoDto = new ParceiroAtualizacaoDto();
		parceiro = new ParceiroNegocio();
		retornoDto = new ParceiroRetornoDto();
	}
	
	@Test
	public void deveSalvarParceiroQuandoTerminarDeAtualizarParceiro() {
		when(atualizadorParceiroRn.operarSobre(atualizacaoDto)).thenReturn(parceiro);
		when(parceiroRepository.salvar(parceiro)).thenReturn(parceiro);
		when(parceiroMapper.converterParaDto(parceiro)).thenReturn(retornoDto);
		
		atualizacaoServiceImpl.atualizar(atualizacaoDto);
		
		InOrder inOrder = inOrder(atualizadorParceiroRn, parceiroRepository);
		inOrder.verify(atualizadorParceiroRn, times(1)).operarSobre(atualizacaoDto);
		inOrder.verify(parceiroRepository, times(1)).salvar(parceiro);
	}
}
