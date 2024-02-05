package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.exception.NotaFiscalDuplicadaException;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository repository) {
        super();
        this.repository = repository;
    }

    public NotaFiscal salvar(NotaFiscal entidade) {

        if (repository.hasDuplicates(entidade.getNumero(), entidade.getNotaFiscalTipo())) {
            throw new NotaFiscalDuplicadaException("Nota fiscal já existe");
        }
        return repository.save(entidade);
    }

    public NotaFiscal buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Nota não encontrada para id {}", id);
            return null;
        });
    }

    public List<NotaFiscal> buscarPorNumero(String numero) {
        return repository.findAllByNumero(numero);
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

    @Transactional
    public void adicionarItem(ItemNotaFiscal item, NotaFiscal notaFiscal) {
        List<ItemNotaFiscal> itemNotaFiscals = notaFiscal.getItemNotaFiscals();
        if (itemNotaFiscals == null) {
            itemNotaFiscals = new ArrayList<>();
        }
        itemNotaFiscals.add(item);
        atualizarValorTotal(notaFiscal);
    }

    public void removerItem(ItemNotaFiscal item, NotaFiscal notaFiscal) {
        List<ItemNotaFiscal> itemNotaFiscals = notaFiscal.getItemNotaFiscals();
        itemNotaFiscals.remove(item);
        atualizarValorTotal(notaFiscal);
    }

    public void atualizarValorTotal(NotaFiscal notaFiscal) {
        BigDecimal valor_total = notaFiscal.getValor_total();
        valor_total = BigDecimal.ZERO;
        for (ItemNotaFiscal item : notaFiscal.getItemNotaFiscals()) {
            if (item.getValor_total() != null) {
                valor_total = valor_total.add(item.getValor_total());
            }
        }

        notaFiscal.calcularValorTotal(valor_total);
        repository.save(notaFiscal);
    }

}
