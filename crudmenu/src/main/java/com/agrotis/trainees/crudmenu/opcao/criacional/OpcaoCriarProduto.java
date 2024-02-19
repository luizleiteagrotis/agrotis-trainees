package com.agrotis.trainees.crudmenu.opcao.criacional;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.produto.ProdutoApi;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crudmenu.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crudmenu.informante.InformanteData;
import com.agrotis.trainees.crudmenu.informante.InformanteParceiro;
import com.agrotis.trainees.crudmenu.opcao.OpcaoMenu;

@Component
public class OpcaoCriarProduto extends OpcaoCriacionalTemplate<ProdutoCadastroDto, ProdutoRetornoDto> {

	private final Scanner SCANNER;
	private final InformanteData INFORMANTE_DATA;
	private final InformanteParceiro INFORMANTE_PARCEIRO;
	
	@Autowired
	public OpcaoCriarProduto(Scanner scanner, ProdutoApi produtoApi, InformanteData informanteData,
			InformanteParceiro informanteParceiro) {
		super(produtoApi, descricao("Criar produto"));
		SCANNER = scanner;
		INFORMANTE_DATA = informanteData;
		INFORMANTE_PARCEIRO = informanteParceiro;
	}
	
	@Override
	public ProdutoCadastroDto criarDto() {
		ProdutoCadastroDto cadastroDto = new ProdutoCadastroDto();
		
		System.out.print("Informe o nome: ");
		String nome = SCANNER.next() + SCANNER.nextLine();
		cadastroDto.setNome(nome);
		
		System.out.print("Informe a descricao: ");
		String descricao = SCANNER.next() + SCANNER.nextLine();
		cadastroDto.setDescricao(descricao);
		
		System.out.println("Informe o fabricante (parceiro)!");
		informarSaidaTemporaria();
		ParceiroRetornoDto fabricante = INFORMANTE_PARCEIRO.informar();
		informarVolta();
		cadastroDto.setIdFabricante(fabricante.getId());
		
		System.out.println("Informe a data de fabricacao!");
		informarSaidaTemporaria();
		LocalDate dataFabricacao = INFORMANTE_DATA.informar();
		informarVolta();
		cadastroDto.setDataFabricacao(dataFabricacao);
		
		System.out.println("Informe a data de validade!");
		informarSaidaTemporaria();
		LocalDate dataValidade = INFORMANTE_DATA.informar();
		informarVolta();
		cadastroDto.setDataValidade(dataValidade);
		
		return cadastroDto;
	}

	private void informarSaidaTemporaria() {
		System.out.println("Saindo temporariamente de produto");
		System.out.println();
	}
	
	private void informarVolta() {
		System.out.println("Voltando para produto");
		System.out.println();
	}
}
