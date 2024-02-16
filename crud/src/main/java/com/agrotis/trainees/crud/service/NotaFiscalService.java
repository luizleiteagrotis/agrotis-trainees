package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository repository) {
        super();
        this.repository = repository;
    }

    @Transactional
    public NotaFiscal salvar(NotaFiscal entidade) {
        return repository.save(entidade);
    }
    
    public NotaFiscalDto salvar(NotaFiscalDto dto) {
        NotaFiscal entidade = converterParaEntidade(dto);
        NotaFiscal savedNotaFiscal = repository.save(entidade);
        List<NotaFiscalItem> itens = savedNotaFiscal.getItensNota();
        atualizarNotaFiscal(itens); 
        LOG.info("Salva nota fiscal {}", savedNotaFiscal.getNumeroNota());
        return converterParaDto(savedNotaFiscal);
    }

    public NotaFiscalDto buscarPorId(Integer id) throws NotFoundException{
    	NotaFiscal entidade = repository.findById(id).orElseThrow(() -> new NotFoundException());
        return converterParaDto(entidade);
    }
    
    //ajustar métodos de busca

    public NotaFiscal buscarPorNotaFiscalTipo(NotaFiscalTipo tipoNota) {
        return repository.findByNotaFiscalTipo(tipoNota).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o tipo de {}.", tipoNota);
            return null;
        });
    }

    public NotaFiscal buscarPorParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        return repository.findByParceiroNegocio(parceiroNegocio).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o parceiro {}.", parceiroNegocio);
            return null;
        });
    }

    public NotaFiscal buscarPorNumero(Integer numeroNota) {
        return repository.findByNumeroNota(numeroNota).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o número {}.", numeroNota);
            return null;
        });
    }

    public NotaFiscal buscarPorData(LocalDate dataNota) {
        return repository.findByDataNota(dataNota).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para a data de {}.", dataNota);
            return null;
        });
    }

    public List<NotaFiscalDto> listarTodos() {
    	List<NotaFiscal> entidades = repository.findAll();
    	return entidades.stream().map(entidade -> converterParaDto(entidade)).collect(Collectors.toList());
    }

    public void deletarPorId(Integer integer) {
        repository.deleteById(integer);
        LOG.info("Deletado com sucesso");
    }

    @Transactional
    public void atualizarNotaFiscal(List<NotaFiscalItem> itens) {
        NotaFiscal notaFiscal = itens.get(0).getNotaFiscal(); 
        double novoValorTotal = 0;

        for (NotaFiscalItem item : itens) {
            double valorItem = item.getPrecoUnitario() * item.getQuantidade(); 
            if (notaFiscal.getNotaFiscalTipo() == NotaFiscalTipo.ENTRADA) {
                novoValorTotal += valorItem;
            } else if (notaFiscal.getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
                novoValorTotal -= valorItem;
            }
        }

        notaFiscal.setValorTotal(novoValorTotal);
        repository.save(notaFiscal);
    }
    
    public NotaFiscal inserir(@Valid NotaFiscalDto dto) {
    	NotaFiscal entidade = converterParaEntidade(dto);
    	return repository.save(entidade);
    }
	
	public NotaFiscalDto atualizar(NotaFiscalDto dto) {
        NotaFiscal entidade = converterParaEntidade(dto);
        return converterParaDto(repository.save(entidade));
    }
	
	public static NotaFiscalDto converterParaDto(NotaFiscal entidade) {
		NotaFiscalDto dto = new NotaFiscalDto();
		dto.setId(entidade.getId());
		dto.setNotaFiscalTipo(entidade.getNotaFiscalTipo());
		dto.setParceiroNegocio(entidade.getParceiroNegocio());
		dto.setNumeroNota(entidade.getNumeroNota());
		dto.setDataNota(entidade.getDataNota());
		dto.setItensNota(entidade.getItensNota());
		dto.setValorTotal(entidade.getValorTotal());
		
		return dto;
	}
	
	public static NotaFiscal converterParaEntidade(NotaFiscalDto dto) {
		NotaFiscal entidade = new NotaFiscal();
		entidade.setId(dto.getId());
		entidade.setNotaFiscalTipo(dto.getNotaFiscalTipo());
		entidade.setParceiroNegocio(dto.getParceiroNegocio());
		entidade.setNumeroNota(dto.getNumeroNota());
		entidade.setDataNota(dto.getDataNota());
		entidade.setItensNota(dto.getItensNota());
		entidade.setValorTotal(dto.getValorTotal());
		
		return entidade;
	}

}