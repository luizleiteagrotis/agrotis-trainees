package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscal.class);

    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository repository) {
        super();
        this.repository = repository;
    }

    public NotaFiscal salvar(NotaFiscal entidade) {
        return repository.save(entidade);
    }

    public NotaFiscal buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Informações não encontradas para o id {}", id);
            return null;
        });
    }

    public NotaFiscal buscarPorTipoeNumero(NotaFiscalTipo tipo, Integer numero) {
        return repository.findByTipoAndNumero(tipo, numero).orElseGet(() -> {
            LOG.error("Informações não encontradas para o id {} e numero de nota {}", tipo, numero);
            return null;
        });
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("id: {} deletado com sucesso", id);
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void gerarNumero(NotaFiscal notaFiscalCampos) {
        int tipo = notaFiscalCampos.getTipo().getId();
        if (notaFiscalCampos.getTipo() != null) {

            if (tipo == 1 || tipo == 2) {

                Integer ultimoNumero = obterUltimoNumeroPorTipo(notaFiscalCampos.getTipo());
                notaFiscalCampos.setNumero((ultimoNumero != null) ? ultimoNumero + 1 : 1);
            }

        }
    }

    @Transactional
    public Integer obterUltimoNumeroPorTipo(NotaFiscalTipo tipo) {
        try {
            return repository.findMaxNumeroByTipo(tipo).orElse(null);
        } catch (NoResultException e) {
            return null;
        }
    }

    public void atualizarValorTotalNota(NotaFiscalItem item) {
        NotaFiscal nota = buscarPorId(item.getIdNota().getId());
        nota.setValorTotalNota(nota.getValorTotalNota() + item.getQuantidade() * item.getPrecoUnitario());
        salvar(nota);
    }
}
