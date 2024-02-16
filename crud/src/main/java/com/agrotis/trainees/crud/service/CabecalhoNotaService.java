package com.agrotis.trainees.crud.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.CabecalhoNotaDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.utils.DtoUtils;

@Service
public class CabecalhoNotaService {

    private static final Logger LOG = LoggerFactory.getLogger(CabecalhoNotaService.class);

    private final CabecalhoNotaRepository repository;
    private final ParceiroNegocioRepository parceiroNegocioRepository;

    public CabecalhoNotaService(CabecalhoNotaRepository repository, ParceiroNegocioRepository parceiroNegocioRepository) {
        this.repository = repository;
        this.parceiroNegocioRepository = parceiroNegocioRepository;
    }

    public CabecalhoNotaDto salvar(CabecalhoNotaDto cabecalhoDto) {
        CabecalhoNota cabecalho = DtoUtils.converteParaEntidade(cabecalhoDto);
        
        ParceiroNegocio parceiroNegocio = cabecalho.getParceiroNegocio();
        if (parceiroNegocio != null && parceiroNegocio.getId() == null) {
            parceiroNegocio = parceiroNegocioRepository.save(parceiroNegocio);
            cabecalho.setParceiroNegocio(parceiroNegocio);
        }

        CabecalhoNota entidadeSalva = repository.save(cabecalho);

        return DtoUtils.converteParaDto(entidadeSalva);
    }


    public CabecalhoNotaDto buscarPorId(Integer id) {
        return repository.findById(id).map(DtoUtils::converteParaDto)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));
    }

    public List<CabecalhoNotaDto> listarTodos() {
        return repository.findAll().stream().map(DtoUtils::converteParaDto).collect(Collectors.toList());
    }

    public CabecalhoNotaDto atualizar(Integer id, CabecalhoNotaDto dto) {
        return repository.findById(id).map(cabecalhoExistente -> {
            cabecalhoExistente.setData(dto.getData());
            cabecalhoExistente.setNotaFiscalTipo(dto.getNotaFiscalTipo());
            cabecalhoExistente.setNumero(dto.getNumero());
            ParceiroNegocio parceiroNegocioExistente = dto.getParceiroNegocio().getId() != null
                            ? parceiroNegocioRepository.findById(dto.getParceiroNegocio().getId()).orElse(null)
                            : null;
            if (parceiroNegocioExistente == null) {
                ParceiroNegocio fabricanteSalvo = parceiroNegocioRepository.save(dto.getParceiroNegocio());
                cabecalhoExistente.setParceiroNegocio(parceiroNegocioExistente);
            } else {
                cabecalhoExistente.setParceiroNegocio(parceiroNegocioExistente);
            }
            CabecalhoNota cabecalhoAtualizado = repository.save(cabecalhoExistente);
            return DtoUtils.converteParaDto(cabecalhoAtualizado);
        }).orElseThrow(() -> {
            LOG.info("Não foi possível encontrar o cabecalho pelo ID {}", id);
            return new EntidadeNaoEncontradaException("Cabechalho com o ID " + id + " não encontrado");
        });
    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(entidade -> {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
            return entidade;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));
    }

    public void atualizarValorTotal(CabecalhoNota cabecalho) {
        repository.save(cabecalho);
        
    }
    
    public CabecalhoNotaDto findByNumero(Integer numeroDaNota) {
        CabecalhoNota byNumber = repository.findByNumero(numeroDaNota).orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada pela busca numérica"));
        return DtoUtils.converteParaDto(byNumber);
    }
    
    
    



}
