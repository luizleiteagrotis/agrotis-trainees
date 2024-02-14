package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ProdutoDto;
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

    public ProdutoDto salvar(ProdutoDto entidadeDto) {
        try {
            Produto produtoConvertido = converter(entidadeDto);
            if (Validador.existeParceiroPorId(produtoConvertido.getFabricante().getId())) {
                if (produtoConvertido.getFabricante() == null) {
                    throw new ProdutoExcecao("Falha ao salvar no banco: É obrigatorio informar um parceiro de negocio.");
                }

                validar(produtoConvertido);
                produtoConvertido = repository.save(produtoConvertido);
                return converter(produtoConvertido);
            }
            return null;
        } catch (ProdutoExcecao pe) {
            LOG.error(pe.getMessage());
            return null;
        }
    }

    public ProdutoDto buscarPorId(int id) {
        Produto produto = repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um  produto com este id {}.", id);
            return null;
        });
        return converter(produto);
    }

    // public List<ProdutoDto> buscarPorFornecedor(ParceiroNegocio parceiro) {
    // List<Produto> produtos = repository.findByFabricante(parceiro);
    // return converter(produtos);
    // }

    public List<ProdutoDto> buscarPorDataFabricacao(LocalDate data) {
        List<Produto> produtos = repository.findByDataFabricacao(data);
        return converter(produtos);
    }

    public List<ProdutoDto> buscarPorDataValidade(LocalDate data) {
        List<Produto> produtos = repository.findByDataValidade(data);
        return converter(produtos);
    }

    public List<ProdutoDto> buscarPorNome(String nome) {
        List<Produto> produtos = repository.findByNome(nome);
        return converter(produtos);
    }

    public List<ProdutoDto> listarTodos() {
        List<Produto> produtos = repository.findAll();
        return converter(produtos);
    }

    public ProdutoDto atualizar(ProdutoDto produtoDto) {
        try {
            Produto produtoConvertido = converter(produtoDto);
            ProdutoDto produtoDtoConvertido = buscarPorId(produtoDto.getId());
            Produto produtoAtualizar = converter(produtoDtoConvertido);
            produtoAtualizar.setNome(produtoConvertido.getNome());
            produtoAtualizar.setDescricao(produtoConvertido.getDescricao());
            if (produtoConvertido.getFabricante() != null) {
                produtoAtualizar.setFabricante(produtoConvertido.getFabricante());
            }
            produtoAtualizar.setDataFabricacao(produtoConvertido.getDataFabricacao());
            produtoAtualizar.setDataValidade(produtoConvertido.getDataValidade());
            validar(produtoAtualizar);
            repository.save(produtoAtualizar);
            LOG.info("Produto atualizado");
            return converter(produtoAtualizar);
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

    private ProdutoDto converter(Produto produto) {
        return new ProdutoDto(produto);
    }

    protected Produto converter(ProdutoDto produtoDto) {
        return new Produto(produtoDto.getNome(), produtoDto.getDescricao(), produtoDto.getFabricante(),
                        produtoDto.getDataFabricacao(), produtoDto.getDataValidade());
    }

    private List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(this::converter).collect(Collectors.toList());
    }

}
