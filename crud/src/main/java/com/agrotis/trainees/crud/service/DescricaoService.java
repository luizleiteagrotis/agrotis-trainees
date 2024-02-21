package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.DescricaoExisteException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Component
public class DescricaoService {

    private final ProdutoRepository repository;

    public DescricaoService(ProdutoRepository repository) {
        super();
        this.repository = repository;
    }

    public void verificarDescricaoEId(Produto entidade) throws DescricaoExisteException {
        if (repository.existsByDescricaoAndIdNot(entidade.getDescricao(), entidade.getId()) == true) {
            throw new DescricaoExisteException("A descrição já existe");
        }

    }

    public void verificarDescricao(Produto entidade) throws DescricaoExisteException {
        if (repository.existsByDescricao(entidade.getDescricao()) == true) {
            throw new DescricaoExisteException("A descrição ja existe");
        }
    }
}
