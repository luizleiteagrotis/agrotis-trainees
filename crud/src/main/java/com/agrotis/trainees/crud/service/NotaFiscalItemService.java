package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ParceiroNegocioService.class);
	
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
			LOG.error("Item da nota fiscal não encontrado para o id {}.", id);
			return null;
		});
	}
	
	public NotaFiscalItem buscarPorNotaFiscalId (NotaFiscal notaFiscal ) {
		return repository.findByNotaFiscalId(notaFiscal).orElseGet(() -> {
			LOG.error("Item da nota fiscal não encontrado para a NF {}.", notaFiscal);
			return null;
		});
	}
	
	public NotaFiscalItem buscarPorNotaFiscalNum (NotaFiscal notaFiscal ) {
		return repository.findByNotaFiscalNum(notaFiscal).orElseGet(() -> {
			LOG.error("Item da nota fiscal não encontrado para a NF {}.", notaFiscal);
			return null;
		});
	}
	
	public NotaFiscalItem buscarPorProduto (Produto produto) {
		return repository.findByProduto(produto).orElseGet(() -> {
			LOG.error("Item da nota fiscal não encontrado para o produto {}.", produto);
			return null;
		});
	}
	
	public List<NotaFiscalItem> listarTodos() {
		return repository.findAll();
	}
	
	public void deletarPorId(Integer id){
		repository.deleteById(id);
		LOG.info("Deletado com sucesso");
	}
	
}
