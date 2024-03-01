package com.agrotis.trainees.crud.service.parceiro.cadastro;

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
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.mapper.parceiro.ParceiroMapper;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;

@ExtendWith(MockitoExtension.class)
class ParceiroNegocioCadastroServiceImplTest {

	@Mock
	private ParceiroNegocioCadastroRn cadastroRn1;
	
	@Mock
	private ParceiroNegocioCadastroRn cadastroRn2;
	
	@Mock
	private ParceiroMapper parceiroMapper;
	
	@Mock
	private ParceiroRepository parceiroRepository;
	
	private List<ParceiroNegocioCadastroRn> cadastroRns;
	
	ParceiroNegocioCadastroServiceImpl cadastroServiceImpl;
	
	private ParceiroCadastroDto cadastroDto;
	private ParceiroNegocio parceiroNegocio;
	private ParceiroRetornoDto retornoDtoEsperado;
	
	@BeforeEach
	public void setUp() {
		cadastroRns = new ArrayList<>();
		cadastroServiceImpl = new ParceiroNegocioCadastroServiceImpl(cadastroRns, parceiroMapper, parceiroRepository);
		cadastroDto = new ParceiroCadastroDto();
		parceiroNegocio = new ParceiroNegocio();
		retornoDtoEsperado = new ParceiroRetornoDto();
	}
	
	@Test
	public void deveExecutarTodasAsRnsQuandoConverterCadastroDtoParaParceiro() {
		cadastroRns.add(cadastroRn1);
		cadastroRns.add(cadastroRn2);
		when(parceiroMapper.converterParaEntidade(cadastroDto)).thenReturn(parceiroNegocio);
		when(cadastroRn1.operarSobre(parceiroNegocio)).thenReturn(parceiroNegocio);
		when(cadastroRn2.operarSobre(parceiroNegocio)).thenReturn(parceiroNegocio);
		
		cadastroServiceImpl.cadastrar(cadastroDto);
		
		verify(cadastroRn1, times(1)).operarSobre(parceiroNegocio);
		verify(cadastroRn2, times(1)).operarSobre(parceiroNegocio);
	}
	
	@Test
	public void deveNaoExecutarOutrasRnsQuandoUmaDelasFalhar() {
		cadastroRns.add(cadastroRn1);
		cadastroRns.add(cadastroRn2);
		when(parceiroMapper.converterParaEntidade(cadastroDto)).thenReturn(parceiroNegocio);
		when(cadastroRn1.operarSobre(parceiroNegocio)).thenThrow(ParceiroNegocioCadastroRnException.class);
		
		try {
			cadastroServiceImpl.cadastrar(cadastroDto);
		} catch (ParceiroNegocioCadastroRnException ignored) {}
		
		verify(cadastroRn2, never()).operarSobre(parceiroNegocio);
	}
	
	@Test
	public void deveSalvarParceiroQuandoTerminarDeExecutarAsRns() {
		cadastroRns.add(cadastroRn1);
		when(parceiroMapper.converterParaEntidade(cadastroDto)).thenReturn(parceiroNegocio);
		when(cadastroRn1.operarSobre(parceiroNegocio)).thenReturn(parceiroNegocio);
		
		cadastroServiceImpl.cadastrar(cadastroDto);
		
		InOrder inOrder = inOrder(cadastroRn1, parceiroRepository);
		inOrder.verify(cadastroRn1, times(1)).operarSobre(parceiroNegocio);
		inOrder.verify(parceiroRepository, times(1)).salvar(parceiroNegocio);
	}
	
	@Test
	public void deveNaoSalvarParceiroQuandoUmaRnFalhar() {
		cadastroRns.add(cadastroRn1);
		when(parceiroMapper.converterParaEntidade(cadastroDto)).thenReturn(parceiroNegocio);
		when(cadastroRn1.operarSobre(parceiroNegocio)).thenThrow(ParceiroNegocioCadastroRnException.class);
		
		try {
			cadastroServiceImpl.cadastrar(cadastroDto);
		} catch (ParceiroNegocioCadastroRnException ignored) {}
		
		verify(parceiroRepository, never()).salvar(parceiroNegocio);
	}
	
	@Test
	public void deveRetornarRetornoDtoQuandoTerminarDeExecutarTodasAsRnsESalvar() {
		cadastroRns.add(cadastroRn1);
		when(parceiroMapper.converterParaEntidade(cadastroDto)).thenReturn(parceiroNegocio);
		when(cadastroRn1.operarSobre(parceiroNegocio)).thenReturn(parceiroNegocio);
		when(parceiroRepository.salvar(parceiroNegocio)).thenReturn(parceiroNegocio);
		when(parceiroMapper.converterParaDto(parceiroNegocio)).thenReturn(retornoDtoEsperado);
		
		ParceiroRetornoDto retornoDto = cadastroServiceImpl.cadastrar(cadastroDto);
		
		verify(parceiroMapper, times(1)).converterParaDto(parceiroNegocio);
		assertThat(retornoDto, is(sameInstance(retornoDtoEsperado)));
	}
}
