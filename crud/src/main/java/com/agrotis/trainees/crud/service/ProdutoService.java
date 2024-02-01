package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service

public class ProdutoService {

	private static final  Logger LOG = LoggerFactory
			.getLogger(Produto.class);
	
	private final ProdutoRepository repository;
	
	public ProdutoService(ProdutoRepository repository) {
		super ();
		this.repository = repository;
	}
	public Produto salvar(Produto entidade) {
		return repository.save(entidade);
	}
	public Produto buscarPorNome(String nome) {
		return repository.findByNome(nome).orElseGet(() -> {
			LOG.error("Nota não encontrada para o nome {}.", nome);
			return null;
		});
		
	}
	
	public void deletarPorId(Integer id) {
		repository.deleteById(id);
		LOG.info("Deletado com sucesso");
		
	}
	public List<Produto> listarTodos(){
		return repository.findAll();
		
	}
	public Produto buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Nota não encontrada para o id {}. ", id);
			return null;
		});
	}
		public Produto update(Integer id, Produto produto) {
			repository.findById(id).orElseGet(() -> {
				LOG.error("Parceiro de Negócio não encontrado para o Id {}.", produto.getNome());
				return null;
			});
		return repository.save(produto);
	}
		public static List<Produto> buscarTodos() {
			// TODO Auto-generated method stub
			return null;
		}
		public static Produto buscaPeloId(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}
		public static void atualizar(Integer id, Produto produtoAtualizado) {
			// TODO Auto-generated method stub
			
		}
	
	
	
}



















