package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository repository;
    private final ParceiroNegocioRepository parceiroNegocioRepository;

    public ProdutoService(ProdutoRepository repository, ParceiroNegocioRepository parceiroNegocioRepository) {
        super();
        this.repository = repository;
        this.parceiroNegocioRepository = parceiroNegocioRepository;
    }

    /*
     * public ProdutoDto buscarPeloFabricante(ParceiroNegocio fabricante) {
     * return repository.findByFabricante(fabricante).map(ProdutoService::
     * converteParaDto) .orElseThrow(() -> new
     * EntidadeNaoEncontradaException("O Produto Nao foi Encontrado pelo fabricante. "
     * )); }
     */
    public ProdutoDto salvar(ProdutoDto produto) {
        Produto entidade = converteParaEntidade(produto);

        ParceiroNegocio fabricanteSalvo = parceiroNegocioRepository.save(entidade.getFabricante());

        entidade.setFabricante(fabricanteSalvo);

        Produto produtoSalvo = repository.save(entidade);

        LOG.info("Salvando o produto {}", produto.getDescricao());

        return converteParaDto(produtoSalvo);
    }

    public ProdutoDto buscarPorId(Integer id) {
        return repository.findById(id).map(ProdutoService::converteParaDto)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("O produto nao foi encontrado pelo id {}. "));

    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(produto -> {
            repository.deleteById(id);
            LOG.info("Produto Deletado com sucesso");
            return produto;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Produto com o ID " + id + " não foi encontrado pelo id"));
    }

    public List<ProdutoDto> listarTodos() {
        return repository.findAll().stream().map(ProdutoService::converteParaDto).collect(Collectors.toList());
    }

    public Produto update(Integer id, Produto produto) {
        repository.findById(id).orElseThrow(() -> {
            LOG.info(" não foi possivel encontrar pelo Id {}.", produto.getNome());
            return new EntidadeNaoEncontradaException("Parceiro de Negocio com o ID: " + id + "Nao foi encontrado");
        });
        return repository.save(produto);
    }

    public static ProdutoDto converteParaDto(Produto entidade) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setDescricao(entidade.getDescricao());
        dto.setNome(entidade.getNome());
        dto.setFabricante(entidade.getFabricante());
        dto.setDataFabricacao(entidade.getDataFabricacao());
        dto.setDataValidade(entidade.getDataValidade());
        dto.setEstoque(entidade.getEstoque());
        return dto;
    }

    public static Produto converteParaEntidade(ProdutoDto dto) {
        Produto entidade = new Produto();
        entidade.setDescricao(dto.getDescricao());
        entidade.setNome(dto.getNome());
        entidade.setFabricante(dto.getFabricante());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setEstoque(dto.getEstoque());
        return entidade;
    }
}
