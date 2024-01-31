package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.PaceiroNegocioRepository;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalTipoService.class);

    private final PaceiroNegocioRepository repository;

    public ParceiroNegocioService(PaceiroNegocioRepository repository) {
        super();
        this.repository = repository;
    }

    public ParceiroNegocio salvar(ParceiroNegocio entidade) {

        if (buscarPorInscricaoFiscal(entidade.getInscricaoFiscal()) != null) {
            LOG.error("Falha ao salvar no banco: Inscrição fiscal já está cadastrada.");
            throw new IllegalArgumentException("Inscrição fiscal já está cadastrada");

        }
        if (entidade.getTelefone() == null) {
            return repository.save(entidade);
        }
        if (entidade.getInscricaoFiscal().length() > 14 || entidade.getInscricaoFiscal().length() < 14
                        || entidade.getTelefone().length() > 14) {
            LOG.error("Falha de validação: Não foi possivel salvar no banco de dados.");
            throw new IllegalArgumentException("Dado de cadastro inválido");

        }
        return repository.save(entidade);
    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Nota não encontrada para id {}.", id);
            return null;
        });
    }

    public ParceiroNegocio buscarPorInscricaoFiscal(String inscricaoFiscal) {
        return repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
            LOG.error("Nota não encontrada para id {}.", inscricaoFiscal);
            return null;
        });
    }

    public List<ParceiroNegocio> listarTodos() {
        return repository.findAll();
    }

    public ParceiroNegocio atualizar(String incricaoFiscal, String nome, String NovaIscricaoFiscal, String endereco,
                    String telefone) {
        if (buscarPorInscricaoFiscal(incricaoFiscal) == null) {
            LOG.error("Inscrição fiscal não encontrada.");
            throw new IllegalArgumentException("Inscrição fiscal não encontrada.");

        }

        Optional<ParceiroNegocio> parceiroOptional = repository.findByInscricaoFiscal(incricaoFiscal);

        if (parceiroOptional.isPresent()) {
            ParceiroNegocio parceiro = parceiroOptional.get();
            parceiro.setNome(nome);
            parceiro.setInscricaoFiscal(NovaIscricaoFiscal);
            parceiro.setEndereco(endereco);
            parceiro.setTelefone(telefone);
            return repository.save(parceiro);
        }
        return null;

    }

    public void deletar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
        } else {
            LOG.info("Este registro não existe");
        }

    }

}
