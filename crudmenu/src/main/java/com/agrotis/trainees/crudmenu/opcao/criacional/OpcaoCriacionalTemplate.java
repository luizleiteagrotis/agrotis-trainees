package com.agrotis.trainees.crudmenu.opcao.criacional;

import com.agrotis.trainees.crudmenu.api.CrudApiMethods;
import com.agrotis.trainees.crudmenu.api.CrudApiMethodsTemplate;
import com.agrotis.trainees.crudmenu.exception.ApiMethodException;
import com.agrotis.trainees.crudmenu.exception.BadRequestException;
import com.agrotis.trainees.crudmenu.exception.RecursoNaoEncontradoException;
import com.agrotis.trainees.crudmenu.opcao.OpcaoMenu;

public abstract class OpcaoCriacionalTemplate<CadastroDto, RetornoDto> implements OpcaoMenu {

	private final CrudApiMethods<CadastroDto, RetornoDto> CADASTRO_API;
	private final String descricao;
	private RetornoDto ultimaEntidadeCriada;
	
	public OpcaoCriacionalTemplate(CrudApiMethods<CadastroDto, RetornoDto> cadastroApi, String descricao) {
		CADASTRO_API = cadastroApi;
		this.descricao = descricao;
	}
	
	@Override
	public void executar() {
		System.out.println("--------------------------");
		System.out.println(descricao);
		System.out.println("--------------------------");
		CadastroDto dto = criarDto();
		ultimaEntidadeCriada = null;
		try {
			ultimaEntidadeCriada = CADASTRO_API.cadastrar(dto);
			System.out.println("Entidade criada com sucesso");
		} catch (BadRequestException e) {
			System.out.println(e.getMessage());
		} catch (RecursoNaoEncontradoException e){
			System.out.println(e.getMessage());
		} catch (ApiMethodException e) {
			System.out.println("Nao foi possivel cadastar a entidade");
		}
		System.out.println();
	}
	
	@Override
	public String getDescricao() {
		return descricao;
	}
	
	public RetornoDto getUltimaEntidadeCriada() {
		return ultimaEntidadeCriada;
	}
	
	public abstract CadastroDto criarDto();
	
	/**
	 * Metodo para aumentar a legibilidade do construtor das classes filhas
	 * 
	 * @param descricao Descricao da opcao
	 * @return Mesma instancia
	 */
	protected static String descricao(String descricao) {
		return descricao;
	}
}
