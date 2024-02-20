package com.agrotis.trainees.crudmenu.printer;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoRetornoDto;

@Component
public class CabecalhoPrinter implements Printer<CabecalhoRetornoDto>{

	@Override
	public void mostrar(CabecalhoRetornoDto cabecalho) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("CABECALHO NOTA");
		System.out.println("Id: " + cabecalho.getId());
		System.out.println("Numero: " + cabecalho.getNumero());
		System.out.println("IdParceiro: " + cabecalho.getIdParceiro());
		System.out.println("Tipo: " + cabecalho.getTipo());
		System.out.println("DataEmissao: " + cabecalho.getDataEmissao().format(formatter));
		System.out.println("ValorTotal: " + cabecalho.getValorTotal());
	}
}
