package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ItemDuplicadoException;
import com.agrotis.trainees.crud.exception.ProdutoDuplicadoException;
import com.agrotis.trainees.crud.exception.QuantidadeInsuficienteException;
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

    public ItemNotaFiscal salvar(ItemNotaFiscal entidade) throws QuantidadeInsuficienteException, ItemDuplicadoException {
        entidade.setValorTotal();
        itemDuplicado(entidade);
        atualizarEstoque(entidade);
        return repository.save(entidade);
    }

    public ItemNotaFiscal atualizar(ItemNotaFiscal entidade) {
        if (repository.existsByProdutoAndNotaFiscalAndIdNot(entidade.getProduto(), entidade.getNotaFiscal(), entidade.getId())) {
            throw new ProdutoDuplicadoException("Já existe um item de nota fiscal com o mesmo produto e nota fiscal");
        }

        entidade.setValorTotal();
        atualizarEstoque(entidade);
        return repository.save(entidade);
    }

    public ItemNotaFiscal buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Item não encontrado para id {}", id);
            return null;
        });
    }

    public List<ItemNotaFiscal> buscarPorProduto(Produto produto) {
        return repository.findAllByProduto(produto);
    }

    public List<ItemNotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

    public void itemDuplicado(ItemNotaFiscal item) throws ItemDuplicadoException {
        if (repository.existsByProdutoAndNotaFiscal(item.getProduto(), item.getNotaFiscal())) {
            throw new ItemDuplicadoException("O item já existe na nota");
        }
    }

    public void atualizarEstoque(ItemNotaFiscal item) throws QuantidadeInsuficienteException {
        Produto produto = item.getProduto();
        String tipo = item.getNotaFiscal().getNotaFiscalTipo().getNome();
        int quantidade = item.getQuantidade();
        int quantidadeProduto = produto.getQuantidadeEstoque();

        ItemNotaFiscal itemExistente = repository.findByProdutoAndNotaFiscal(item.getProduto(), item.getNotaFiscal());

        if (itemExistente != null) {
            int diferencaQuantidade = quantidade - itemExistente.getProduto().getQuantidadeEstoque();
            produto.setQuantidadeEstoque(quantidadeProduto + diferencaQuantidade);
        } else {

            if (tipo.equalsIgnoreCase("SAÍDA") && (quantidadeProduto - quantidade) < 0) {
                throw new QuantidadeInsuficienteException("Quantidade insuficiente em estoque para a saída do produto: ");
            }

            if (tipo.equalsIgnoreCase("ENTRADA")) {
                produto.setQuantidadeEstoque(quantidadeProduto + quantidade);
            } else {
                produto.setQuantidadeEstoque(quantidadeProduto - quantidade);
            }

        }

        produtoService.atualizar(produto);
    }

}
