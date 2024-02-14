package com.agrotis.trainees.crudmenu.opcao.leitura;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.cabecalho.CabecalhoApi;
import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.OpcaoMenu;

@Component
public class OpcaoBuscarCabecalhoPorId implements OpcaoMenu {
	
	private final Scanner SCANNER;
	private final CabecalhoApi CABECALHO_API;
	private CabecalhoRetornoDto ultimoCabecalhoBuscado;
	
	@Autowired
	public OpcaoBuscarCabecalhoPorId(Scanner scanner, CabecalhoApi cabecalhoApi) {
		SCANNER = scanner;
		CABECALHO_API = cabecalhoApi;
	}

	@Override
	public String getDescricao() {
		return "Buscar cabecalho por id";
	}
	
	@Override
	public void executar() {
		CabecalhoRetornoDto cabecalho = selecionarCabecalho();
		System.out.println();
		mostrar(cabecalho);
		System.out.println();
	}
	
	private CabecalhoRetornoDto selecionarCabecalho() {
		CabecalhoRetornoDto cabecalho = null;
		while (naoEncontrado(cabecalho)) {
			System.out.print("Informe o id: ");
			long id = SCANNER.nextLong();
			cabecalho = CABECALHO_API.buscarPor(id);
			ultimoCabecalhoBuscado = cabecalho;
		}
		return cabecalho;
	}
	
	public CabecalhoRetornoDto getUltimoCabecalhoBuscado() {
		return ultimoCabecalhoBuscado;
	}
	
	private boolean naoEncontrado(CabecalhoRetornoDto cabecalho) {
		return cabecalho == null;
	}
	
	private void mostrar(CabecalhoRetornoDto cabecalho) {
		System.out.println("ID = " + cabecalho.getId());
		System.out.println("NUMERO = " + cabecalho.getNumero());
		System.out.println("ID PARCEIRO = " + cabecalho.getIdParceiro());
		System.out.println("TIPO = " + cabecalho.getTipo());
		System.out.println("DATA EMISSAO = " + cabecalho.getDataEmissao());
		System.out.println("Valor total = " + cabecalho.getValorTotal());
	}
}
