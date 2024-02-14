package com.agrotis.trainees.crudmenu.opcao.criacional;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.parceiro.ParceiroApi;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.OpcaoMenu;

@Component
public class OpcaoCriarParceiro implements OpcaoMenu {
	
	private final Scanner SCANNER;
	private final ParceiroApi PARCEIRO_API;
	private ParceiroRetornoDto ultimoParceiroCriado;
	
	@Autowired
	public OpcaoCriarParceiro(Scanner scanner, ParceiroApi parceiroApi) {
		SCANNER = scanner;
		PARCEIRO_API = parceiroApi;
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
			ParceiroCadastroDto cadastroDto = new ParceiroCadastroDto();
			
			System.out.print("Informe o nome: ");
			String nome = SCANNER.next() + SCANNER.nextLine();
			cadastroDto.setNome(nome);
			
			System.out.print("Informe a inscricao fiscal: ");
			String inscricao = SCANNER.next() + SCANNER.nextLine();
			cadastroDto.setInscricaoFiscal(inscricao);
			
			System.out.print("Informe o endereco: ");
			String endereco = SCANNER.next() + SCANNER.nextLine();
			cadastroDto.setEndereco(endereco);
			
			System.out.print("Informe o telefone: ");
			String telefone = SCANNER.next() + SCANNER.nextLine();
			cadastroDto.setTelefone(telefone);
			
			try {
				ultimoParceiroCriado = PARCEIRO_API.cadastrar(cadastroDto);
				naoPersistiu = false;
			} catch(Exception e) {
				System.out.println("Nao foi possivel cadastar");
			}
		}
	}

	public ParceiroRetornoDto getUltimoParceiroCriado() {
		return ultimoParceiroCriado;
	}
}
