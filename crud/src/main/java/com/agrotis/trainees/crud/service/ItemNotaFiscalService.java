package com.agrotis.trainees.crud.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import java.util.List;

@Service
public class ItemNotaFiscalService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);
	
	
private final ItemNotaFiscalRepository repository;
	
	public ItemNotaFiscalService(ItemNotaFiscalRepository repository) {
		super();
		this.repository = repository;
	}
		
 public ItemNotaFiscal salvar (ItemNotaFiscal entidade) {
	return repository.save(entidade);
}
	
 
 public List<ItemNotaFiscal> buscarTodos(){
	return repository.findAll();
}

 public ItemNotaFiscal buscarPorId(Integer id) {
	return repository.findById(id).orElseGet(() -> {
		LOG.info("Nao foi identificada a nota fiscal pelo ID {} ", id);
		return null;
	});
}
	
 public ItemNotaFiscal update(Integer id, ItemNotaFiscal fiscal) {
	  ItemNotaFiscal byId = repository.findById(id).orElseGet(() -> {
		LOG.info("A Nota Fiscal nao foi encontrada pelo ID: {}.", id);
		return null;
	});
	return repository.save(byId);
}	
	
	
 public void deletarPorId(Integer id) {
		repository.deleteById(id);
		LOG.info("Deletado com sucesso!");
	}
	
		
	

}
