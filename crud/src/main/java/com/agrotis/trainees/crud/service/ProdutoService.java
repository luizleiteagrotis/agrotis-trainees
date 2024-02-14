package com.agrotis.trainees.crud.service;

import java.util.List;

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
    
    public ProdutoDto salvar(ProdutoDto produto){
        if (ValidacaoUtils.isProdutoFieldEmptyOrNull(produto)) {
            throw new CampoVazioOuNuloException("Preencha todos os campos obrigatórios de produto.");
        }
        
        // Convert ProdutoDto to Produto entity
        Produto produtoEntidade = DtoUtils.converteParaEntidade(produto);
        
        // Save the fabricante (ParceiroNegocio) entity first
        ParceiroNegocio fabricanteSalvo = parceiroNegocioRepository.save(produtoEntidade.getFabricante());
        
        // Set the saved fabricante to the Produto entity
        produtoEntidade.setFabricante(fabricanteSalvo);
        
        // Save the Produto entity
        Produto produtoSalvo = repository.save(produtoEntidade);
        
        LOG.info("Salvando o produto {}", produto.getDescricao());
        
        // Convert saved Produto entity back to ProdutoDto and return
        return DtoUtils.converteParaDto(produtoSalvo);
    }


    public List<Produto> buscarTodos() {
        return repository.findAll();
    }

    public Produto buscaPeloId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
    }

    public Produto atualizar(Integer id, Produto produto) {
        return repository.findById(id).map(produtoExistente -> {
            produtoExistente.setDescricao(produto.getDescricao());
            produtoExistente.setFabricante(produto.getFabricante());
            produtoExistente.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            produtoExistente.setDataValidade(produto.getDataValidade());
            produtoExistente.setDataFabricacao(produto.getDataFabricacao());
            LOG.info("Atualizando o produto: {} ", produtoExistente.getDescricao());
            return repository.save(produto);
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
