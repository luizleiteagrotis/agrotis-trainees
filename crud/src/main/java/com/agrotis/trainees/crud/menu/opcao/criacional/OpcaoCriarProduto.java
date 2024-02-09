package com.agrotis.trainees.crud.menu.opcao.criacional;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.menu.informante.InformanteData;
import com.agrotis.trainees.crud.menu.informante.InformanteParceiro;
import com.agrotis.trainees.crud.menu.opcao.OpcaoMenu;
import com.agrotis.trainees.crud.service.ProdutoService;

@Component
public class OpcaoCriarProduto implements OpcaoMenu {

	private final ProdutoService PRODUTO_SERVICE;
	private final InformanteParceiro INFORMANTE_PARCEIRO;
	private final InformanteData INFORMANTE_DATA;
	private final Scanner SCANNER;
	private Produto ultimoProdutoCriado;
	
	@Autowired
	public OpcaoCriarProduto(ProdutoService produtoService, 
			InformanteParceiro informanteParceiro, InformanteData informanteData, Scanner scanner) {
		PRODUTO_SERVICE = produtoService;
		INFORMANTE_PARCEIRO = informanteParceiro;
		INFORMANTE_DATA = informanteData;
		SCANNER = scanner;
	}
	
	@Override
	public String getDescricao() {
		return "Criar produto";
	}
	
	public Produto getUltimoProdutoCriado() {
		return ultimoProdutoCriado;
	}

	@Override
	public void executar() {
		System.out.println("--------------------------");
		System.out.println(getDescricao());
		System.out.println("--------------------------");
		boolean naoPersistiu = true;
		while (naoPersistiu) {
			Produto produto = new Produto();
			
			System.out.print("Informe o nome: ");
			String nome = SCANNER.next() + SCANNER.nextLine();
			produto.setNome(nome);
			
			System.out.print("Informe a descricao: ");
			String descricao = SCANNER.next() + SCANNER.nextLine();
			produto.setDescricao(descricao);
			
			System.out.println("Informe o fabricante (parceiro)!");
			informarSaidaTemporaria();
			ParceiroNegocio fabricante = INFORMANTE_PARCEIRO.informar();
			informarVolta();
			produto.setFabricante(fabricante);
			
			System.out.println("Informe a data de fabricacao!");
			informarSaidaTemporaria();
			LocalDate dataFabricacao = INFORMANTE_DATA.informar();
			informarVolta();
			produto.setDataFabricacao(dataFabricacao);
			
			
			System.out.println("Informe a data de validade!");
			informarSaidaTemporaria();
			LocalDate dataValidade = INFORMANTE_DATA.informar();
			informarVolta();
			produto.setDataValidade(dataValidade);
			
			produto.setEstoque(0);
			
			try {
				ultimoProdutoCriado = PRODUTO_SERVICE.salvar(produto);
				naoPersistiu = false;
			} catch(ConstraintViolationException e) {
				Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
				for (ConstraintViolation<?> violation : violations) {
					System.out.println(violation.getMessage());
				}
			}
		}
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
