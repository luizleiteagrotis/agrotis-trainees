package com.agrotis.trainees.crud.service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);
	
	private final ProdutoRepository repository;

	public ProdutoService(ProdutoRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Produto buscarPeloNome(String nome) {
		return repository.findByNome(nome).orElseGet(() -> {
			LOG.error("O produto nao foi encontrado pelo nome{}", nome);
			return null;
		}); 
	} 
	
	
	public Produto buscarPeloFabricante(String parceiroFabricante) {
		return repository.findByFabricante(parceiroFabricante).orElseGet(() -> {
			LOG.error("O produto nao foi encontrado pelo fabricante {}", parceiroFabricante);
			return null;
		});
	}
	
	
	public Produto salvar(Produto entidade) {
		return repository.save(entidade);
	}
	
	public Produto buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Nao foi possivel encontrar pelo id o Produto{}.", id);
			return null;
		});
	}
	
	public void deletarPorId(Integer id){
		repository.findById(id);
		LOG.info("Deletado com sucesso");
	}
	
	public List<Produto> listarTodos() {
		return repository.findAll();
	}
	
	public Produto update(Integer id, Produto produto) {
		  repository.findById(id).orElseGet(() -> {
			LOG.error(" não foi possivel encontrar pelo Id {}.", produto.getNome());
			return null;
		});
		return repository.save(produto);
	}	

	
}
