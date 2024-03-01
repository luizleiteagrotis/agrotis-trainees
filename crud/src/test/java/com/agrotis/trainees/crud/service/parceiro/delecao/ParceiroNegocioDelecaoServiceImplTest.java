package com.agrotis.trainees.crud.service.parceiro.delecao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapperException;

@ExtendWith(MockitoExtension.class)
class ParceiroNegocioDelecaoServiceImplTest {

	@Mock
	private ParceiroRepository parceiroRepository;
	
	@InjectMocks
	private ParceiroNegocioDelecaoServiceImpl delecaoService;

	private final Long ID_PARCEIRO = 1L;
	
	@Test
	public void deveDeletarParceiroDeNegocioQuandoEleNaoEstaAssociadoANenhumItem() {
		delecaoService.deletarPor(ID_PARCEIRO);
		
		verify(parceiroRepository, times(1)).deletar(ID_PARCEIRO);
	}
	
	@Test
	public void deveLancarExceptionQuandoParceiroEstaAssociadoComOutraEntidade() {
		doThrow(JpaRepositoryWrapperException.class).when(parceiroRepository).deletar(ID_PARCEIRO);
		
		assertThrows(JpaRepositoryWrapperException.class, () -> {
			delecaoService.deletarPor(ID_PARCEIRO);
		});
	}
}
