package com.agrotis.trainees.crud.service.produto.delecao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapperException;

@ExtendWith(MockitoExtension.class)
class ProdutoDelecaoServiceImplTest {

	@Mock
	private ProdutoRepository produtoRepository;
	
	@InjectMocks
	private ProdutoDelecaoServiceImpl delecaoServiceImpl;

	private final Long ID_PARCEIRO = 1L;
	
	@Test
	public void deveDeletarParceiroDeNegocioQuandoEleNaoEstaAssociadoANenhumItem() {
		delecaoServiceImpl.deletarPor(ID_PARCEIRO);
		
		verify(produtoRepository, times(1)).deletar(ID_PARCEIRO);
	}
	
	@Test
	public void deveLancarExceptionQuandoParceiroEstaAssociadoComOutraEntidade() {
		doThrow(JpaRepositoryWrapperException.class).when(produtoRepository).deletar(ID_PARCEIRO);
		
		assertThrows(JpaRepositoryWrapperException.class, () -> {
			delecaoServiceImpl.deletarPor(ID_PARCEIRO);
		});
	}
}
