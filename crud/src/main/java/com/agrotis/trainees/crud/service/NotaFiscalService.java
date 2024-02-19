package com.agrotis.trainees.crud.service;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioTipoRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository, NotaFiscalItemRepository notaFiscalItemRepository,
                    ParceiroNegocioTipoRepository parceiroNegocioTipoRepository) {
        this.notaFiscalRepository = notaFiscalRepository;
    }

    @Transactional
    public NotaFiscal salvar(Integer id) throws NotFoundException {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return notaFiscalRepository.save(notaFiscal);
    }

    public NotaFiscalDto salvar(NotaFiscalDto dto) throws NotFoundException {
        NotaFiscal entidade = converterParaEntidade(dto);
        NotaFiscal savedNotaFiscal = notaFiscalRepository.save(entidade);
        // atualizarValorTotalNotaFiscal(itens);
        LOG.info("Salva nota fiscal {}", savedNotaFiscal.getNumero());
        return converterParaDto(savedNotaFiscal);
    }

    public NotaFiscalDto buscarPorId(Integer id) throws NotFoundException {
        NotaFiscal entidade = notaFiscalRepository.findById(id).orElseThrow(() -> new NotFoundException());
        Hibernate.initialize(entidade.getItensNota());
        return converterParaDto(entidade);
    }

    public NotaFiscal buscarPorNotaFiscalTipo(NotaFiscalTipo tipoNota) {
        return notaFiscalRepository.findByNotaFiscalTipo(tipoNota).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o tipo de {}.", tipoNota);
            return null;
        });
    }

    public NotaFiscal buscarPorParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        return notaFiscalRepository.findByParceiroNegocio(parceiroNegocio).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o parceiro {}.", parceiroNegocio);
            return null;
        });
    }

    public NotaFiscal buscarPorNumero(Integer numero) {
        return notaFiscalRepository.findByNumero(numero).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o número {}.", numero);
            return null;
        });
    }

    public NotaFiscal buscarPorData(LocalDate dataEmissao) {
        return notaFiscalRepository.findByDataEmissao(dataEmissao).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para a data de {}.", dataEmissao);
            return null;
        });
    }

    public List<NotaFiscalDto> listarTodos() {
        List<NotaFiscal> entidades = notaFiscalRepository.findAll();
        return entidades.stream().map(entidade -> converterParaDto(entidade)).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        notaFiscalRepository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

    @Transactional
    public void atualizarValorTotal(Integer id, Double novoValorTotal) throws NotFoundException {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id).orElseThrow(() -> new NotFoundException());

        List<NotaFiscalItem> itensNota = notaFiscal.getItensNota();

        for (NotaFiscalItem item : itensNota) {
            double valorItem = item.getPreco_unitario();

            if (notaFiscal.getNotaFiscalTipo() == NotaFiscalTipo.ENTRADA) {
                novoValorTotal += valorItem;
            } else if (notaFiscal.getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
                novoValorTotal -= valorItem;
            }
        }

        notaFiscal.setValorTotal(novoValorTotal);
        notaFiscalRepository.save(notaFiscal);
    }

    @Transactional
    public NotaFiscal inserir(@Valid NotaFiscalDto dto) throws NotFoundException {
        NotaFiscal entidade = converterParaEntidade(dto);
        double novoValorTotal = calcularValorTotal(dto.getItensNota());
        entidade.setValorTotal(novoValorTotal);

        return notaFiscalRepository.save(entidade);
    }

    @Transactional
    public NotaFiscalDto atualizar(NotaFiscalDto dto) throws NotFoundException {
        NotaFiscal entidade = converterParaEntidade(dto);
        double novoValorTotal = calcularValorTotal(dto.getItensNota());
        entidade.setValorTotal(novoValorTotal);

        return converterParaDto(notaFiscalRepository.save(entidade));
    }

    private double calcularValorTotal(List<NotaFiscalItem> itens) {
        return itens.stream().mapToDouble(item -> {
            double valorItem = item.getPreco_unitario();
            return (item.getNotaFiscal().getNotaFiscalTipo() == NotaFiscalTipo.ENTRADA) ? valorItem : -valorItem;
        }).sum();
    }

    public NotaFiscalDto converterParaDto(NotaFiscal entidade) {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(entidade.getId());
        dto.setNotaFiscalTipo(entidade.getNotaFiscalTipo());
        dto.setParceiroNegocio(entidade.getParceiroNegocio());
        dto.setNumero(entidade.getNumero());
        dto.setDataEmissao(entidade.getDataEmissao());
        dto.setItensNota(entidade.getItensNota());
        dto.setValorTotal(entidade.getValorTotal());

        return dto;
    }

    public NotaFiscal converterParaEntidade(NotaFiscalDto dto) throws NotFoundException {
        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setNotaFiscalTipo(dto.getNotaFiscalTipo());

        ParceiroNegocio parceiroNegocio = dto.getParceiroNegocio();

        entidade.setParceiroNegocio(parceiroNegocio);
        entidade.setNumero(dto.getNumero());
        entidade.setDataEmissao(dto.getDataEmissao());
        entidade.setItensNota(dto.getItensNota());
        entidade.setValorTotal(dto.getValorTotal());

        return entidade;
    }
}
