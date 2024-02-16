package com.agrotis.trainees.crud.service.cabecalho;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dto.cabecalho.CabecalhoAtualizacaoDto;
import com.agrotis.trainees.crud.dto.cabecalho.CabecalhoCadastroDto;
import com.agrotis.trainees.crud.dto.cabecalho.CabecalhoRetornoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.mapper.cabecalho.CabecalhoMapper;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;

@Service
public class CabecalhoNotaService {
	
	private CabecalhoMapper mapper;
	private CabecalhoNotaRepository repository;
	private final Logger LOGGER;
	
	@Autowired
	public CabecalhoNotaService(CabecalhoMapper mapper, CabecalhoNotaRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
		LOGGER = LoggerFactory.getLogger(CabecalhoNotaService.class);
	}
	
	public CabecalhoRetornoDto salvar(CabecalhoCadastroDto cadastroDto) {
		CabecalhoNota cabecalho = mapper.converterParaEntidade(cadastroDto);
		verificar(cabecalho);
		cabecalho = repository.salvar(cabecalho);
		return mapper.converterParaDto(cabecalho);
	}
	
	private void verificar(CabecalhoNota cabecalho) {
		boolean naoFoiPersistido = cabecalho.getId() == null;
		boolean existeComTipoENumeroIgual = repository.existeInstanciaCom(cabecalho.getTipo(), cabecalho.getNumero());
		if (naoFoiPersistido && existeComTipoENumeroIgual) {
			String nomeClasseCabecalho = cabecalho.getClass().getSimpleName();
			String mensagemErro = "Ja existe " 
									+ nomeClasseCabecalho
									+ " com tipo " 
									+ cabecalho.getTipo()
									+ " e numero "
									+ cabecalho.getNumero();
			LOGGER.error(mensagemErro);
			throw new CabecalhoNotaServiceException(mensagemErro);
		}
	}
	
	public CabecalhoRetornoDto buscar(Long id) {
		CabecalhoNota cabecalho = repository.buscarPor(id);
		return mapper.converterParaDto(cabecalho);
	}

	public Page<CabecalhoRetornoDto> listarTodos(Pageable pageable) {
		return repository.buscarTodos(pageable).map((cabecalho) -> mapper.converterParaDto(cabecalho));
	}
	
	public CabecalhoRetornoDto atualizar(CabecalhoAtualizacaoDto atualizacaoDto) {
		Long idCabecalho = atualizacaoDto.getId();
		CabecalhoNota cabecalho = repository.buscarPor(idCabecalho);
		atualizarCabecalho(atualizacaoDto, cabecalho);
		verificar(cabecalho);
		cabecalho = repository.salvar(cabecalho);
		return mapper.converterParaDto(cabecalho);
	}

	public void deletar(Long id) {
		repository.deletar(id);
	}
	
	private void atualizarCabecalho(CabecalhoAtualizacaoDto atualizacaoDto, CabecalhoNota cabecalho) {
		if (atualizacaoDto.getNumero() != null) {
			cabecalho.setNumero(atualizacaoDto.getNumero());
		}
		if (atualizacaoDto.getDataEmissao() != null) {
			cabecalho.setDataEmissao(atualizacaoDto.getDataEmissao());
		}
	}
}
