package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import com.agrotis.trainees.crud.dto.NotaFiscalTipoDto;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.NotaFiscalTipoRepository;

@Service
public class NotaFiscalTipoService {

    private final NotaFiscalTipoRepository repository;

    public NotaFiscalTipoService(NotaFiscalTipoRepository repository) {
        super();
        this.repository = repository;
    }

    public NotaFiscalTipoDto inserir(NotaFiscalTipoDto dto) {
        if (StringUtils.isEmpty(dto.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do tipo de nota fiscal.");
        }
        NotaFiscalTipo entidade = converterParaEntidade(dto);
        entidade = repository.save(entidade);
        return converterParaDto(entidade);
    }

    public NotaFiscalTipoDto atualizar(NotaFiscalTipo entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do tipo de nota fiscal.");
        }
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do tipo de nota fiscal.");
        }
        return converterParaDto(repository.save(entidade));
    }

    public NotaFiscalTipoDto buscarPorId(Integer id) {
        return null;
        // return repository.findById(id).orElseGet(() -> {
        // throw new CrudException(String.format("Nota não encontrada para id
        // {}.", id));
        // });
    }

    public NotaFiscalTipoDto buscarPorNome(String nome) {
        return null;
        // return repository.findByNome(nome).orElseGet(() -> {
        // throw new CrudException(String.format("Nota não encontrada para o
        // nome {}.", nome));
        // });
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }

    public List<NotaFiscalTipo> listarTodos() {
        return repository.findAll();
    }

    private NotaFiscalTipoDto converterParaDto(NotaFiscalTipo entidade) {
        NotaFiscalTipoDto dto = new NotaFiscalTipoDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        return dto;
    }

    private NotaFiscalTipo converterParaEntidade(NotaFiscalTipoDto dto) {
        NotaFiscalTipo entidade = new NotaFiscalTipo();
        entidade.setId(dto.getId());
        entidade.setNome(dto.getNome());
        return entidade;
    }

}
