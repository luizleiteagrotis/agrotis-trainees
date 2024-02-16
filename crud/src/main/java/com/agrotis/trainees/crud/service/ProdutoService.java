package com.agrotis.trainees.crud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.CampoVazioOuNuloException;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.utils.DtoUtils;
import com.agrotis.trainees.crud.utils.ValidacaoUtils;

@Service
public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository repository;
    private final ParceiroNegocioRepository parceiroNegocioRepository;

    public ProdutoService(ProdutoRepository repository, ParceiroNegocioRepository parceiroNegocioRepository) {
        this.repository = repository;
        this.parceiroNegocioRepository = parceiroNegocioRepository;
    }

    public ProdutoDto salvar(ProdutoDto produto) {
        if (ValidacaoUtils.isProdutoFieldEmptyOrNull(produto)) {
            throw new CampoVazioOuNuloException("Preencha todos os campos obrigatórios de produto.");
        }

        Produto entidade = DtoUtils.converteParaEntidade(produto);

        ParceiroNegocio fabricanteSalvo = parceiroNegocioRepository.save(entidade.getFabricante());

        entidade.setFabricante(fabricanteSalvo);

        Produto produtoSalvo = repository.save(entidade);

        LOG.info("Salvando o produto {}", produto.getDescricao());

        return DtoUtils.converteParaDto(produtoSalvo);
    }

    public ProdutoDto buscaPeloId(Integer id) {
        return repository.findById(id).map(DtoUtils::converteParaDto)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
    }

    public List<ProdutoDto> buscarTodos() {
        return repository.findAll().stream().map(DtoUtils::converteParaDto).collect(Collectors.toList());
    }

    public ProdutoDto atualizar(Integer id, ProdutoDto dto) {
        return repository.findById(id).map(produtoExistente -> {
            produtoExistente.setDescricao(dto.getDescricao());
            ParceiroNegocio fabricanteExistente = dto.getFabricante().getId() != null
                            ? parceiroNegocioRepository.findById(dto.getFabricante().getId()).orElse(null)
                            : null;
            if (fabricanteExistente == null) {
                ParceiroNegocio fabricanteSalvo = parceiroNegocioRepository.save(dto.getFabricante());
                produtoExistente.setFabricante(fabricanteSalvo);
            } else {
                produtoExistente.setFabricante(fabricanteExistente);
            }
            produtoExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());
            produtoExistente.setDataValidade(dto.getDataValidade());
            produtoExistente.setDataFabricacao(dto.getDataFabricacao());
            LOG.info("Atualizando o produto: {} ", produtoExistente.getDescricao());
            Produto produtoAtualizado = repository.save(produtoExistente);
            return DtoUtils.converteParaDto(produtoAtualizado);
        }).orElseThrow(() -> {
            LOG.info("Não foi possível encontrar o produto pelo ID {}", id);
            return new EntidadeNaoEncontradaException("Produto com o ID " + id + " não encontrado");
        });
    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(produto -> {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
            return produto;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Produto com o ID " + id + " não encontrado"));
    }
}
