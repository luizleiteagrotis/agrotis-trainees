package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItem.class);

    private final NotaFiscalItemRepository repository;

    public NotaFiscalItemService(NotaFiscalItemRepository repository) {
        super();
        this.repository = repository;
    }

    public NotaFiscalItem salvar(NotaFiscalItem entidade) {
        return repository.save(entidade);
    }

    public NotaFiscalItem buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Informações não encontradas para o id {}", id);
            return null;
        });
    }

    public NotaFiscalItem buscarPorProduto(Produto produto) {
        return repository.findByProduto(produto).orElseGet(() -> {
            LOG.error("Informações não encontradas para o produto {}", produto);
            return null;
        });
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("id: {} deletado com sucesso", id);
    }

    public List<NotaFiscalItem> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Double> obterOValorTotalDaNota() {
        try {
            return repository.sumAllTotal();
        } catch (NoResultException e) {
            return null;
        }
    }
}
