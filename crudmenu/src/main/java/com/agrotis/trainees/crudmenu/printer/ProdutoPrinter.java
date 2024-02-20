package com.agrotis.trainees.crudmenu.printer;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.produto.ProdutoRetornoDto;

@Component
public class ProdutoPrinter implements Printer<ProdutoRetornoDto>{

	@Override
	public void mostrar(ProdutoRetornoDto produto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("PRODUTO");
		System.out.println("Id: " + produto.getId());
		System.out.println("Nome: " + produto.getNome());
		System.out.println("Descricao: " + produto.getDescricao());
		System.out.println("IdFabricante: " + produto.getIdFabricante());
		System.out.println("DataFabricacao: " + produto.getDataFabricacao().format(formatter));
		System.out.println("DataValidade: " + produto.getDataValidade().format(formatter));
		System.out.println("Estoque: " + produto.getEstoque());
	}
}
