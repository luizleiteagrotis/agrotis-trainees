package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.CabecalhoNotaFiscalRepository;

@Service
public class CabecalhoNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final CabecalhoNotaFiscalRepository repository;

    public CabecalhoNotaFiscalService(CabecalhoNotaFiscalRepository repository) {
        this.repository = repository;
    }

    public CabecalhoNotaFiscal salvar(CabecalhoNotaFiscal entidade) {
        return repository.save(entidade);
    }
    
    public List<CabecalhoNotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public CabecalhoNotaFiscal buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o id {}.", id);
            return null;
        });
    }
    
    public CabecalhoNotaFiscal buscarPorNumero(Integer numero) {
    	return repository.findByNumero(numero).orElseGet(() -> {
    		LOG.error("Nota Fiscal não encontrada para o numero {}", numero);
    		return null;
    	});
    }
    
    public List<CabecalhoNotaFiscal> buscarPorTipo(String tipo){
    	return repository.findByTipo(tipo).orElseGet(() -> {
    		LOG.error("Notas fiscais não encontradas do tipo {}", tipo);
    		return null;
    	});
    }
    
    public List<CabecalhoNotaFiscal> buscarPorParceiro(ParceiroNegocio parceiro){
    	return repository.findByParceiro(parceiro.getId()).orElseGet(() -> {
    		LOG.error("Notas fiscais não encontradas para o parceiro {}", parceiro.getNome());
    		return null;
    	});
    }
    
    public CabecalhoNotaFiscal atualizar(Integer id) {
    	CabecalhoNotaFiscal nota = buscarPorId(id);
    	return repository.save(nota);
    }

}
