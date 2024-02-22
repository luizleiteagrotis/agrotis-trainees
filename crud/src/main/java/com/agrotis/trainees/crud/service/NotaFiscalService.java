package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.exception.NotaFiscalCabecalhoNaoEncontradaException;
import com.agrotis.trainees.crud.exception.NotaFiscalDuplicadaException;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.utils.NotaFiscalDTOMapper;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final NotaFiscalRepository repository;

    private final NotaFiscalDTOMapper mapper;

    public NotaFiscalService(NotaFiscalRepository repository, NotaFiscalDTOMapper mapper) {
        super();
        this.repository = repository;
        this.mapper = mapper;
    }

    public NotaFiscalDto salvar(NotaFiscalDto dto) {
        NotaFiscal entidade = mapper.converterParaEntidade(dto);

        if (repository.existsByNumeroAndNotaFiscalTipo(entidade.getNumero(), entidade.getNotaFiscalTipo())) {
            throw new NotaFiscalDuplicadaException("Nota fiscal já existe");
        }
        return mapper.converterParaDto(repository.save(entidade));
    }

    public NotaFiscalDto atualizar(NotaFiscalDto dto) {
        NotaFiscal entidade = mapper.converterParaEntidade(dto);

        if (repository.existsByNumeroAndNotaFiscalTipoAndIdNot(entidade.getNumero(), entidade.getNotaFiscalTipo(),
                        entidade.getId())) {
            throw new NotaFiscalDuplicadaException("Nota com o numero e o tipo já existe: " + entidade.getNumero() + " "
                            + entidade.getNotaFiscalTipo().getNome());
        }

        return mapper.converterParaDto(repository.save(entidade));

    }

    public NotaFiscalDto buscarPorId(Integer id) {
        return repository.findById(id).map(mapper::converterParaDto).orElseThrow(
                        () -> new NotaFiscalCabecalhoNaoEncontradaException("Nota Fiscal não encontrada para o id " + id));
    }

    public List<NotaFiscalDto> buscarPorNumero(String numero) {
        List<NotaFiscal> entidades = repository.findAllByNumero(numero);
        if (entidades.isEmpty()) {
            throw new NotaFiscalCabecalhoNaoEncontradaException("Nota Fiscal não encontrada para o numero " + numero);
        }

        return entidades.stream().map(mapper::converterParaDto).collect(Collectors.toList());
    }

    public List<NotaFiscalDto> listarTodos() {
        List<NotaFiscal> entidades = repository.findAll();
        return entidades.stream().map(mapper::converterParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }
}
