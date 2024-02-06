package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.helper.Validador;
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

        if (Validador.validarParceiro(entidade.getFabricante().getId())) {
            return repository.save(entidade);
        }
        LOG.error("O fabricante não existe");
        return null;
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

    public Produto atualizar(Produto produto, int id) {

        Produto byId = buscarPorId(id);
        if (byId != null) {
            byId.setNome(produto.getNome());
            byId.setDescricao(produto.getDescricao());
            byId.setDataFabricacao(produto.getDataFabricacao());
            byId.setDataValidade(produto.getDataValidade());
            byId.setFabricante(produto.getFabricante());
            return repository.save(byId);

        }
        return null;
    }

    public void deletarPorId(int id) {
        if (this.buscarPorId(id) != null) {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
        } else {
            LOG.info("Registro não encontrado");
        }

    }

}
