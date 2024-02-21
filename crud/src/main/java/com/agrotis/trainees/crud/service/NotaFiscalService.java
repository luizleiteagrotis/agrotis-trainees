package com.agrotis.trainees.crud.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.wrapper.NotaFiscalWrapper;

@Service
public class NotaFiscalService {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscal.class);

    private final NotaFiscalRepository repository;
    private final NotaFiscalTipoService tipoService;
    private final NotaFiscalWrapper notaFiscalWrapper;

    public NotaFiscalService(NotaFiscalRepository repository, NotaFiscalTipoService tipoService,
                    NotaFiscalWrapper notaFiscalWrapper) {
        super();
        this.repository = repository;
        this.tipoService = tipoService;
        this.notaFiscalWrapper = notaFiscalWrapper;
    }

    public NotaFiscalDto salvar(NotaFiscalDto dto) {
        NotaFiscal entidade = notaFiscalWrapper.converterParaEntidade(dto);
        gerarNumero(entidade);
        entidade = repository.save(entidade);
        return notaFiscalWrapper.converterParaDto(entidade);
    }

    public NotaFiscalDto atualizar(NotaFiscal entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do produto.");
        }

        NotaFiscal nota = buscarPorId(entidade.getId());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.map(entidade, nota);

        return notaFiscalWrapper.converterParaDto(repository.save(nota));
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

    public List<NotaFiscal> listarPorTipo(Integer idTipo) {

        NotaFiscalTipo tipo = tipoService.buscarPorId(idTipo);
        return repository.findByTipo(tipo);
    }

    public List<NotaFiscal> listarPorNumero(Integer numero) {
        return repository.findByNumero(numero);
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("id: {} deletado com sucesso", id);
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void gerarNumero(NotaFiscal notaFiscal) {
        int tipo = notaFiscal.getTipo().getId();
        if (notaFiscal.getTipo() != null) {

            if (tipo == 1 || tipo == 2) {

                Integer ultimoNumero = obterUltimoNumeroPorTipo(notaFiscal);
                notaFiscal.setNumero((ultimoNumero != null) ? ultimoNumero + 1 : 1);
            }

        }
    }

    @Transactional
    public Integer obterUltimoNumeroPorTipo(NotaFiscal nota) {

        try {
            return repository.findMaxNumeroByTipo(nota.getTipo()).orElse(null);
        } catch (NoResultException e) {
            return null;
        }
    }

    public void atualizarValorTotalNota(NotaFiscalItem item) {
        NotaFiscal nota = buscarPorId(item.getIdNota().getId());
        nota.setValorTotal(nota.getValorTotal().add(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade()))));

        salvar(notaFiscalWrapper.converterParaDto(nota));
    }

}
