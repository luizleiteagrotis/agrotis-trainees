package com.agrotis.trainees.crud.menu.opcao.criacional;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.menu.informante.InformanteCabecalho;
import com.agrotis.trainees.crud.menu.informante.InformanteProduto;
import com.agrotis.trainees.crud.menu.opcao.OpcaoMenu;
import com.agrotis.trainees.crud.service.ItemNotaService;

@Component
public class OpcaoCriarItemNota implements OpcaoMenu {

	private ItemNota ultimoItemCriado;
	private final Scanner SCANNER;
	private final InformanteProduto INFORMANTE_PRODUTO;
	private final InformanteCabecalho INFORMANTE_CABECALHO;
	private final ItemNotaService ITEM_NOTA_SERVICE;
	
	@Autowired
	public OpcaoCriarItemNota(Scanner scanner, 
			InformanteProduto informanteProduto, 
			InformanteCabecalho informanteCabecalho,
			ItemNotaService itemNotaService) {
		SCANNER = scanner;
		INFORMANTE_PRODUTO = informanteProduto;
		INFORMANTE_CABECALHO = informanteCabecalho;
		ITEM_NOTA_SERVICE = itemNotaService;
	}
	
	@Override
	public String getDescricao() {
		return "Criar item nota";
	}

	@Override
	public void executar() {
		System.out.println("--------------------------");
		System.out.println(getDescricao());
		System.out.println("--------------------------");
		boolean naoPersistiu = true;
		while (naoPersistiu) {
			ItemNota itemNota = new ItemNota();
			
			System.out.println("Informe o produto!");
			informarSaidaTemporaria();
			Produto produto = INFORMANTE_PRODUTO.informar();
			informarVolta();
			itemNota.setProduto(produto);
			
			System.out.print("Informe a quantidade: ");
			int quantidade = SCANNER.nextInt();
			itemNota.setQuantidade(quantidade);
			
			System.out.print("Informe o preco unitario: ");
			BigDecimal precoUnitario = SCANNER.nextBigDecimal();
			itemNota.setPrecoUnitario(precoUnitario);
			
			System.out.print("Informe o cabecalho!");
			informarSaidaTemporaria();
			CabecalhoNota cebecalho = INFORMANTE_CABECALHO.informar();
			informarVolta();
			itemNota.setCabecalhoNota(cebecalho);
			
			try {
				ultimoItemCriado = ITEM_NOTA_SERVICE.salvar(itemNota);
				naoPersistiu = false;
			} catch(ConstraintViolationException e) {
				Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
				for (ConstraintViolation<?> violation : violations) {
					System.out.println(violation.getMessage());
				}
			}
		}
		System.out.println("Item nota criado com id: " + ultimoItemCriado.getId());
	}
	
	private void informarSaidaTemporaria() {
		System.out.println("Saindo temporariamente de item nota");
		System.out.println();
	}
	
	private void informarVolta() {
		System.out.println("Voltando para item nota");
		System.out.println();
	}

}
