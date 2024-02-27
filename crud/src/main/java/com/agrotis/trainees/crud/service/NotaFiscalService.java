package com.agrotis.trainees.crud.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.wrapper.NotaFiscalWrapper;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscal.class);

    private final NotaFiscalRepository repository;
    private final NotaFiscalTipoService tipoService;
    private final NotaFiscalWrapper notaFiscalWrapper;
    private final NumeroService numeroService;

    public NotaFiscalService(NotaFiscalRepository repository, NotaFiscalTipoService tipoService,
                    NotaFiscalWrapper notaFiscalWrapper, NumeroService numeroService) {
        super();
        this.repository = repository;
        this.tipoService = tipoService;
        this.notaFiscalWrapper = notaFiscalWrapper;
        this.numeroService = numeroService;
    }

    public NotaFiscalDto inserir(NotaFiscalDto dto) {
        NotaFiscal entidade = notaFiscalWrapper.converterParaEntidade(dto);
        numeroService.gerarNumero(entidade);
        entidade = repository.save(entidade);
        return notaFiscalWrapper.converterParaDto(entidade);
    }

    public NotaFiscalDto atualizar(NotaFiscalDto dto) {
        if (dto.getId() == null) {
            throw new CrudException("Obrigatório preencher o id");
        }

        NotaFiscal nota = buscarPorId(dto.getId());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.map(dto, nota);

        return notaFiscalWrapper.converterParaDto(repository.save(nota));
    }

    public NotaFiscal buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            LOG.error("Informações não encontradas para o id {}", id);
            return new NoSuchElementException("Informações não encontradas para o id " + id);
        });
    }

    public NotaFiscal buscarPorTipoeNumero(NotaFiscalTipo tipo, Integer numero) {
        return repository.findByTipoAndNumero(tipo, numero).orElseThrow(() -> {
            LOG.error("Informações não encontradas para o id {} e numero de nota {}", tipo, numero);
            return new NoSuchElementException("Informações não encontradas para o id e numero informados ");
        });
    }

    public List<NotaFiscal> listarPorTipo(Integer idTipo) {

        NotaFiscalTipo tipo = tipoService.buscarPorId(idTipo);
        return repository.findByTipo(tipo);
    }

    public List<NotaFiscal> listarPorNumero(Integer numero) {
        return repository.findByNumero(numero);
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("id: {} deletado com sucesso", id);
    }

}
