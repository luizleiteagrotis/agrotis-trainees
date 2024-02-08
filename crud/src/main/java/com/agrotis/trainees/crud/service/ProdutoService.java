package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.DescricaoExisteException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(Produto.class);

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        super();
        this.repository = repository;
    }

    public Produto salvar(Produto entidade) {
        try {
            verificarDescricao(entidade);
            return repository.save(entidade);
        } catch (DescricaoExisteException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }

    }

    public Produto buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Informações não encontradas para o id {}. ", id);
            return null;
        });
    }

    public Produto buscarPorDescricao(String descricao) {
        return repository.findByDescricao(descricao).orElseGet(() -> {
            LOG.error("Informações não encontradas para a descrição {}. ", descricao);
            return null;
        });
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("id: {} deletado com sucesso ", id);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public void verificarDescricao(Produto entidade) throws DescricaoExisteException {
        if (repository.existsByDescricaoAndIdNot(entidade.getDescricao(), entidade.getId()) == true) {
            throw new DescricaoExisteException("A descrição já existe");
        }

    }
}
