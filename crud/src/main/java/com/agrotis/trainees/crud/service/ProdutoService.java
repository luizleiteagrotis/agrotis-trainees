package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.agrotis.trainees.crud.convert.ProdutoConversor;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ProdutoExcecao;
import com.agrotis.trainees.crud.helper.Validador;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {
    private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);
    private final ProdutoRepository repository;
    private final ProdutoConversor produtoConversor;
    private final Validador validador;

    public ProdutoService(ProdutoRepository repository, ProdutoConversor produtoConversor, Validador validador) {
        super();
        this.repository = repository;
        this.produtoConversor = produtoConversor;
        this.validador = validador;

    }

    public ProdutoDto salvar(ProdutoDto entidadeDto) throws ProdutoExcecao {
        try {
            Produto produtoConvertido = produtoConversor.converter(entidadeDto);
            if (validador.existeParceiroPorId(produtoConvertido.getFabricante().getId())) {
                produtoConvertido.setCustoMedio(new BigDecimal(0));
                produtoConvertido.setEstoque(new BigDecimal(0));
                validar(produtoConvertido);
                produtoConvertido = repository.save(produtoConvertido);
                return produtoConversor.converter(produtoConvertido);
            } else {
                throw new ProdutoExcecao("Falha ao salvar no banco: É obrigatorio informar um parceiro de negocio.");
            }

        } catch (ProdutoExcecao pe) {
            LOG.error(pe.getMessage());
            throw pe;
        }
    }

    public ProdutoDto buscarPorId(int id) {
        Produto produto = repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um  produto com este id {}.", id);
            return null;
        });
        return produtoConversor.converter(produto);
    }

    public List<ProdutoDto> buscarPorDataFabricacao(LocalDate data) throws ProdutoExcecao {
        try {
            if (data == null) {
                throw new ProdutoExcecao("Falha ao trazer dados: É obrigatorio informar uma data.");
            }

            List<Produto> produtos = repository.findByDataFabricacao(data);
            if (produtos.isEmpty()) {
                LOG.info("Não possui nenhum produto cadastrado com esta Data de Fabricacao.");
                return Collections.emptyList();
            }
            return produtoConversor.converter(produtos);
        } catch (ProdutoExcecao pe) {
            LOG.error(pe.getMessage());
            throw pe;
        }
    }

    public List<ProdutoDto> buscarPorDataValidade(LocalDate data) throws ProdutoExcecao {
        try {
            if (data == null) {
                throw new ProdutoExcecao("Falha ao trazer dados: É obrigatorio informar uma data.");
            }
            List<Produto> produtos = repository.findByDataValidade(data);
            if (produtos.isEmpty()) {
                LOG.info("Não possui nenhum produto cadastrado com esta Data de Validade.");
                return Collections.emptyList();
            }
            return produtoConversor.converter(produtos);
        } catch (ProdutoExcecao pe) {
            LOG.error(pe.getMessage());
            throw pe;
        }
    }

    public List<ProdutoDto> buscarPorNome(String nome) throws ProdutoExcecao {
        try {
            if (nome == null || nome.isBlank()) {
                throw new ProdutoExcecao("Falha ao trazer dados: É obrigatorio informar nome.");
            }
            List<Produto> produtos = repository.findByNome(nome);
            if (produtos.isEmpty()) {
                LOG.info("Não possui nenhum produto cadastrado com este nome.");
                return Collections.emptyList();
            }
            return produtoConversor.converter(produtos);
        } catch (ProdutoExcecao pe) {
            LOG.error(pe.getMessage());
            throw pe;
        }
    }

    public List<ProdutoDto> listarTodos() {
        List<Produto> produtos = repository.findAll();
        if (produtos.isEmpty()) {
            LOG.info("Não possui nenhum produto cadastrado.");
            return Collections.emptyList();
        }
        return produtoConversor.converter(produtos);
    }

    public ProdutoDto atualizar(ProdutoDto produtoDto, int id) {
        try {
            Produto produtoConvertido = produtoConversor.converter(produtoDto);
            Produto produtoAtualizar = verificarPorId(id);
            if (produtoConvertido.getNome() != null) {
                produtoAtualizar.setNome(produtoConvertido.getNome());
            }
            if (produtoConvertido.getDescricao() != null) {
                produtoAtualizar.setDescricao(produtoConvertido.getDescricao());
            }
            if (produtoConvertido.getFabricante() != null) {
                produtoAtualizar.setFabricante(produtoConvertido.getFabricante());
            }
            if (produtoConvertido.getDataFabricacao() != null) {
                produtoAtualizar.setDataFabricacao(produtoConvertido.getDataFabricacao());
            }
            if (produtoConvertido.getDataValidade() != null) {
                produtoAtualizar.setDataValidade(produtoConvertido.getDataValidade());
            }
            validar(produtoAtualizar);
            repository.save(produtoAtualizar);
            LOG.info("Produto atualizado");
            return produtoConversor.converter(produtoAtualizar);
        } catch (ProdutoExcecao pe) {
            LOG.error(pe.getMessage());
            return null;
        }

    }

    public void deletarPorId(int id) {
        if (validador.existeProdutoPorId(id)) {
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

        if (produto.getDataFabricacao() == null) {
            throw new ProdutoExcecao("Falha ao salvar no banco: Por favor insira uma data de fabricação válida");
        }

        if (produto.getDataValidade().isBefore(produto.getDataFabricacao())) {
            throw new ProdutoExcecao("Falha ao salvar no banco : A data de validade deve ser posterior a data de fabricação.");
        }

        if (repository.findByNomeAndDataFabricacaoAndFabricanteAndDescricao(produto.getNome(), produto.getDataFabricacao(),
                        produto.getFabricante(), produto.getDescricao()).isPresent()) {
            throw new ProdutoExcecao("Falha ao salvar no banco: Este produto já está cadastrado");
        }

    }

    protected Produto verificarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um  produto com este id {}.", id);
            return null;
        });

    }

}
