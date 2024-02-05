package com.agrotis.trainees.crud.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;

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
		
	
	
	
	
	
	
	
		
	

}
