package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.FabricanteDuplicadoException;
import com.agrotis.trainees.crud.exception.FabricanteNaoEncontradoException;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.utils.ParceiroNegocioDTOMapper;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final ParceiroNegocioRepository repository;

    private final ParceiroNegocioDTOMapper mapper;

    public ParceiroNegocioService(ParceiroNegocioRepository repository, ParceiroNegocioDTOMapper mapper) {
        super();
        this.repository = repository;
        this.mapper = mapper;
    }

    public ParceiroNegocioDto salvar(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = mapper.conveterParaEntidade(dto);

        if (repository.existsByNomeOrInscricaoFiscal(entidade.getNome(), entidade.getInscricaoFiscal())) {
            throw new FabricanteDuplicadoException("Nome do fabricante ou inscrição fiscal já existem");
        }

        entidade = repository.save(entidade);
        return mapper.converterParaDto(entidade);
    }

    public ParceiroNegocioDto atualizar(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = mapper.conveterParaEntidade(dto);

        if (repository.existsByNomeAndIdNot(entidade.getNome(), entidade.getId())) {
            throw new FabricanteDuplicadoException("Já existe um fabricante com o mesmo nome: " + entidade.getNome());
        }

        if (repository.existsByInscricaoFiscalAndIdNot(entidade.getInscricaoFiscal(), entidade.getId())) {
            throw new FabricanteDuplicadoException(
                            "Já existe um fabricante com a mesma inscrição: " + entidade.getInscricaoFiscal());
        }

        return mapper.converterParaDto(repository.save(entidade));
    }

    public ParceiroNegocioDto buscarPorId(Integer id) {
        return repository.findById(id).map(mapper::converterParaDto)
                        .orElseThrow(() -> new FabricanteNaoEncontradoException("Fabricante não encontrado para id " + id));
    }

    public ParceiroNegocioDto buscarPorInscricao(String inscricao) {
        return repository.findByInscricaoFiscal(inscricao).map(mapper::converterParaDto).orElseThrow(
                        () -> new FabricanteNaoEncontradoException("Fabricante não encontrado para inscrição fiscal " + inscricao));
    }

    public ParceiroNegocioDto buscarPorNome(String nome) {
        return repository.findByNome(nome).map(mapper::converterParaDto)
                        .orElseThrow(() -> new FabricanteNaoEncontradoException("Fabricante não encontrado para nome " + nome));
    }

    public List<ParceiroNegocioDto> listarTodos() {
        List<ParceiroNegocio> entidades = repository.findAll();
        return entidades.stream().map(mapper::converterParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

}
