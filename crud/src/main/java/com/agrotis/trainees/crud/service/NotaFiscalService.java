package com.agrotis.trainees.crud.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import java.util.List;


@Service
public class NotaFiscalService {
	
private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);
	
	private final NotaFiscalRepository repository;
	
	public NotaFiscalService(NotaFiscalRepository repository) {
		super();
		this.repository = repository;
	}
	
	public NotaFiscal salvar(NotaFiscal entidade) {
		return repository.save(entidade);
	}
	
	public NotaFiscal buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Nota Fiscal nao encontrada para o ID {} ", id);
			return null;
		});
	}
	
	public NotaFiscal update(Integer id, NotaFiscal fiscal) {
		  NotaFiscal byId = repository.findById(id).orElseGet(() -> {
			LOG.info("A Nota Fiscal nao foi encontrada pelo ID: {}.", id);
			return null;
		});
		return repository.save(byId);
	}
	
	public List<NotaFiscal> listarTodos(){
		return repository.findAll();
	}

	
	
	
}
