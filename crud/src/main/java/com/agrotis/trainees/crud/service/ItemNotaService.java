package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class ItemNotaService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ItemNotaService.class);
	
	private final NotaFiscalItemRepository repository;

	public ItemNotaService(NotaFiscalItemRepository repository) {
		this.repository = repository;
	}

	public ItemNota salvar(ItemNota entidade) {
		return repository.save(entidade);
	}
	
	public List<ItemNota> buscarTodos(){
		return repository.findAll();
	}
	
	public ItemNota buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.info("Não foi possível encontrar a nota fiscal pelo ID {}", id);
			return null;
		});
	}
	
	public ItemNota atualizar(Integer id, ItemNota notaFiscalItem) {
		ItemNota byId = repository.findById(id).orElseGet(() -> {
			LOG.info("Não foi possível encontrar a nota fiscal pelo ID {}", id);
			return null;
		});
		return repository.save(byId);
	}
	
	public void deletarPorId(Integer id) {
		repository.deleteById(id);
		LOG.info("Deletado com sucesso");
	}

    public void calcularValorTotal(ItemNota notaFiscalItem) {
        Integer quantidade = notaFiscalItem.getQuantidade();
        Double precoUnitario = notaFiscalItem.getPrecoUnitario();
        if (quantidade != null && precoUnitario != null) {
            Double valorTotal = quantidade * precoUnitario;
            notaFiscalItem.setValorTotal(valorTotal);
        }
    }
	
	
}

	

