package com.agrotis.trainees.crud.service.parceiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;

@Service
public class ParceiroNegocioService {
	
	private ParceiroNegocioCadastroService cadastroService;
	private ParceiroNegocioBuscaService buscaService;
	private ParceiroNegocioAtualizacaoService atualizacaoService;
	private ParceiroNegocioDelecaoService delecaoService;
	
	@Autowired
	public ParceiroNegocioService(ParceiroNegocioCadastroService cadastroService,
			ParceiroNegocioBuscaService buscaService, 
			ParceiroNegocioAtualizacaoService atualizacaoService,
			ParceiroNegocioDelecaoService delecaoService) {
		this.cadastroService = cadastroService;
		this.buscaService = buscaService;
		this.atualizacaoService = atualizacaoService;
		this.delecaoService = delecaoService;
	}

	public ParceiroRetornoDto cadastrar(ParceiroCadastroDto cadastroDto) {
		return cadastroService.cadastrar(cadastroDto);
	}
	
	public ParceiroRetornoDto buscarPor(Long idParceiro) {
		return buscaService.buscarPor(idParceiro);
	}
	
	public Page<ParceiroRetornoDto> listarTodos(Pageable pageable) {
		return buscaService.listarTodos(pageable);
	}
	
	public ParceiroRetornoDto atualizar(ParceiroAtualizacaoDto atualizacaoDto) {
		return atualizacaoService.atualizar(atualizacaoDto);
	}
	
	public void deletarPor(Long idParceiro) {
		delecaoService.deletarPor(idParceiro);
	}
}
