package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final NotaFiscalRepository repository;

    private final ParceiroNegocioRepository parceiroNegocioRepository;

    public NotaFiscalService(NotaFiscalRepository repository, ParceiroNegocioRepository parceiroNegocioRepository) {
        super();
        this.repository = repository;
        this.parceiroNegocioRepository = parceiroNegocioRepository;
    }

    public NotaFiscalDto salvar(NotaFiscalDto cabecalhoNota) {
        NotaFiscal nota = converteParaEntidade(cabecalhoNota);
        ParceiroNegocio fabricanteSalvo = parceiroNegocioRepository.save(nota.getParceiroNegocio());
        nota.setParceiroNegocio(fabricanteSalvo);
        repository.save(nota);
        return converteParaDto(nota);
    }

    public NotaFiscalDto buscarPorId(Integer id) {
        return repository.findById(id).map(NotaFiscalService::converteParaDto).orElseThrow(
                        () -> new EntidadeNaoEncontradaException("A NOTA FISCAL com o ID: " + id + "nao foi encontrada!"));
    }

    public NotaFiscalDto update(Integer id, NotaFiscalDto dto) {
        return repository.findById(id).map(notaFiscalExistente -> {
            notaFiscalExistente.setData(dto.getData());
            notaFiscalExistente.setTipo(dto.getTipo());
            notaFiscalExistente.setNumero(dto.getNumero());
            ParceiroNegocio parceiroExistente = dto.getParceiroNegocio().getId() != null
                            ? parceiroNegocioRepository.findById(dto.getParceiroNegocio().getId()).orElse(null)
                            : null;
            if (parceiroExistente == null) {
                ParceiroNegocio fabricante = parceiroNegocioRepository.save(dto.getParceiroNegocio());
                notaFiscalExistente.setParceiroNegocio(parceiroExistente);
            } else {
                notaFiscalExistente.setParceiroNegocio(parceiroExistente);
            }
            NotaFiscal notaFiscalUpdate = repository.save(notaFiscalExistente);
            return converteParaDto(notaFiscalUpdate);
        }).orElseThrow(() -> {
            LOG.info("Nao foi possivel encontrar a nota fiscal pelo ID {}", id);
            return new EntidadeNaoEncontradaException("NOTA FISCAL com o ID " + id + "nao foi encontrada");
        });
    }

    public List<NotaFiscalDto> listarTodos() {
        return repository.findAll().stream().map(NotaFiscalService::converteParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(nota -> {
            repository.deleteById(id);
            LOG.info("Nota Fiscal" + id + "Deletada com sucesso");
            return nota;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("NOTA FISCAL com o ID " + id + " não foi encontrada!"));
    }

    public static NotaFiscal converteParaEntidade(NotaFiscalDto dto) {
        NotaFiscal entidade = new NotaFiscal();

        entidade.setData(dto.getData());
        entidade.setNumero(dto.getNumero());
        entidade.setTipo(dto.getTipo());
        entidade.setParceiroNegocio(dto.getParceiroNegocio());
        entidade.setValorTotal(dto.getValorTotal());

        return entidade;
    }

    public static NotaFiscalDto converteParaDto(NotaFiscal entidade) {
        NotaFiscalDto dto = new NotaFiscalDto();

        dto.setId(entidade.getId());
        dto.setData(entidade.getData());
        dto.setNumero(dto.getNumero());
        dto.setTipo(entidade.getTipo());
        dto.setParceiroNegocio(dto.getParceiroNegocio());
        dto.setValorTotal(dto.getValorTotal());

        return dto;
    }

}
