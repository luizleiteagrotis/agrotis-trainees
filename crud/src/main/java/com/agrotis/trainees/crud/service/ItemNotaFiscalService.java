package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;

@Service
public class ItemNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);

    private final ItemNotaFiscalRepository repository;
    private final ProdutoService produtoService;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository, ProdutoService produtoService) {
        super();
        this.repository = repository;
        this.produtoService = produtoService;
    }

    public ItemNotaFiscal salvar(ItemNotaFiscal entidade) {
        atualizarEstoque(entidade);
        return repository.save(entidade);
    }

    public List<ItemNotaFiscal> buscarTodos() {
        return repository.findAll();
    }

    public ItemNotaFiscal buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.info("Nao foi identificada a nota fiscal pelo ID {} ", id);
            return null;
        });
    }

    public ItemNotaFiscal update(Integer id, ItemNotaFiscal fiscal) {
        ItemNotaFiscal byId = repository.findById(id).orElseGet(() -> {
            LOG.info("A Nota Fiscal nao foi encontrada pelo ID: {}.", id);
            return null;
        });
        return repository.save(byId);
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso!");
    }

    public Double calcularValorTotal(ItemNotaFiscal itemNotaFiscal) {
        Integer quantidadeNotaFiscal = itemNotaFiscal.getQuantidade();
        Double precoUnitarioItemNotaFiscal = itemNotaFiscal.getPrecoUnitario();
        return quantidadeNotaFiscal * precoUnitarioItemNotaFiscal;
    }

    public void atualizarEstoque(ItemNotaFiscal itemNotaFiscal) {

        Produto produto = itemNotaFiscal.getProduto();
        Integer quantidade = itemNotaFiscal.getQuantidade();
        Integer notaFiscalTipo = itemNotaFiscal.getNotaFiscal().getId();
        Integer quantidadeProduto = itemNotaFiscal.getProduto().getEstoque();

        if (notaFiscalTipo == 1) {
            produto.setEstoque(quantidadeProduto + quantidade);
            System.out.println("Entrada");

        } else {
            if (quantidadeProduto - quantidade >= 0) {
                produto.setEstoque(quantidadeProduto - quantidade);
                System.out.println("Saida");

            } else {
                throw new IllegalArgumentException("Nao e possivel remover mais itens que o disponivel em estoque.");
            }

        }
        produtoService.salvar(produto);
    }

}
