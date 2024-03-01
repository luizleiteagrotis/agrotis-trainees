package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.service.exceptions.IdExistenteException;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final ParceiroNegocioRepository repository;

    public ParceiroNegocioService(ParceiroNegocioRepository repository) {
        super();
        this.repository = repository;
    }

    public ParceiroNegocioDto salvar(ParceiroNegocioDto dto) {

        if (dto.getNome() == null || dto.getNome().isEmpty()) {
            throw new EntidadeNaoEncontradaException("O nome do parceiro de negocios e nulo.");
        }
//        if (dto.getId() != null && repository.existsById(dto.getId())) {
//            throw new IdExistenteException("O ID ja existe: " + dto.getId());
//        }
//        if (dto.getId() == null && repository.existsById(dto.getId())) {
//            throw new EntidadeNaoEncontradaException("O ID e nulo: " + dto.getId());
//        }

        if (dto.getEndereco() == null || dto.getEndereco().isEmpty()) {
            throw new EntidadeNaoEncontradaException("O endereço do parceiro de negócios é nulo. ");
        }

        if (dto.getTelefone() == null || dto.getTelefone().isEmpty()) {
            throw new EntidadeNaoEncontradaException("O telefone do parceiro de negócios é nulo.");
        }

        if (!seTelefoneForValido(dto.getTelefone())) {
            throw new EntidadeNaoEncontradaException("O telefone do parceiro de negócios é inválido.");
        }
        
        if (!dto.getTelefone().matches("\\d+")) {
            throw new EntidadeNaoEncontradaException("O telefone do parceiro de negócios contém caracteres não numéricos.");
        }
       

        ParceiroNegocio entidade = converteParaEntidade(dto);
        repository.save(entidade);
        return converteParaDto(entidade);
    }

    public static boolean seTelefoneForValido(String telefone) {
        return telefone != null && telefone.length() == 11;
    }


    public ParceiroNegocioDto buscarPorId(Integer id) {
        return repository.findById(id).map(ParceiroNegocioService::converteParaDto)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Parceiro de negocio nao encontrado pelo id {}."));
    }

    public ParceiroNegocioDto buscarPorNome(String nome) {
        return repository.findByNome(nome).map(ParceiroNegocioService::converteParaDto)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Nome do parceiro de negocio nao encontrado {}."));

    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(entidade -> {
            repository.deleteById(id);
            LOG.info("Parceiro de Negocio Deletado com sucesso");
            return entidade;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade nao encontrada"));
    }
    
    

    public List<ParceiroNegocioDto> listarTodos() {
        return repository.findAll().stream().map(ParceiroNegocioService::converteParaDto).collect(Collectors.toList());
    }

    public ParceiroNegocioDto update(Integer id, ParceiroNegocioDto dto) {

        return repository.findById(id).map(atualizacaoParceiro -> {

            atualizacaoParceiro.setNome(dto.getNome());
            atualizacaoParceiro.setEndereco(dto.getEndereco());
            atualizacaoParceiro.setTelefone(dto.getTelefone());
            atualizacaoParceiro.setInscricaoFiscal(dto.getInscricaoFiscal());

            ParceiroNegocio entity = repository.save(atualizacaoParceiro);

            return ParceiroNegocioService.converteParaDto(entity);

        }).orElseThrow(() -> {
            LOG.info("O PARCEIRO DE NEGOCIOS nao foi encontrado com o  ID{}", id);

            return new EntidadeNaoEncontradaException("O parceiro de negocio " + id + "nao foi localizado com este ID.");
        });
    }
    
    

    public static ParceiroNegocio converteParaEntidade(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setEndereco(dto.getEndereco());
        entidade.setInscricaoFiscal(dto.getInscricaoFiscal());
        entidade.setNome(dto.getNome());
        entidade.setTelefone(dto.getTelefone());
        return entidade;
    }

    public static ParceiroNegocioDto converteParaDto(ParceiroNegocio entidade) {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(entidade.getId());
        dto.setEndereco(entidade.getEndereco());
        dto.setInscricaoFiscal(entidade.getEndereco());
        dto.setNome(entidade.getNome());
        dto.setTelefone(entidade.getTelefone());
        return dto;
    }
    
  

}
