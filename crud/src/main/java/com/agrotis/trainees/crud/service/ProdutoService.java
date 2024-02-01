package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
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

    public Produto buscarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Nota não encontrada para id {}.", id);
            return null;
        });
    }

    public List<Produto> buscarPorFornecedor(ParceiroNegocio parceiro) {
        return repository.findByFabricante(parceiro);
    }

    public List<Produto> buscarPorDataFabricacao(Date data) {
        return repository.findByDataFabricacao(data);
    }

    public List<Produto> buscarPorDataValidade(Date data) {
        return repository.findByDataValidade(data);
    }

    public List<Produto> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

}
