package com.agrotis.trainees.crud.service.parceiro.cadastro.rn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.service.parceiro.cadastro.ParceiroNegocioCadastroRnException;

@ExtendWith(MockitoExtension.class)
class ParceiroExistenteCadastroRnTest {

	@Mock
	private ParceiroRepository parceiroRepository;
	
	@InjectMocks
	private ParceiroExistenteCadastroRn cadastroRn;
	
	private final String NOME_PARCEIRO = "Fabrica de bananas";
	private final String INSCRICAO_FISCAL_PARCEIRO = "82.413.816/0001-01";
	
	private ParceiroNegocio parceiroNegocio;
	
	@BeforeEach
	public void setUp() {
		parceiroNegocio = new ParceiroNegocio();
		parceiroNegocio.setNome(NOME_PARCEIRO);
		parceiroNegocio.setInscricaoFiscal(INSCRICAO_FISCAL_PARCEIRO);
	}
	
	@Test
	public void deveNaoLancarExceptionQuandoNaoExisteParceiroComMesmoNomeOuInscricaoFiscal() {
		when(parceiroRepository.existeInstanciaCom(NOME_PARCEIRO, INSCRICAO_FISCAL_PARCEIRO)).thenReturn(false);
		
		assertDoesNotThrow(() -> cadastroRn.operarSobre(parceiroNegocio));
	}
	
	@Test
	public void deveLancarExceptionQuandoExisteParceiroComMesmoNomeOuInscricaoFiscal() {
		when(parceiroRepository.existeInstanciaCom(NOME_PARCEIRO, INSCRICAO_FISCAL_PARCEIRO)).thenReturn(true);
		
		Exception exception = assertThrows(ParceiroNegocioCadastroRnException.class, () -> {
			cadastroRn.operarSobre(parceiroNegocio);
		});
		
		String mensagemEsperada = "Ja existe parceiro com nome {" + NOME_PARCEIRO + "}"
									+ " ou inscricao fiscal {" + INSCRICAO_FISCAL_PARCEIRO + "}";
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	@Test
	public void deveRetornarMesmoParceiroPassadoComoArgumentoQuandoNaoExisteParceiroAinda() {
		when(parceiroRepository.existeInstanciaCom(NOME_PARCEIRO, INSCRICAO_FISCAL_PARCEIRO)).thenReturn(false);
		
		ParceiroNegocio parceiroRetornado =	cadastroRn.operarSobre(parceiroNegocio);
		
		assertThat(parceiroRetornado, is(sameInstance(parceiroNegocio)));
	}
}
