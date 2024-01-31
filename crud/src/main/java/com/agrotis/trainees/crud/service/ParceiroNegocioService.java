package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.CrudApplication;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@Service
public class ParceiroNegocioService {
	
	private ParceiroNegocioRepository repository;
	
	private final Logger LOG = LoggerFactory
			.getLogger(ParceiroNegocioService.class);
	
	@Autowired
	public ParceiroNegocioService(ParceiroNegocioRepository repository) {
		this.repository = repository;
	}
	
	public ParceiroNegocio salvar(ParceiroNegocio entidade) {
		LOG.info("Tentando salvar parceiro");
		entidade = repository.save(entidade);
		LOG.info("Entidade salva com sucesso. ID: {}", entidade.getId());
		return entidade;
	}
	
	public ParceiroNegocio buscarPorId(long idParceiro) {
		LOG.info("Tentando buscar parceiro com id: {}", idParceiro);
		ParceiroNegocio parceiro = repository.findById(idParceiro).orElseGet(() -> {
			LOG.error("Nota não encontrada para id {}.", idParceiro);
			return null;
		});
		LOG.info("Parceiro encontrado com sucesso. ID: {}", idParceiro);
		return parceiro;
	}
	
	public List<ParceiroNegocio> listarTodos() {
		LOG.info("Tentando buscar todos os parceiros");
		List<ParceiroNegocio> parceiros = repository.findAll();
		LOG.info("Parceiros buscados com sucesso. Quantidade: {}", parceiros.size());
		return parceiros;
	}
	
	public void deletar(long idParceiro) {
		LOG.info("Tentando deletar parceiro. ID: {}", idParceiro);
		repository.deleteById(idParceiro);
		LOG.info("Deletado id {} com sucesso", idParceiro);
	}
}
