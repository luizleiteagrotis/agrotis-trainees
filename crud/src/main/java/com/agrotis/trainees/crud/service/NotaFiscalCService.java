package com.agrotis.trainees.crud.service;

import java.lang.System.Logger;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscalC;
import com.agrotis.trainees.crud.repository.NotaFiscalCRepository;

import dto.CabecalhoDto;

@Service
public class NotaFiscalCService {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(NotaFiscalCService.class);

	private final NotaFiscalCRepository repository;

	public NotaFiscalCService(NotaFiscalCRepository repository) {
		this.repository = repository;

	}

	public NotaFiscalC salvar(NotaFiscalC entidade) {
		return repository.save(entidade);
	}

	public NotaFiscalC buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			((org.slf4j.Logger) LOG).error("Nota não encontrada para id {}.", id);
			return null;
		});

	}

	public NotaFiscalC atualizar(Integer id, NotaFiscalC negocio) {
		NotaFiscalC byId = repository.findById(id).orElseGet(() -> {
			((org.slf4j.Logger) LOG).info("Não foi possivel encontrar a nota fiscal pelo ID {}", id);
			return null;
		});
		return repository.save(byId);
	}

	public List<NotaFiscalC> listarTodos() {
		return repository.findAll();

	}

	public void deleterPorId(Integer id) {
		repository.deleteById(id);
		((org.slf4j.Logger) LOG).info("Deletado com sucesso");

	}

    public Object atualizar(Integer id, CabecalhoDto dto) {
        NotaFiscalC byId = repository.findById(id).orElseGet(() -> {
            ((org.slf4j.Logger) LOG).info("Não foi possivel encontrar a nota fiscal pelo ID {}", id);
        return null;
    });
        return repository.save(byId);
    
    }

    public CabecalhoDto salvar(CabecalhoDto cabecalho) {
        return repository.save(cabecalho);
    }

}
