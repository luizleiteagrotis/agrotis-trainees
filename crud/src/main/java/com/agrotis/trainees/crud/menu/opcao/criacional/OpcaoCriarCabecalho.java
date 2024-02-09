package com.agrotis.trainees.crud.menu.opcao.criacional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.menu.informante.InformanteData;
import com.agrotis.trainees.crud.menu.informante.InformanteParceiro;
import com.agrotis.trainees.crud.menu.informante.InformanteTipoNota;
import com.agrotis.trainees.crud.menu.opcao.OpcaoMenu;
import com.agrotis.trainees.crud.service.CabecalhoNotaService;

@Component
public class OpcaoCriarCabecalho implements OpcaoMenu {
	
	private CabecalhoNota ultimoCabecalhoCriado;
	private final Scanner SCANNER;
	private final InformanteParceiro INFORMANTE_PARCEIRO;
	private final InformanteTipoNota INFORMANTE_TIPO_NOTA;
	private final InformanteData INFORMANTE_DATA;
	private final CabecalhoNotaService CABECALHO_SERVICE;
	
	@Autowired
	public OpcaoCriarCabecalho(Scanner scanner, 
			InformanteParceiro informanteParceiro, 
			InformanteTipoNota informanteTipoNota,
			InformanteData informanteData, 
			CabecalhoNotaService cabecalhoService) {
		SCANNER = scanner;
		INFORMANTE_PARCEIRO = informanteParceiro;
		INFORMANTE_TIPO_NOTA = informanteTipoNota;
		INFORMANTE_DATA = informanteData;
		CABECALHO_SERVICE = cabecalhoService;
	}
	
	@Override
	public String getDescricao() {
		return "Criar cabecalho";
	}
	
	public CabecalhoNota getUltimoCabecalhoCriado() {
		return ultimoCabecalhoCriado;
	}

	@Override
	public void executar() {
		System.out.println("--------------------------");
		System.out.println(getDescricao());
		System.out.println("--------------------------");
		boolean naoPersistiu = true;
		while (naoPersistiu) {
			CabecalhoNota cabecalho = new CabecalhoNota();
			
			System.out.print("Informe o numero: ");
			Long numero = SCANNER.nextLong();
			cabecalho.setNumero(numero);
			
			System.out.println("Informe o parceiro!");
			informarSaidaTemporaria();
			ParceiroNegocio parceiro = INFORMANTE_PARCEIRO.informar();
			informarVolta();
			cabecalho.setParceiro(parceiro);
			
			System.out.println("Informe o tipo da nota!");
			informarSaidaTemporaria();
			TipoNota tipo = INFORMANTE_TIPO_NOTA.informar();
			informarVolta();
			cabecalho.setTipo(tipo);
			
			System.out.println("Informe a data de emissao!");
			informarSaidaTemporaria();
			LocalDate data = INFORMANTE_DATA.informar();
			informarVolta();
			cabecalho.setDataEmissao(data);
			
			cabecalho.setValorTotal(BigDecimal.ZERO);
			
			try {
				ultimoCabecalhoCriado = CABECALHO_SERVICE.salvar(cabecalho);
				naoPersistiu = false;
			} catch(ConstraintViolationException e) {
				Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
				for (ConstraintViolation<?> violation : violations) {
					System.out.println(violation.getMessage());
				}
			}
		}
		System.out.println("Criado cabecalho com id: " + ultimoCabecalhoCriado.getId());
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
