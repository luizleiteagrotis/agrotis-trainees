package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioTipoRepository;

@Service
public class ParceiroNegocioTipoService {

    private ParceiroNegocioTipoRepository parceiroNegocioRepository;
    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioTipoService.class);

    @Autowired
    public ParceiroNegocioTipoService(ParceiroNegocioTipoRepository parceiroNegocioRepository) {
        this.parceiroNegocioRepository = parceiroNegocioRepository;
    }

    public ParceiroNegocio salvar(ParceiroNegocio parceiroNegocio) {
        return parceiroNegocioRepository.save(parceiroNegocio);
    }

    public ParceiroNegocio buscarPorId(Integer id) {
        Optional<ParceiroNegocio> parceiroOptional = parceiroNegocioRepository.findById(id);
        return parceiroOptional.orElse(null);
    }

    public List<ParceiroNegocio> listarTodos() {
        return parceiroNegocioRepository.findAll();
    }

    @Transactional
    public void deletarPorId(Integer id) {
        parceiroNegocioRepository.deleteById(id);
        LOG.info("Parceiro de negócio deletado com sucesso");
    }

    public ParceiroNegocio atualizar(Integer id, ParceiroNegocio novoParceiro) {
        Optional<ParceiroNegocio> optionalParceiro = parceiroNegocioRepository.findById(id);

        if (optionalParceiro.isPresent()) {
            ParceiroNegocio parceiroExistente = optionalParceiro.get();
            parceiroExistente.setNome(novoParceiro.getNome());
            parceiroExistente.setInscricaoFiscal(novoParceiro.getInscricaoFiscal());
            parceiroExistente.setEndereco(novoParceiro.getEndereco());
            parceiroExistente.setTelefone(novoParceiro.getTelefone());

            return parceiroNegocioRepository.save(parceiroExistente);
        } else {
            throw new EntityNotFoundException("ParceiroNegocio com ID " + id + " não encontrado");
        }
    }

    public static ParceiroNegocioDto converterParaDto(ParceiroNegocio entidade) {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setInscricaoFiscal(entidade.getInscricaoFiscal());
        dto.setEndereco(entidade.getEndereco());
        dto.setTelefone(entidade.getTelefone());

        return dto;
    }

    public static ParceiroNegocio converterParaEntidade(ParceiroNegocio parceiroNegocio) {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(parceiroNegocio.getId());
        entidade.setNome(parceiroNegocio.getNome());
        entidade.setInscricaoFiscal(parceiroNegocio.getInscricaoFiscal());
        entidade.setEndereco(parceiroNegocio.getEndereco());
        entidade.setTelefone(parceiroNegocio.getTelefone());

        return entidade;
    }

}