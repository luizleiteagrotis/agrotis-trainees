package com.agrotis.trainees.crud.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.exception.InscricaoExisteException;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.wrapper.ParceiroNegocioWrapper;

@Service
public class ParceiroNegocioService {

    private static Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private ParceiroNegocioRepository repository;
    private ParceiroNegocioWrapper parceiroNegocioWrapper;
    private InscricaoService inscricaoService;

    public ParceiroNegocioService(ParceiroNegocioRepository repository, ParceiroNegocioWrapper parceiroNegocioWrapper,
                    InscricaoService inscricaoService) {
        super();
        this.repository = repository;
        this.parceiroNegocioWrapper = parceiroNegocioWrapper;
        this.inscricaoService = inscricaoService;
    }

    public ParceiroNegocioDto inserir(ParceiroNegocioDto dto) throws InscricaoExisteException {
        try {
            inscricaoService.verificarInscricao(dto.getInscricaoFiscal());
            ParceiroNegocio entidade = parceiroNegocioWrapper.converterParaEntidade(dto);
            entidade = repository.save(entidade);
            return parceiroNegocioWrapper.converterParaDto(entidade);
        } catch (InscricaoExisteException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }

    public ParceiroNegocioDto atualizar(ParceiroNegocioDto dto) {
        if (dto.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do parceiro.");
        }

        ParceiroNegocio parceiro = buscarPorId(dto.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, parceiro);

        return parceiroNegocioWrapper.converterParaDto(repository.save(parceiro));
    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            LOG.error("Nota não encontrada para o id {}. ", id);
            return new NoSuchElementException("Nota não encontrada para o id " + id);
        });
    }

    public List<ParceiroNegocio> buscarPorNome(String nome) {
        return repository.findByNomeContainingOrderById(nome);
    }

    public ParceiroNegocio buscarPorInscricaoFiscal(String inscricaoFiscal) {
        String inscricaoFiscalNormalizada = inscricaoService.normalizarInscricaoFiscal(inscricaoFiscal);

        return repository.findByInscricaoFiscal(inscricaoFiscalNormalizada).orElseGet(() -> {
            return repository.findByInscricaoFiscal(inscricaoFiscal).orElseThrow(() -> {
                LOG.error("Nota não encontrada para a inscrição {}.", inscricaoFiscal);
                return new NoSuchElementException("Nota não encontrada para a inscrição " + inscricaoFiscal);
            });
        });
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

    public List<ParceiroNegocio> listarTodos() {
        return repository.findAll();
    }

}
