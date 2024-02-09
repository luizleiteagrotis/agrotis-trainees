package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ProdutoExcecao;
import com.agrotis.trainees.crud.helper.Validador;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {
    private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        super();
        this.repository = repository;

    }

    public Produto salvar(Produto entidade) {
        try {
            if (Validador.existeParceiroPorId(entidade.getFabricante().getId())) {
                if (entidade.getFabricante() == null) {
                    throw new ProdutoExcecao("Falha ao salvar no banco: É obrigatorio informar um parceiro de negocio.");
                }

                validar(entidade);
                return repository.save(entidade);
            }
            return null;
        } catch (ProdutoExcecao pe) {
            LOG.error(pe.getMessage());
            return null;
        }
    }

    public Produto buscarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um  produto com este id {}.", id);
            return null;
        });
    }

    public List<Produto> buscarPorFornecedor(ParceiroNegocio parceiro) {
        return repository.findByFabricante(parceiro);
    }

    public List<Produto> buscarPorDataFabricacao(LocalDate data) {
        return repository.findByDataFabricacao(data);
    }

    public List<Produto> buscarPorDataValidade(LocalDate data) {
        return repository.findByDataValidade(data);
    }

    public List<Produto> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto atualizarPorId(Produto produto, int id) {
        try {
            Produto produtoAtualizar = buscarPorId(id);
            produtoAtualizar.setNome(produto.getNome());
            produtoAtualizar.setDescricao(produto.getDescricao());
            if (produto.getFabricante() != null) {
                produtoAtualizar.setFabricante(produto.getFabricante());
            }
            produtoAtualizar.setDataFabricacao(produto.getDataFabricacao());
            produtoAtualizar.setDataValidade(produto.getDataValidade());
            validar(produtoAtualizar);
            repository.save(produtoAtualizar);
            LOG.info("Produto atualizado");
            return produtoAtualizar;
        } catch (ProdutoExcecao pe) {
            LOG.error(pe.getMessage());
            return null;
        }

    }

    public void deletarPorId(int id) {
        if (this.buscarPorId(id) != null) {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
        } else {
            LOG.info("Registro não encontrado");
        }
    }

    private void validar(Produto produto) throws ProdutoExcecao {
        if (produto == null) {
            throw new ProdutoExcecao("Falha ao salvar no banco: Por favor insira valores válidos.");
        }

        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new ProdutoExcecao("Falha ao salvar no banco: É obrigatorio informar um nome.");

        }
        if (produto.getDataValidade() == null) {
            throw new ProdutoExcecao("Falha ao salvar no banco: Por favor insira uma data de validade válida");
        }

        if (produto.getDataValidade().isBefore(produto.getDataFabricacao())) {
            throw new ProdutoExcecao("Falha ao salvar no banco : A data de validade deve ser posterior a data de fabricação.");
        }

        if (repository.findByNomeAndDataFabricacaoAndFabricanteAndDescricao(produto.getNome(), produto.getDataFabricacao(),
                        produto.getFabricante(), produto.getDescricao()).isPresent()) {
            throw new ProdutoExcecao("Falha ao salvar no banco: Este produto já está cadastrado");
        }
    }

}
