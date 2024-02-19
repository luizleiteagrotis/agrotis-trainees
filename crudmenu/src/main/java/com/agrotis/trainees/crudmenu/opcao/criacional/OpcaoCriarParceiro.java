package com.agrotis.trainees.crudmenu.opcao.criacional;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.parceiro.ParceiroApi;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.OpcaoMenu;

@Component
public class OpcaoCriarParceiro extends OpcaoCriacionalTemplate<ParceiroCadastroDto, ParceiroRetornoDto> {
	
	private final Scanner SCANNER;
	
	@Autowired
	public OpcaoCriarParceiro(Scanner scanner, ParceiroApi parceiroApi) {
		super(parceiroApi, descricao("Criar parceiro de negocio"));
		SCANNER = scanner;
	}

	@Override
	public ParceiroCadastroDto criarDto() {
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
		
		return cadastroDto;
	}
}
