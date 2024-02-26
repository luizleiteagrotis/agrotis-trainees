package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
        ParceiroNegocio fabricanteSalvo = salvarOuBuscarFabricante(parceiroNegocio);
        cabecalho.setParceiroNegocio(fabricanteSalvo);

        // Calcula o valor total do cabeçalho da nota
        BigDecimal valorTotal = calcularValorTotal(cabecalho);
        cabecalho.setValorTotal(valorTotal);

        // Salva o cabeçalho da nota no banco de dados
        CabecalhoNota entidadeSalva = repository.save(cabecalho);

        return DtoUtils.converteParaDto(entidadeSalva);
    }

    public CabecalhoNotaDto buscarPorId(Integer id) {
        CabecalhoNota cabecalho = repository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));

        BigDecimal valorTotal = calcularValorTotal(cabecalho);
        cabecalho.setValorTotal(valorTotal);

        return DtoUtils.converteParaDto(cabecalho);
    }

    public List<CabecalhoNotaDto> listarTodos() {
        return repository.findAll().stream().map(DtoUtils::converteParaDto).collect(Collectors.toList());
    }

    public CabecalhoNotaDto atualizar(Integer id, CabecalhoNotaDto dto) {
        return repository.findById(id).map(cabecalhoExistente -> {
            // Debugging: Log the current state of the DTO before updating
            LOG.debug("DTO before update: {}", dto);

            cabecalhoExistente.setData(dto.getData());
            cabecalhoExistente.setNotaFiscalTipo(dto.getNotaFiscalTipo());
            cabecalhoExistente.setNumero(dto.getNumero());

            ParceiroNegocio parceiroNegocioExistente = dto.getParceiroNegocio().getId() != null
                            ? parceiroNegocioRepository.findById(dto.getParceiroNegocio().getId()).orElse(null)
                            : null;

            // Debugging: Log the current state of the ParceiroNegocio entity
            LOG.debug("ParceiroNegocio entity before update: {}", parceiroNegocioExistente);

            if (parceiroNegocioExistente == null) {
                ParceiroNegocio novoParceiroNegocio = dto.getParceiroNegocio();
                parceiroNegocioExistente = parceiroNegocioRepository.save(novoParceiroNegocio);
            }

            // Debugging: Log the final state of the ParceiroNegocio entity
            LOG.debug("ParceiroNegocio entity after update: {}", parceiroNegocioExistente);

            cabecalhoExistente.setParceiroNegocio(parceiroNegocioExistente);

            CabecalhoNota cabecalhoAtualizado = repository.save(cabecalhoExistente);

            // Debugging: Log the final state of the updated CabecalhoNota
            // entity
            LOG.debug("CabecalhoNota entity after update: {}", cabecalhoAtualizado);

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

    private BigDecimal calcularValorTotal(CabecalhoNota cabecalho) {
        return cabecalho.getItens().stream().map(item -> item.getQuantidade().multiply(item.getPrecoUnitario()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private ParceiroNegocio salvarOuBuscarFabricante(ParceiroNegocio fabricante) {
        if (fabricante.getId() != null) {
            return parceiroNegocioRepository.findById(fabricante.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException(
                            "Fabricante com o ID " + fabricante.getId() + " não encontrado"));
        } else {
            return parceiroNegocioRepository.save(fabricante);
        }
    }

}
