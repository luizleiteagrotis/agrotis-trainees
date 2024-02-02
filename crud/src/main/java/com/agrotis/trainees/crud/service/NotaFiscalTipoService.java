package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    public NotaFiscalTipo inserir(NotaFiscalTipo entidade) {
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do tipo de nota fiscal.");
        }
        return repository.save(entidade);
    }

    public NotaFiscalTipo atualizar(NotaFiscalTipo entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do tipo de nota fiscal.");
        }
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do tipo de nota fiscal.");
        }
        return repository.save(entidade);
    }

    public NotaFiscalTipo buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            throw new CrudException(String.format("Nota não encontrada para id {}.", id));
        });
    }

    public NotaFiscalTipo buscarPorNome(String nome) {
        return repository.findByNome(nome).orElseGet(() -> {
            throw new CrudException(String.format("Nota não encontrada para o nome {}.", nome));
        });
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }

    public List<NotaFiscalTipo> listarTodos() {
        return repository.findAll();
    }

}
