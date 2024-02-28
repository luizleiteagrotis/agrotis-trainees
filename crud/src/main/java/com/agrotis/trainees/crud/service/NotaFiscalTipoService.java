package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.NotaFiscalTipoDto;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.exception.NotaFiscalTipoNaoEncontrada;
import com.agrotis.trainees.crud.repository.NotaFiscalTipoRepository;
import com.agrotis.trainees.crud.utils.NotaFiscalTipoDTOMapper;

@Service
public class NotaFiscalTipoService {

    private final NotaFiscalTipoRepository repository;

    private final NotaFiscalTipoDTOMapper mapper;

    public NotaFiscalTipoService(NotaFiscalTipoRepository repository, NotaFiscalTipoDTOMapper mapper) {
        super();
        this.repository = repository;
        this.mapper = mapper;
    }

    public NotaFiscalTipoDto inserir(NotaFiscalTipoDto dto) {
        if (StringUtils.isEmpty(dto.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do tipo de nota fiscal.");
        }
        NotaFiscalTipo entidade = mapper.converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return mapper.converterParaDto(entidade);
    }

    public NotaFiscalTipoDto atualizar(NotaFiscalTipoDto dto) {
        NotaFiscalTipo entidade = mapper.converterParaEntidade(dto);
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do tipo de nota fiscal.");
        }
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do tipo de nota fiscal.");
        }
        return mapper.converterParaDto(repository.save(entidade));
    }

    public NotaFiscalTipoDto buscarPorId(Integer id) {
        return repository.findById(id).map(mapper::converterParaDto)
                        .orElseThrow(() -> new NotaFiscalTipoNaoEncontrada("Tipo de nota não encontrada para o id " + id));
    }

    public NotaFiscalTipoDto buscarPorNome(String nome) {
        return repository.findByNome(nome).map(mapper::converterParaDto)
                        .orElseThrow(() -> new NotaFiscalTipoNaoEncontrada("Tipo de nota não encontrada para o nome " + nome));
    }

    public void deletarPorId(Integer id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public List<NotaFiscalTipoDto> listarTodos() {
        return repository.findAll().stream().map(mapper::converterParaDto).collect(Collectors.toList());
    }

}