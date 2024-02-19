package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
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

    private final NotaFiscalRepository notaFiscal;

    public NotaFiscalService(NotaFiscalRepository repository, ParceiroNegocioRepository parceiroNegocioRepository,
                    NotaFiscalRepository notaFiscal) {
        super();
        this.repository = repository;
        this.parceiroNegocioRepository = parceiroNegocioRepository;
        this.notaFiscal = notaFiscal;
    }

    /*
     * public NotaFiscalDto salvar(NotaFiscalDto notaFiscal) { NotaFiscal nota =
     * converteParaEntidade(notaFiscal); ParceiroNegocio fabricanteSalvo =
     * parceiroNegocioRepository.save(nota.getParceiroNegocio());
     * nota.setParceiroNegocio(fabricanteSalvo); repository.save(nota); return
     * converteParaDto(nota); }
     */

    public NotaFiscalDto salvar(NotaFiscalDto cabecalhoDto) {
        NotaFiscal cabecalho = converteParaEntidade(cabecalhoDto);

        for (ItemNotaFiscal item : cabecalho.getItens()) {
            item.setValorTotal(item.getQuantidade() * item.getPrecoUnitario());
        }

        ParceiroNegocio parceiroNegocio = cabecalho.getParceiroNegocio();
        if (parceiroNegocio != null && parceiroNegocio.getId() == null) {
            parceiroNegocio = parceiroNegocioRepository.save(parceiroNegocio);
            cabecalho.setParceiroNegocio(parceiroNegocio);
        }

        Double valorTotal = calcularValorTotal(cabecalho);
        cabecalho.setValorTotal(valorTotal);

        NotaFiscal entidadeSalva = repository.save(cabecalho);

        return converteParaDto(entidadeSalva);
    }

    public NotaFiscalDto buscarPorId(Integer id) {
        NotaFiscal cabecalho = repository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));

        Double valorTotal = calcularValorTotal(cabecalho);
        cabecalho.setValorTotal(valorTotal);

        return converteParaDto(cabecalho);
    }

    /*
     * public NotaFiscalDto update(Integer id, NotaFiscalDto dto) { return
     * repository.findById(id).map(notaFiscalExistente -> {
     * notaFiscalExistente.setData(dto.getData());
     * notaFiscalExistente.setTipo(dto.getTipo());
     * notaFiscalExistente.setNumero(dto.getNumero()); ParceiroNegocio
     * parceiroExistente = dto.getParceiroNegocio().getId() != null ?
     * parceiroNegocioRepository.findById(dto.getParceiroNegocio().getId()).
     * orElse(null) : null; if (parceiroExistente == null) { ParceiroNegocio
     * fabricanteSalvo =
     * parceiroNegocioRepository.save(dto.getParceiroNegocio());
     * notaFiscalExistente.setParceiroNegocio(parceiroExistente); } else {
     * notaFiscalExistente.setParceiroNegocio(parceiroExistente); } NotaFiscal
     * notaFiscalUpdate = repository.save(notaFiscalExistente); return
     * converteParaDto(notaFiscalUpdate); }).orElseThrow(() -> {
     * LOG.info("Nao foi possivel encontrar a nota fiscal pelo ID {}", id);
     * return new EntidadeNaoEncontradaException("NOTA FISCAL com o ID " + id +
     * "nao foi encontrada"); }); }
     */

    public NotaFiscalDto update(Integer id, NotaFiscalDto dto) {
        return repository.findById(id).map(cabecalhoExistente -> {

            LOG.debug("DTO: {}", dto);

            cabecalhoExistente.setData(dto.getData());
            cabecalhoExistente.setTipo(dto.getTipo());
            cabecalhoExistente.setNumero(dto.getNumero());

            ParceiroNegocio parceiroNegocioExistente = dto.getParceiroNegocio().getId() != null
                            ? parceiroNegocioRepository.findById(dto.getParceiroNegocio().getId()).orElse(null)
                            : null;

            LOG.debug("ParceiroNegocio entity before update: {}", parceiroNegocioExistente);

            if (parceiroNegocioExistente == null) {
                ParceiroNegocio novoParceiroNegocio = dto.getParceiroNegocio();
                parceiroNegocioExistente = parceiroNegocioRepository.save(novoParceiroNegocio);
            }

            LOG.debug("ParceiroNegocio entity after update: {}", parceiroNegocioExistente);

            cabecalhoExistente.setParceiroNegocio(parceiroNegocioExistente);

            NotaFiscal cabecalhoAtualizado = repository.save(cabecalhoExistente);

            LOG.debug("CabecalhoNota entity after update: {}", cabecalhoAtualizado);

            return converteParaDto(cabecalhoAtualizado);
        }).orElseThrow(() -> {
            LOG.info("Não foi possível encontrar o cabecalho pelo ID {}", id);
            return new EntidadeNaoEncontradaException("Cabechalho com o ID " + id + " não encontrado");
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

    private Double calcularValorTotal(NotaFiscal cabecalho) {
        return cabecalho.getItens().stream().mapToDouble(item -> item.getQuantidade() * item.getPrecoUnitario()).sum();
    }

    public static NotaFiscal converteParaEntidade(NotaFiscalDto dto) {
        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
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
