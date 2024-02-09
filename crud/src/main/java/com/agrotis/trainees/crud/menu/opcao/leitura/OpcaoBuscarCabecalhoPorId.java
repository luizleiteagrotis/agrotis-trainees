package com.agrotis.trainees.crud.menu.opcao.leitura;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.menu.opcao.OpcaoMenu;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapperException;
import com.agrotis.trainees.crud.service.CabecalhoNotaService;

@Component
public class OpcaoBuscarCabecalhoPorId implements OpcaoMenu {
	
	private CabecalhoNota ultimoCabecalhoBuscado;
	private final Scanner SCANNER;
	private final CabecalhoNotaService CABECALHO_SERVICE;
	
	@Autowired
	public OpcaoBuscarCabecalhoPorId(Scanner scanner, CabecalhoNotaService cabecalhoService) {
		SCANNER = scanner;
		CABECALHO_SERVICE = cabecalhoService;
	}

	@Override
	public String getDescricao() {
		return "Buscar cabecalho por id";
	}
	
	public CabecalhoNota getUltimoCabecalhoBuscado() {
		return ultimoCabecalhoBuscado;
	}

	@Override
	public void executar() {
		CabecalhoNota cabecalho = selecionarCabecalho();
		System.out.println();
		mostrar(cabecalho);
		System.out.println();
	}
	
	private CabecalhoNota selecionarCabecalho() {
		CabecalhoNota cabecalho = null;
		while (naoEncontrado(cabecalho)) {
			System.out.print("Informe o id: ");
			long id = SCANNER.nextLong();
			try {
				cabecalho = CABECALHO_SERVICE.buscar(id);
				ultimoCabecalhoBuscado = cabecalho;
			} catch (JpaRepositoryWrapperException e) {
				System.out.println(e.getMessage());
			}
		}
		return cabecalho;
	}
	
	private boolean naoEncontrado(CabecalhoNota cabecalho) {
		return cabecalho == null;
	}
	
	private void mostrar(CabecalhoNota cabecalho) {
		System.out.println("ID = " + cabecalho.getId());
		System.out.println("NUMERO = " + cabecalho.getNumero());
		System.out.println("PARCEIRO = " + cabecalho.getParceiro().getNome());
		System.out.println("TIPO = " + cabecalho.getTipo());
		System.out.println("DATA EMISSAO = " + cabecalho.getDataEmissao());
		System.out.println("Valor total = " + cabecalho.getValorTotal());
	}
}
