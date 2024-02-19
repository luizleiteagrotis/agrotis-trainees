package com.agrotis.trainees.crudmenu.opcao.criacional;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.cabecalho.CabecalhoApi;
import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoRetornoDto;
import com.agrotis.trainees.crudmenu.dto.cabecalho.TipoNota;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crudmenu.informante.InformanteData;
import com.agrotis.trainees.crudmenu.informante.InformanteParceiro;
import com.agrotis.trainees.crudmenu.informante.InformanteTipoNota;
import com.agrotis.trainees.crudmenu.opcao.OpcaoMenu;

@Component
public class OpcaoCriarCabecalho extends OpcaoCriacionalTemplate<CabecalhoCadastroDto, CabecalhoRetornoDto> {
	
	private final Scanner SCANNER;
	private final InformanteParceiro INFORMANTE_PARCEIRO;
	private final InformanteTipoNota INFORMANTE_TIPO_NOTA;
	private final InformanteData INFORMANTE_DATA;
	
	@Autowired
	public OpcaoCriarCabecalho(Scanner scanner, 
			InformanteParceiro informanteParceiro, 
			InformanteTipoNota informanteTipoNota,
			InformanteData informanteData,
			CabecalhoApi cabecalhoApi) {
		super(cabecalhoApi, descricao("Criar cabecalho"));
		SCANNER = scanner;
		INFORMANTE_PARCEIRO = informanteParceiro;
		INFORMANTE_TIPO_NOTA = informanteTipoNota;
		INFORMANTE_DATA = informanteData;
	}
	
	@Override
	public CabecalhoCadastroDto criarDto() {
		CabecalhoCadastroDto cadastroDto = new CabecalhoCadastroDto();
		
		System.out.print("Informe o numero: ");
		Long numero = SCANNER.nextLong();
		cadastroDto.setNumero(numero);
		
		System.out.println("Informe o parceiro!");
		informarSaidaTemporaria();
		ParceiroRetornoDto parceiro = INFORMANTE_PARCEIRO.informar();
		informarVolta();
		cadastroDto.setIdParceiro(parceiro.getId());
		
		System.out.println("Informe o tipo da nota!");
		informarSaidaTemporaria();
		TipoNota tipo = INFORMANTE_TIPO_NOTA.informar();
		informarVolta();
		cadastroDto.setTipo(tipo);
		
		System.out.println("Informe a data de emissao!");
		informarSaidaTemporaria();
		LocalDate data = INFORMANTE_DATA.informar();
		informarVolta();
		cadastroDto.setDataEmissao(data);
		
		return cadastroDto;
	}

	private void informarSaidaTemporaria() {
		System.out.println("Saindo temporariamente de cabecalho");
		System.out.println();
	}
	
	private void informarVolta() {
		System.out.println("Voltando para cabecalho");
		System.out.println();
	}
}
