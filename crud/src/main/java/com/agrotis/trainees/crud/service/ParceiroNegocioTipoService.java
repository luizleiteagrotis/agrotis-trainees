package com.agrotis.trainees.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioTipoRepository;

@Service
public class ParceiroNegocioTipoService {

    private ParceiroNegocioTipoRepository parceiroNegocioRepository;

    @Autowired
    public ParceiroNegocioTipoService(ParceiroNegocioTipoRepository parceiroNegocioRepository) {
        this.parceiroNegocioRepository = parceiroNegocioRepository;
    }

    public ParceiroNegocio salvar(ParceiroNegocio parceiroNegocio) {
        return parceiroNegocioRepository.save(parceiroNegocio);
    }

    public ParceiroNegocio buscarPorId(Long id) {
        Optional<ParceiroNegocio> parceiroOptional = parceiroNegocioRepository.findById(id);
        return parceiroOptional.orElse(null);
    }

    public List<ParceiroNegocio> listarTodos() {
        return parceiroNegocioRepository.findAll();
    }

    public void deletarPorId(Long id) {
        parceiroNegocioRepository.deleteById(id);
    }

    public ParceiroNegocio atualizar(Long id, ParceiroNegocio novoParceiro) {
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
}