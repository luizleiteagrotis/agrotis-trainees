package com.agrotis.trainees.crud.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.exception.InscricaoExisteException;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.wrapper.ParceiroNegocioWrapper;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final ParceiroNegocioRepository repository;
    private final ParceiroNegocioWrapper parceiroNegocioWrapper;
    private final InscricaoService inscricaoService;

    public ParceiroNegocioService(ParceiroNegocioRepository repository, ParceiroNegocioWrapper parceiroNegocioWrapper,
                    InscricaoService inscricaoService) {
        super();
        this.repository = repository;
        this.parceiroNegocioWrapper = parceiroNegocioWrapper;
        this.inscricaoService = inscricaoService;
    }

    public ParceiroNegocioDto inserir(ParceiroNegocioDto dto) {
        try {
            inscricaoService.verificarInscricao(dto.getInscricaoFiscal());
            ParceiroNegocio entidade = parceiroNegocioWrapper.converterParaEntidade(dto);
            entidade = repository.save(entidade);
            return parceiroNegocioWrapper.converterParaDto(entidade);
        } catch (InscricaoExisteException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
            return null;
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
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Nota não encontrada para o id {}. ", id);
            return null;
        });
    }

    public List<ParceiroNegocio> buscarPorNome(String nome) {
        return repository.findByNomeContainingOrderById(nome);
    }

    public ParceiroNegocio buscarPorInscricaoFiscal(String inscricaoFiscal) {
        String inscricaoFiscalNormalizada = inscricaoService.normalizarInscricaoFiscal(inscricaoFiscal);

        return repository.findByInscricaoFiscal(inscricaoFiscalNormalizada).orElseGet(() -> {
            return repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
                LOG.error("Nota não encontrada para a inscrição {}.", inscricaoFiscal);
                return null;
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
