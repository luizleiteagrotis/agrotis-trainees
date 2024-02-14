package com.agrotis.trainees.crudmenu.menu;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.informante.InformantePosicaoOpcao;
import com.agrotis.trainees.crudmenu.opcao.OpcaoMenu;

@Component
public class CrudMenu {
	
	private List<OpcaoMenu> opcoes;
	private boolean continuar;
	private final int OPCAO_SAIDA;
	private final InformantePosicaoOpcao INFORMANTE_POSICAO;
	
	@Autowired
	public CrudMenu(List<OpcaoMenu> opcoes, Scanner scanner, InformantePosicaoOpcao informantePosicao) {
		this.opcoes = opcoes;
		OPCAO_SAIDA = 0;
		INFORMANTE_POSICAO = informantePosicao;
	}
	
	public void iniciar() {
		System.out.println("--------------------------");
		System.out.println("CRUD MENU");
		System.out.println("--------------------------");
		continuar = true;
		while (continuar) {	
			mostrarOpcoes();
			selecionarOpcao();
		}
		System.out.println("Te aguardo novamente!");
	}

	private void mostrarOpcoes() {
		System.out.println(OPCAO_SAIDA + " - Sair");
		for (int i = 1; i < opcoes.size() + 1; i++) {
			OpcaoMenu opcao = opcoes.get(i - 1);
			String descricao = opcao.getDescricao();
			System.out.println(i + " - " + descricao);
		}
	}
	
	private void selecionarOpcao() {
		Integer posicaoOpcao = INFORMANTE_POSICAO.informar(0, opcoes.size());
		if (posicaoOpcao == OPCAO_SAIDA) {
			continuar = false;
		} else {
			OpcaoMenu opcao = opcoes.get(posicaoOpcao - 1);
			opcao.executar();
		}
	}
}
