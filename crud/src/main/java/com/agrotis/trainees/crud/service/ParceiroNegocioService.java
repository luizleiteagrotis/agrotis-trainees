package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final ParceiroNegocioRepository repository;

    public ParceiroNegocioService(ParceiroNegocioRepository repository) {
        super();
        this.repository = repository;
    }

    public ParceiroNegocioDto salvar(ParceiroNegocioDto dto) {
    	ParceiroNegocio entidade = converterParaEntidade(dto);
        repository.save(entidade);
        LOG.info("Salvo Parceiro de Negócio {}", entidade.getNome());
        return converterParaDto(entidade);
    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Parceiro de negócio não encontrado para id {}.", id);
            return null;
        });
    }

    public ParceiroNegocio buscarPorInscricaoFiscal(String inscricaoFiscal) {
        return repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
            LOG.error("Parceiro de negócio não encontrado para a inscrição fiscal {}.", inscricaoFiscal);
            return null;
        });
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Parceiro de negócio deletado com sucesso");
    }

    public List<ParceiroNegocioDto> listarTodos() {
    	 List<ParceiroNegocio> entidades = repository.findAll();
         return entidades.stream()
                         .map(entidade -> converterParaDto(entidade))
                         .collect(Collectors.toList());
    }
    
    public ParceiroNegocio inserir(@Valid ParceiroNegocio entidade) {
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do parceiro de negócio.");
        }
        return repository.save(entidade);
    }
    
    public ParceiroNegocio atualizar(ParceiroNegocio entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id parceiro de negócio.");
        }
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do parceiro de negócio.");
        }
        return repository.save(entidade);
    }
	
	public static ParceiroNegocioDto converterParaDto(ParceiroNegocio entidade) {
        ParceiroNegocioDto dto = new ParceiroNegocioDto(entidade);
        return dto;
    }

	public static ParceiroNegocio converterParaEntidade(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = new ParceiroNegocio(dto);
        return entidade;
    }
   
	public ParceiroNegocio atualizar(Integer id, ParceiroNegocio entidade) throws Exception {
		ParceiroNegocioDto dto = converterParaDto(entidade);
		if (repository.existsById(dto.getId())) {
			entidade = converterParaEntidade(dto);
	        return repository.save(entidade);
	    } else {
	    	throw new Exception("Parceiro não encontrado");
	    	
	    }
	}

}
