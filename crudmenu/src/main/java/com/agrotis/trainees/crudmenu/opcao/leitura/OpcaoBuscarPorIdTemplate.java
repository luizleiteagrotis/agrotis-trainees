package com.agrotis.trainees.crudmenu.opcao.leitura;

import com.agrotis.trainees.crudmenu.api.CrudApiMethods;
import com.agrotis.trainees.crudmenu.exception.ApiMethodException;
import com.agrotis.trainees.crudmenu.exception.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crudmenu.exception.RecursoNaoEncontradoException;
import com.agrotis.trainees.crudmenu.informante.InformanteEntidadeId;
import com.agrotis.trainees.crudmenu.opcao.OpcaoMenu;
import com.agrotis.trainees.crudmenu.printer.Printer;

public abstract class OpcaoBuscarPorIdTemplate<RetornoDto> implements OpcaoMenu {

	private final CrudApiMethods<?, RetornoDto> ENTIDADE_API;
	private final String DESCRICAO;
	private final InformanteEntidadeId INFORMANTE_ENTIDADE_ID;
	private final Printer<RetornoDto> ENTIDADE_PRINTER;
	private RetornoDto ultimaEntidadeBuscada;
	
	public OpcaoBuscarPorIdTemplate(CrudApiMethods<?, RetornoDto> entidadeApi, 
			String descricao, InformanteEntidadeId informanteEntidadeId, 
			Printer<RetornoDto> entidadePrinter) {
		ENTIDADE_API = entidadeApi;
		DESCRICAO = descricao;
		INFORMANTE_ENTIDADE_ID = informanteEntidadeId;
		ENTIDADE_PRINTER = entidadePrinter;
	}
	
	@Override
	public String getDescricao() {
		return DESCRICAO;
	}

	@Override
	public void executar() {
		System.out.println("--------------------------");
		System.out.println(DESCRICAO);
		System.out.println("--------------------------");
		long id = INFORMANTE_ENTIDADE_ID.informar();
		RetornoDto retornoDto = null;
		try {
			retornoDto = ENTIDADE_API.buscarPor(id);
		} catch (EntidadeNaoEncontradaException e) {
			System.out.println(e.getMessage());
		} catch (RecursoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (ApiMethodException e) {
			System.out.println("Nao foi possivel buscar a entidade");
		}
		if (retornoDto != null) {
			System.out.println();
			ENTIDADE_PRINTER.mostrar(retornoDto);
			System.out.println();
			ultimaEntidadeBuscada = retornoDto;
		}
	}
	
	public RetornoDto getUltimaEntidadeBuscada() {
		return ultimaEntidadeBuscada;
	}
	
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
