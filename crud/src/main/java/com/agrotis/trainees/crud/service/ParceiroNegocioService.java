package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
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

    public ParceiroNegocioDto buscarPorId(Integer id) throws NotFoundException {
        ParceiroNegocio entidade = repository.findById(id)
                                             .orElseThrow(() -> new NotFoundException());
        return converterParaDto(entidade);
    }

    public ParceiroNegocioDto buscarPorInscricaoFiscal(String inscricaoFiscal) throws NotFoundException {
        ParceiroNegocio entidade = repository.findByInscricaoFiscal(inscricaoFiscal)
                                             .orElseThrow(() -> new NotFoundException());
        return converterParaDto(entidade);
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
    
    public ParceiroNegocio inserir(@Valid ParceiroNegocioDto dto) {
    	ParceiroNegocio entidade = converterParaEntidade(dto);
        return repository.save(entidade);
    }
    
    public ParceiroNegocioDto atualizar(ParceiroNegocioDto dto) {
    	ParceiroNegocio entidade = converterParaEntidade(dto);
        return converterParaDto(repository.save(entidade));
    }
	
	public static ParceiroNegocioDto converterParaDto(ParceiroNegocio entidade) {
		ParceiroNegocioDto dto= new ParceiroNegocioDto();
		dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setInscricaoFiscal(entidade.getInscricaoFiscal());
        dto.setEndereco(entidade.getEndereco());        
        dto.setTelefone(entidade.getTelefone());   
        
        return dto;
    }

	public static ParceiroNegocio converterParaEntidade(ParceiroNegocioDto dto) {
		ParceiroNegocio entidade = new ParceiroNegocio();
		entidade.setId(dto.getId());
        entidade.setNome(dto.getNome());
        entidade.setInscricaoFiscal(dto.getInscricaoFiscal());
        entidade.setEndereco(dto.getEndereco());        
        entidade.setTelefone(dto.getTelefone());   
        
        return entidade;
    }

}
