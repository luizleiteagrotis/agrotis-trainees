package com.agrotis.trainees.crudmenu.opcao.criacional;

import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.item.ItemApi;
import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoRetornoDto;
import com.agrotis.trainees.crudmenu.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crudmenu.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crudmenu.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crudmenu.informante.InformanteCabecalho;
import com.agrotis.trainees.crudmenu.informante.InformanteProduto;

@Component
public class OpcaoCriarItemNota extends OpcaoCriacionalTemplate<ItemCadastroDto, ItemRetornoDto> {

	private final Scanner SCANNER;
	private final InformanteProduto INFORMANTE_PRODUTO;
	private final InformanteCabecalho INFORMANTE_CABECALHO;
	
	@Autowired
	public OpcaoCriarItemNota(Scanner scanner, ItemApi itemApi, InformanteProduto informanteProduto, 
			InformanteCabecalho informanteCabecalho) {
		super(itemApi, descricao("Criar item nota"));
		SCANNER = scanner;
		INFORMANTE_PRODUTO = informanteProduto;
		INFORMANTE_CABECALHO = informanteCabecalho;
	}
	
	@Override
	public ItemCadastroDto criarDto() {
		ItemCadastroDto cadastroDto = new ItemCadastroDto();
		
		System.out.println("Informe o produto!");
		informarSaidaTemporaria();
		ProdutoRetornoDto produto = INFORMANTE_PRODUTO.informar();
		informarVolta();
		cadastroDto.setIdProduto(produto.getId());
		
		System.out.print("Informe a quantidade: ");
		int quantidade = SCANNER.nextInt();
		cadastroDto.setQuantidade(quantidade);
		
		System.out.print("Informe o preco unitario: ");
		BigDecimal precoUnitario = SCANNER.nextBigDecimal();
		cadastroDto.setPrecoUnitario(precoUnitario);
		
		System.out.print("Informe o cabecalho!");
		informarSaidaTemporaria();
		CabecalhoRetornoDto cebecalho = INFORMANTE_CABECALHO.informar();
		informarVolta();
		cadastroDto.setIdCabecalho(cebecalho.getId());
		
		return cadastroDto;
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
