package com.agrotis.trainees.crud.service.parceiro.atualizacao.rn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.mapper.parceiro.ParceiroMapper;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.service.parceiro.atualizacao.ParceiroNegocioAtualizacaoRnException;

@ExtendWith(MockitoExtension.class)
class AtualizadorParceiroRnImplTest {

	@Mock
	private ParceiroMapper parceiroMapper;
	
	@Mock
	private ParceiroRepository parceiroRepository;
	
	@InjectMocks
	private AtualizadorParceiroRnImpl atualizadorParceiroRnImpl;

	private final String NOME_PARCEIRO = "Fabrica de bananas";
	private final String INSCRICAO_FISCAL = "82.413.816/0001-01";
	private final Long ID_PARCEIRO_VERIFICADO = 1L;
	private final Long ID_OUTRO_PARCEIRO = 2L;
	private ParceiroAtualizacaoDto atualizacaoDto;
	private ParceiroNegocio parceiroVerificado;
	private ParceiroNegocio outroParceiro;
	
	@BeforeEach
	public void setUp() {
		atualizacaoDto = new ParceiroAtualizacaoDto();
		atualizacaoDto.setNome(NOME_PARCEIRO);
		atualizacaoDto.setInscricaoFiscal(INSCRICAO_FISCAL);
		atualizacaoDto.setId(ID_PARCEIRO_VERIFICADO);
		parceiroVerificado = new ParceiroNegocio(ID_PARCEIRO_VERIFICADO);
		outroParceiro = new ParceiroNegocio(ID_OUTRO_PARCEIRO);
	}
	
	@Test
	public void deveLancarExceptionQuandoExistirParceiroDiferenteComOMesmoNome() {
		when(parceiroRepository.buscarPorNome(NOME_PARCEIRO)).thenReturn(outroParceiro);
		
		Exception exception = assertThrows(ParceiroNegocioAtualizacaoRnException.class, () -> {
			atualizadorParceiroRnImpl.operarSobre(atualizacaoDto);
		});
		
		String mensagemEsperada = "Ja existe parceiro com nome {" + NOME_PARCEIRO + "}";
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	@Test
	public void deveNaoLancarExceptionQuandoParceiroComOMesmoNomeForEleMesmo() {
		when(parceiroRepository.buscarPorNome(NOME_PARCEIRO)).thenReturn(parceiroVerificado);
		
		assertDoesNotThrow(() -> atualizadorParceiroRnImpl.operarSobre(atualizacaoDto));
	}
	
	@Test
	public void deveLancarExceptionQuandoExistirParceiroDiferenteComAMesmaInscricaoFiscal() {
		when(parceiroRepository.buscarPorInscricaoFiscal(INSCRICAO_FISCAL)).thenReturn(outroParceiro);
		
		Exception exception = assertThrows(ParceiroNegocioAtualizacaoRnException.class, () -> {
			atualizadorParceiroRnImpl.operarSobre(atualizacaoDto);
		});
		
		String mensagemEsperada = "Ja existe parceiro com inscricao fiscal {" + INSCRICAO_FISCAL + "}";
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	@Test
	public void deveNaoLancarExceptionQuandoParceiroComAMesmaInscricaoForEleMesmo() {
		when(parceiroRepository.buscarPorInscricaoFiscal(INSCRICAO_FISCAL)).thenReturn(parceiroVerificado);
		
		assertDoesNotThrow(() -> atualizadorParceiroRnImpl.operarSobre(atualizacaoDto));
	}
	
	@Test
	public void deveRetornarParceiroComAtributosAtualizadosQuandoPassarPorTodasAsVerificacoes() {
		when(parceiroMapper.converterParaEntidade(atualizacaoDto)).thenReturn(parceiroVerificado);
		
		ParceiroNegocio parceiroRetornado = atualizadorParceiroRnImpl.operarSobre(atualizacaoDto);
		
		verify(parceiroMapper, times(1)).converterParaEntidade(atualizacaoDto);
		assertThat(parceiroRetornado, is(sameInstance(parceiroVerificado)));
	}
}
