package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {
    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final ProdutoRepository repository;
    private final ParceiroNegocioService parceiroNegocioService;

    public ProdutoService(ProdutoRepository repository, ParceiroNegocioService parceiroNegocioService) {
        super();
        this.repository = repository;
        this.parceiroNegocioService = parceiroNegocioService;
    }

    public Produto salvar(Produto entidade) {
        if (parceiroNegocioService.buscarPorId(entidade.getFabricante().getId()) == null) {
            LOG.error("O fabricante não existe");
            return null;
        }
        return repository.save(entidade);

    }

}
