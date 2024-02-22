package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository repository) {
        super();
        this.repository = repository;
    }

    @Transactional
    public NotaFiscal salvar(NotaFiscal entidade) {
        if (entidade.getNumero() == null) {
            throw new IllegalArgumentException("O número da nota fiscal é obrigatório.");
        }
        return repository.save(entidade);
    }

    public NotaFiscalDto salvar(NotaFiscalDto dto) {
        NotaFiscal entidade = converterParaEntidade(dto);
        NotaFiscal notaFiscalSalva = repository.save(entidade);
        List<NotaFiscalItem> itens = notaFiscalSalva.getItens();
        atualizarNotaFiscal(itens);
        System.out.println(entidade.getNumero());
        LOG.info("Salva nota fiscal {}", notaFiscalSalva.getNumero());
        return converterParaDto(notaFiscalSalva);
    }

    public NotaFiscalDto buscarPorId(Integer id) throws NotFoundException {
        NotaFiscal entidade = repository.findById(id).orElseThrow(() -> new NotFoundException());
        return converterParaDto(entidade);
    }

    // ajustar métodos de busca

    public NotaFiscal buscarPorTipo(NotaFiscalTipo tipo) {
        return repository.findByTipo(tipo).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o tipo de {}.", tipo);
            return null;
        });
    }

    public NotaFiscal buscarPorParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        return repository.findByParceiroNegocio(parceiroNegocio).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o parceiro {}.", parceiroNegocio);
            return null;
        });
    }

    public NotaFiscal buscarPorNumero(Integer numero) {
        return repository.findByNumero(numero).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o número {}.", numero);
            return null;
        });
    }

    public NotaFiscal buscarPorData(LocalDate data) {
        return repository.findByData(data).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para a data de {}.", data);
            return null;
        });
    }

    public List<NotaFiscalDto> listarTodos() {
        List<NotaFiscal> entidades = repository.findAll();
        return entidades.stream().map(entidade -> converterParaDto(entidade)).collect(Collectors.toList());
    }

    public void deletarPorId(Integer integer) {
        repository.deleteById(integer);
        LOG.info("Deletado com sucesso");
    }

    @Transactional
    public void atualizarNotaFiscal(List<NotaFiscalItem> itens) {
        if (itens.isEmpty()) {
            // Lista vazia para crrigir erro
            return;
        }

        NotaFiscal notaFiscal = itens.get(0).getNotaFiscal();
        BigDecimal novoValorTotal = BigDecimal.ZERO;

        for (NotaFiscalItem item : itens) {
            BigDecimal precoUnitario = item.getPrecoUnitario();
            BigDecimal quantidade = new BigDecimal(item.getQuantidade());
            BigDecimal valorItem = precoUnitario.multiply(quantidade);

            if (notaFiscal.getNotaFiscalTipo() == NotaFiscalTipo.ENTRADA) {
                novoValorTotal = novoValorTotal.add(valorItem);
            } else if (notaFiscal.getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
                novoValorTotal = novoValorTotal.subtract(valorItem);
            }
        }

        notaFiscal.setValorTotal(novoValorTotal);
        repository.save(notaFiscal);
    }

    public NotaFiscal inserir(@Valid NotaFiscalDto dto) {
        NotaFiscal entidade = converterParaEntidade(dto);
        return repository.save(entidade);
    }

    public NotaFiscalDto atualizar(NotaFiscalDto dto) {
        NotaFiscal entidade = converterParaEntidade(dto);
        return converterParaDto(repository.save(entidade));
    }

    public static NotaFiscalDto converterParaDto(NotaFiscal entidade) {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(entidade.getId());
        dto.setNotaFiscalTipo(entidade.getNotaFiscalTipo());
        dto.setParceiroNegocio(ParceiroNegocioService.converterParaDto(entidade.getParceiroNegocio()));
        dto.setNumero(entidade.getNumero());
        dto.setData(entidade.getData());
        dto.setItens(entidade.getItens());
        dto.setValorTotal(entidade.getValorTotal());

        return dto;
    }

    public static NotaFiscal converterParaEntidade(NotaFiscalDto dto) {
        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setNotaFiscalTipo(dto.getNotaFiscalTipo());
        entidade.setParceiroNegocio(ParceiroNegocioService.converterParaEntidade(dto.getParceiroNegocio()));
        entidade.setNumero(dto.getNumero());
        entidade.setData(dto.getData());
        entidade.setItens(dto.getItens());
        entidade.setValorTotal(dto.getValorTotal());

        return entidade;
    }

}