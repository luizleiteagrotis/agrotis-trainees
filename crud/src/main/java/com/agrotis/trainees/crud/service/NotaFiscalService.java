package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.CrudException;
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
        return repository.save(entidade);
    }

    public NotaFiscal buscarPorId(Integer integer) {
        return repository.findById(integer).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para id {}.", integer);
            return null;
        });
    }

    public NotaFiscal buscarPorNotaFiscalTipo(NotaFiscalTipo tipoNota) {
        return repository.findByNotaFiscalTipo(tipoNota).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o tipo de {}.", tipoNota);
            return null;
        });
    }

    public NotaFiscal buscarPorParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        return repository.findByParceiroNegocio(parceiroNegocio).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o parceiro {}.", parceiroNegocio);
            return null;
        });
    }

    public NotaFiscal buscarPorNumero(Integer numeroNota) {
        return repository.findByNumeroNota(numeroNota).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o número {}.", numeroNota);
            return null;
        });
    }

    public NotaFiscal buscarPorData(LocalDate dataNota) {
        return repository.findByDataNota(dataNota).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para a data de {}.", dataNota);
            return null;
        });
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer integer) {
        repository.deleteById(integer);
        LOG.info("Deletado com sucesso");
    }

    private void atualizarNotaFiscal(NotaFiscal notaFiscal) {
        repository.save(notaFiscal);
    }

    public void adicionarItem(NotaFiscalItem item) {
        NotaFiscal nota = item.getNotaFiscal();
        // BigDecimal
        double novoValorTotal = nota.getValorTotal() + item.getValorTotal();
        nota.setValorTotal(novoValorTotal);
        atualizarNotaFiscal(nota);
    }

    public void atualizarItem(NotaFiscalItem item) {
        NotaFiscal nota = item.getNotaFiscal();
        double novoValorTotal = item.getValorTotal() - item.getValorTotal();
        nota.setValorTotal(nota.getValorTotal() + novoValorTotal);
        atualizarNotaFiscal(nota);
    }
    
    public NotaFiscal inserir(NotaFiscal entidade) {
        if (StringUtils.isEmpty(entidade.getNumeroNota())) {
            throw new CrudException("Obrigatório preencher o número da nota fiscal.");
        }
        return repository.save(entidade);
    }
	
	public NotaFiscal atualizar(NotaFiscal entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id da nota fiscal.");
        }
        if (StringUtils.isEmpty(entidade.getNumeroNota())) {
            throw new CrudException("Obrigatório preencher o número da nota fiscal.");
        }
        return repository.save(entidade);
    }

}