package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoTipoRepository;

@Service
public class ProdutoTipoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoTipoService.class);

    private ProdutoTipoRepository repository;

    public ProdutoTipoService(ProdutoTipoRepository repository) {
        super();
        this.repository = repository;
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }
}
