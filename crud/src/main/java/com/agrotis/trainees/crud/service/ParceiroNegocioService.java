package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);
    private ParceiroNegocioConversaoService conversao;
    private ParceiroNegocioRepository repository;

    public ParceiroNegocioService(ParceiroNegocioConversaoService conversao, ParceiroNegocioRepository repository) {
        super();
        this.conversao = conversao;
        this.repository = repository;
    }

    @Transactional
    public ParceiroNegocioDto salvar(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = conversao.converterParaEntidade(dto);
        repository.save(entidade);
        LOG.info("Salvo Parceiro de Negócio {}", entidade.getNome());
        return conversao.converterParaDto(entidade);
    }

    public ParceiroNegocioDto buscarPorId(Integer id) throws NotFoundException {
        ParceiroNegocio entidade = repository.findById(id).orElseThrow(() -> new NotFoundException());
        return conversao.converterParaDto(entidade);
    }

    @Transactional
    public ParceiroNegocioDto buscarPorInscricaoFiscal(String inscricaoFiscal) throws NotFoundException {
        ParceiroNegocio entidade = repository.findByInscricaoFiscal(inscricaoFiscal).orElseThrow(() -> new NotFoundException());
        return conversao.converterParaDto(entidade);
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Parceiro de negócio deletado com sucesso");
    }

    public List<ParceiroNegocioDto> listarTodos() {
        List<ParceiroNegocio> entidades = repository.findAll();
        return entidades.stream().map(entidade -> conversao.converterParaDto(entidade)).collect(Collectors.toList());
    }

    public ParceiroNegocio inserir(@Valid ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = conversao.converterParaEntidade(dto);
        return repository.save(entidade);
    }

    public ParceiroNegocioDto atualizar(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = conversao.converterParaEntidade(dto);
        return conversao.converterParaDto(repository.save(entidade));
    }

}
