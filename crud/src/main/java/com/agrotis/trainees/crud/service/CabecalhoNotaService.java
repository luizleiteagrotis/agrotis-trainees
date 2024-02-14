package com.agrotis.trainees.crud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.CabecalhoNotaDto;
import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.utils.DtoUtils;

@Service
public class CabecalhoNotaService {

    private static final Logger LOG = LoggerFactory.getLogger(CabecalhoNotaService.class);

    private final NotaFiscalRepository repository;
    private final ParceiroNegocioRepository parceiroNegocioRepository;

    public CabecalhoNotaService(NotaFiscalRepository repository, ParceiroNegocioRepository parceiroNegocioRepository) {
        this.repository = repository;
        this.parceiroNegocioRepository = parceiroNegocioRepository;
    }

    
    public CabecalhoNotaDto salvar(CabecalhoNotaDto cabecalho) {
        CabecalhoNota entidade = DtoUtils.converteParaEntidade(cabecalho);
        
        ParceiroNegocio fabricanteSalvo = parceiroNegocioRepository.save(entidade.getParceiroNegocio());

        entidade.setParceiroNegocio(fabricanteSalvo);
        
        repository.save(entidade);
        
        return DtoUtils.converteParaDto(entidade);
        
    }

    public CabecalhoNotaDto buscarPorId(Integer id) {
        return repository.findById(id)
                        .map(DtoUtils::converteParaDto)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));
    }

    public List<CabecalhoNotaDto> listarTodos() {
        return repository.findAll()
                        .stream()
                        .map(DtoUtils::converteParaDto)
                        .collect(Collectors.toList());
    }
    
    public CabecalhoNota atualizar(Integer id, CabecalhoNota cabecalhoNota) {
        return repository.findById(id).map(cabecalhoNotaExistente -> {
            cabecalhoNotaExistente.setData(cabecalhoNota.getData());
            cabecalhoNotaExistente.setNotaFiscalTipo(cabecalhoNota.getNotaFiscalTipo());
            cabecalhoNotaExistente.setNumero(cabecalhoNota.getNumero());
            cabecalhoNotaExistente.setParceiroNegocio(cabecalhoNota.getParceiroNegocio());
            return repository.save(cabecalhoNotaExistente);
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));
    }



    public void deletarPorId(Integer id) {
        repository.findById(id).map(entidade -> {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
            return entidade;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));
    }

}
