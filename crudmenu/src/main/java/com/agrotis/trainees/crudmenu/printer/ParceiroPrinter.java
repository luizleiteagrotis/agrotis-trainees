package com.agrotis.trainees.crudmenu.printer;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;

@Component
public class ParceiroPrinter implements Printer<ParceiroRetornoDto>{

	@Override
	public void mostrar(ParceiroRetornoDto parceiro) {
		System.out.println("PARCEIRO DE NEGOCIO");
		System.out.println("Id: " + parceiro.getId());
		System.out.println("Nome: " + parceiro.getNome());
		System.out.println("InscricaoFiscal: " + parceiro.getInscricaoFiscal());
		System.out.println("Endereco: " + parceiro.getEndereco());
		System.out.println("Telefone: " + parceiro.getTelefone());
	}
}
