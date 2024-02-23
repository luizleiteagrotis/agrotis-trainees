package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioTipoRepository;

@Service
public class ParceiroNegocioTipoService {

    private final ParceiroNegocioTipoRepository parceiroNegocioTipoRepository;
    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioTipoService.class);

    @Autowired
    public ParceiroNegocioTipoService(ParceiroNegocioTipoRepository parceiroNegocioTipoRepository) {
        this.parceiroNegocioTipoRepository = parceiroNegocioTipoRepository;
    }

    @Transactional
    public ParceiroNegocioDto salvar(ParceiroNegocioDto parceiroNegocioDto) {
        ParceiroNegocio parceiroNegocioEntidade = converterParaEntidade(parceiroNegocioDto);
        parceiroNegocioTipoRepository.save(parceiroNegocioEntidade);
        LOG.info("Salvo Parceiro de Negócio {}", parceiroNegocioEntidade.getNome());
        return converterParaDto(parceiroNegocioEntidade);
    }

    public ParceiroNegocioDto buscarPorId(Integer id) throws NotFoundException {
        ParceiroNegocio parceiroNegocioEntidade = parceiroNegocioTipoRepository.findById(id)
                        .orElseThrow(() -> new org.springframework.data.crossstore.ChangeSetPersister.NotFoundException());
        return converterParaDto(parceiroNegocioEntidade);
    }

    public ParceiroNegocioDto buscarPorInscricaoFiscal(String inscricaoFiscal) throws NotFoundException {
        ParceiroNegocio parceiroNegocioEntidade = parceiroNegocioTipoRepository.findByInscricaoFiscal(inscricaoFiscal)
                        .orElseThrow(() -> new org.springframework.data.crossstore.ChangeSetPersister.NotFoundException());
        return converterParaDto(parceiroNegocioEntidade);
    }

    public void deletarPorId(Integer id) {
        parceiroNegocioTipoRepository.deleteById(id);
        LOG.info("Parceiro de negócio com ID {} deletado com sucesso", id);
    }

    public List<ParceiroNegocioDto> listarTodos() {
        List<ParceiroNegocio> parceiroNegocioEntidades = parceiroNegocioTipoRepository.findAll();
        return parceiroNegocioEntidades.stream().map(ParceiroNegocioTipoService::converterParaDto).collect(Collectors.toList());

    }

    @Transactional
    public ParceiroNegocio inserir(@Valid ParceiroNegocioDto parceiroNegocioDto) {
        ParceiroNegocio parceiroNegocioEntidade = converterParaEntidade(parceiroNegocioDto);
        return parceiroNegocioTipoRepository.save(parceiroNegocioEntidade);
    }

    @Transactional
    public ParceiroNegocioDto atualizar(ParceiroNegocioDto parceiroNegocioDto) {
        ParceiroNegocio parceiroNegocioEntidade = converterParaEntidade(parceiroNegocioDto);
        return converterParaDto(parceiroNegocioTipoRepository.save(parceiroNegocioEntidade));
    }

    public static ParceiroNegocioDto converterParaDto(ParceiroNegocio parceiroNegocioEntidade) {
        if (parceiroNegocioEntidade == null) {
            return null;
        }

        ParceiroNegocioDto parceiroNegocioDto = new ParceiroNegocioDto();
        parceiroNegocioDto.setId(parceiroNegocioEntidade.getId());
        parceiroNegocioDto.setNome(parceiroNegocioEntidade.getNome());
        parceiroNegocioDto.setInscricaoFiscal(parceiroNegocioEntidade.getInscricaoFiscal());
        parceiroNegocioDto.setEndereco(parceiroNegocioEntidade.getEndereco());
        parceiroNegocioDto.setTelefone(parceiroNegocioEntidade.getTelefone());

        return parceiroNegocioDto;
    }

    public static ParceiroNegocio converterParaEntidade(ParceiroNegocioDto parceiroNegocioDto) {
        if (parceiroNegocioDto == null) {
            return null;
        }

        ParceiroNegocio parceiroNegocioEntidade = new ParceiroNegocio();
        parceiroNegocioEntidade.setId(parceiroNegocioDto.getId());
        parceiroNegocioEntidade.setNome(parceiroNegocioDto.getNome());
        parceiroNegocioEntidade.setInscricaoFiscal(parceiroNegocioDto.getInscricaoFiscal());
        parceiroNegocioEntidade.setEndereco(parceiroNegocioDto.getEndereco());
        parceiroNegocioEntidade.setTelefone(parceiroNegocioDto.getTelefone());

        return parceiroNegocioEntidade;
    }
}
