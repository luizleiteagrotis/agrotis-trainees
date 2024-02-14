package com.agrotis.trainees.crudmenu.opcao.criacional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

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
public class OpcaoCriarCabecalho implements OpcaoMenu {
	
	private final Scanner SCANNER;
	private final CabecalhoApi CABECALHO_API;
	private final InformanteParceiro INFORMANTE_PARCEIRO;
	private final InformanteTipoNota INFORMANTE_TIPO_NOTA;
	private final InformanteData INFORMANTE_DATA;
	private CabecalhoRetornoDto ultimoCabecalhoCriado;
	
	@Autowired
	public OpcaoCriarCabecalho(Scanner scanner, 
			InformanteParceiro informanteParceiro, 
			InformanteTipoNota informanteTipoNota,
			InformanteData informanteData,
			CabecalhoApi cabecalhoApi) {
		SCANNER = scanner;
		INFORMANTE_PARCEIRO = informanteParceiro;
		INFORMANTE_TIPO_NOTA = informanteTipoNota;
		INFORMANTE_DATA = informanteData;
		CABECALHO_API = cabecalhoApi;
	}
	
	@Override
	public String getDescricao() {
		return "Criar cabecalho";
	}

	@Override
	public void executar() {
		System.out.println("--------------------------");
		System.out.println(getDescricao());
		System.out.println("--------------------------");
		boolean naoPersistiu = true;
		while (naoPersistiu) {
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
			
			try {
				ultimoCabecalhoCriado = CABECALHO_API.cadastrar(cadastroDto);
				naoPersistiu = false;
			} catch(Exception e) {
				System.out.println("Nao foi possivel cadastar");
			}
		}
		System.out.println("Criado cabecalho com id: " + ultimoCabecalhoCriado.getId());
	}

	public CabecalhoRetornoDto getUltimoCabecalhoCriado() {
		return ultimoCabecalhoCriado;
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
