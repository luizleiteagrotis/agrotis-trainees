package com.agrotis.trainees.crudmenu.printer;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.item.ItemRetornoDto;

@Component
public class ItemPrinter implements Printer<ItemRetornoDto> {

	@Override
	public void mostrar(ItemRetornoDto item) {
		System.out.println("ITEM NOTA");
		System.out.println("Id: " + item.getId());
		System.out.println("IdProduto: " + item.getIdProduto());
		System.out.println("Quantidade: " + item.getQuantidade());
		System.out.println("PrecoUnitario: " + item.getPrecoUnitario());
		System.out.println("Valor total: " + item.getValorTotal());
		System.out.println("IdCabecalho: " + item.getIdCabecalho());
	}
}
