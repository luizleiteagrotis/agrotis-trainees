package com.agrotis.trainees.crud.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(ProdutoService.class);
	
	private final ProdutoRepository repository;

	public ProdutoService(ProdutoRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Produto salvar(Produto entidade) {
		return repository.save(entidade);
	}
	
	public Produto buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Produto não encontrado para id {}.", id);
			return null;
		});
	}

	public Produto buscarPorParceiro(ParceiroNegocio parceiroNegocio) {
	    return repository.findByParceiroNegocio(parceiroNegocio).orElseGet(() -> {
	        LOG.error("Produto não encontrado para o parceiro {}.", parceiroNegocio);
	        return null;
	    });
	}
	
	public Produto buscarPorFabricante(String fabricante) {
		return repository.findByFabricante(fabricante).orElseGet(() -> {
			LOG.error("Produto não encontrado para o fabricante {}.", fabricante);
			return null;
		});
	}
	
	public Produto buscarPorDataFabricacao(LocalDate dataFabricacao) {
		return repository.findByDataFabricacao(dataFabricacao).orElseGet(() -> {
			LOG.error("Produto não encontrado para data de fabricacao {}.", dataFabricacao);
			return null;
		});
	}
	
	public Produto buscarPorDataValidade(LocalDate dataValidade) {
		return repository.findByDataValidade(dataValidade).orElseGet(() -> {
			LOG.error("Produto não encontrado para data de validade {}.", dataValidade);
			return null;
		});
	}
	
	public List<Produto> listarTodos() {
		return repository.findAll();
	}

	public void deletarPorId(Integer id){
		repository.deleteById(id);
		LOG.info("Deletado com sucesso");
	}
	
	public Produto inserir(Produto entidade) {
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do produto.");
        }
        return repository.save(entidade);
    }
	
	public Produto atualizar(Produto entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id produto.");
        }
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do produto.");
        }
        return repository.save(entidade);
    }
}
