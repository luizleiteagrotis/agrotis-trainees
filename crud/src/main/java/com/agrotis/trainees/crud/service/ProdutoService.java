package com.agrotis.trainees.crud.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

import dto.ProdutoDto;

@Service

public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(Produto.class);

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        super();
        this.repository = repository;
    }

    public Produto salvar(Produto entidade) {
        return repository.save(entidade);
    }

    public Produto buscarPorNome(String nome) {
        return repository.findByNome(nome).orElseGet(() -> {
            LOG.error("Nota não encontrada para o nome {}.", nome);
            return null;
        });

    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso");

    }

    public List<Produto> listarTodos() {
        return repository.findAll();

    }

    public Produto buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Nota não encontrada para o id {}. ", id);
            return null;
        });
    }

    public Produto update(Integer id, Produto produto) {
        repository.findById(id).orElseGet(() -> {
            LOG.error("Parceiro de Negócio não encontrado para o Id {}.", produto.getNome());
            return null;
        });
        return repository.save(produto);
    }

    public Produto buscarPorDescricao(String descricao) {
        return ((Optional<Produto>) repository.findByDescricao(descricao)).orElseGet(() -> {
            LOG.error("Informações não encontradas para a descrição {}. ", descricao);
            return null;
        });
    }

    public ProdutoDto atualizar(Integer id, ProdutoDto dto) {
        return (ProdutoDto) repository.findById(id).map(produtoExistente -> {
            produtoExistente.setDescricao(dto.getDescricao());
            Produto fabricanteExistente;
            if (dto.getFabricante() != null)
                fabricanteExistente = ParceiroNegocioRepository.findById(dto.getFabricante().getBytes()).orElse(null);
            else
                fabricanteExistente = null;
            if (fabricanteExistente == null) {
                ParceiroNegocio fabricanteSalvo = ParceiroNegocioRepository.save(dto.getFabricante());
                produtoExistente.setFabricante(fabricanteSalvo);
            }
            produtoExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());
            produtoExistente.setDataValidade(dto.getDataValidade());
            produtoExistente.setDataFabricacao(dto.getDataFabricacao());
            LOG.info("Atualizando o produto: {} ", produtoExistente.getDescricao());
            Produto produtoAtualizado = repository.save(produtoExistente);
            return null;
        }).orElseThrow(() -> {
            LOG.info("Não foi possível encontrar o produto pelo ID {}", id);
            return null;
        });
    }

    public List<Produto> listarTodos1() {
        return repository.findAll();

    }

    public void atualizar(Integer id, Produto produtoAtualizado) {

    }

    public List<ProdutoDto> buscarTodos() {
        return null;
    }

    public ProdutoDto salvar(ProdutoDto entidade) {
        return repository.save(entidade);

    }

}
