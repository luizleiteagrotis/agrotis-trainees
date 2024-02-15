package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Produto buscarPeloNome(String nome) {
        return repository.findByNome(nome).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException("O produto nao foi encontrado pelo nome{}");
        });
    }

    public Produto buscarPeloFabricante(String parceiroFabricante) {
        return repository.findByFabricante(parceiroFabricante).orElseGet(() -> {
            LOG.error("O produto nao foi encontrado pelo fabricante {}", parceiroFabricante);
            return null;
        });
    }

    public ProdutoDto salvar(ProdutoDto produto) {
        Produto entidade = converteParaEntidade(produto);

        ParceiroNegocio fabricanteSalvo = parceiroNegocioRepository.save(entidade.getFabricante());

        entidade.setFabricante(fabricanteSalvo);

        Produto produtoSalvo = repository.save(entidade);

        LOG.info("Salvando o produto {}", produto.getDescricao());

        return converteParaDto(produtoSalvo);
    }

    public Produto buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException("Nao foi possivel encontrar pelo id o Produto{}.");
        });
    }

    public void deletarPorId(Integer id) {
        repository.findById(id);
        LOG.info("Deletado com sucesso");
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
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
