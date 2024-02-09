package com.agrotis.trainees.crud.menu.opcao.criacional;

import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.menu.opcao.OpcaoMenu;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@Component
public class OpcaoCriarParceiro implements OpcaoMenu {
	
	private final ParceiroNegocioService PARCEIRO_SERVICE;
	private final Scanner SCANNER;
	private ParceiroNegocio ultimoParceiroCriado;
	
	@Autowired
	public OpcaoCriarParceiro(ParceiroNegocioService parceiroService, Scanner scanner) {
		PARCEIRO_SERVICE = parceiroService;
		SCANNER = scanner;
	}
	
	@Override
	public String getDescricao() {
		return "Criar parceiro de negocio";
	}

	@Override
	public void executar() {
		System.out.println("--------------------------");
		System.out.println(getDescricao());
		System.out.println("--------------------------");
		boolean naoPersistiu = true;
		while (naoPersistiu) {
			ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
			
			System.out.print("Informe o nome: ");
			String nome = SCANNER.next() + SCANNER.nextLine();
			parceiroNegocio.setNome(nome);
			
			System.out.print("Informe a inscricao fiscal: ");
			String inscricao = SCANNER.next() + SCANNER.nextLine();
			parceiroNegocio.setInscricaoFiscal(inscricao);
			
			System.out.print("Informe o endereco: ");
			String endereco = SCANNER.next() + SCANNER.nextLine();
			parceiroNegocio.setEndereco(endereco);
			
			System.out.print("Informe o telefone: ");
			String telefone = SCANNER.next() + SCANNER.nextLine();
			parceiroNegocio.setTelefone(telefone);
			
			try {
				ultimoParceiroCriado = PARCEIRO_SERVICE.salvar(parceiroNegocio);
				naoPersistiu = false;
			} catch(ConstraintViolationException e) {
				Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
				for (ConstraintViolation<?> violation : violations) {
					System.out.println(violation.getMessage());
				}
			}
		}
	}

	public ParceiroNegocio getUltimoParceiroCriado() {
		return ultimoParceiroCriado;
	}
}
