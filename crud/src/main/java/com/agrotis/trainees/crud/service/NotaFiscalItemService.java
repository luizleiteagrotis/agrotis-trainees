package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import javax.transaction.Transactional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItemService.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;

    @Autowired
    public NotaFiscalItemService(NotaFiscalItemRepository repository, ProdutoService produtoService, NotaFiscalService notaFiscalService) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
    }

    @Transactional
    public NotaFiscalItem salvar(NotaFiscalItem entidade) {
        adicionarOuAtualizarItemNotaFiscal(entidade);
        return repository.save(entidade);
    }

    public NotaFiscalItem buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new CrudException("Item da nota fiscal não encontrado para o id: " + id));
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Item da nota fiscal deletado com sucesso");
    }

    public void adicionarOuAtualizarItemNotaFiscal(NotaFiscalItem item) {
        if (item != null && item.getNotaFiscal() != null && item.getProduto() != null) {
            atualizarValorTotalNotaFiscal(item);
            controlarEstoque(item);
        } else {
            throw new CrudException("O item da nota fiscal, nota fiscal e produto devem ser fornecidos");
        }
    }

    private void atualizarValorTotalNotaFiscal(NotaFiscalItem item) {
        NotaFiscal notaFiscal = item.getNotaFiscal();
        double valorTotalItem = calcularValorTotalItem(item);
        double novoValorTotal = notaFiscal.getValorTotal() + valorTotalItem;
        notaFiscal.setValorTotal(novoValorTotal);
        notaFiscalService.salvar(notaFiscal);
    }

    private double calcularValorTotalItem(NotaFiscalItem item) {
        return item.getQuantidade() * item.getPrecoUnitario();
    }

    private void controlarEstoque(NotaFiscalItem item) {
        Produto produto = item.getProduto();
        int quantidade = item.getQuantidade();

        if (item.getNotaFiscal().getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
            if (produto.getEstoque() < quantidade) {
                throw new CrudException("Estoque insuficiente para o produto: " + produto.getNome());
            }
            produto.setEstoque(produto.getEstoque() - quantidade);
        } else {
            produto.setEstoque(produto.getEstoque() + quantidade);
        }

        produtoService.salvar(produto);
    }
    
    public List<NotaFiscalItem> listarTodos() {
        return repository.findAll();
    }
    
    public NotaFiscalItem inserir(NotaFiscalItem entidade) {
        if (StringUtils.isEmpty(entidade.getNotaFiscal())) {
            throw new CrudException("Obrigatório preencher o número da nota fiscal.");
        }
        return repository.save(entidade);
    }
    
    public NotaFiscalItem atualizar(NotaFiscalItem entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do item da nota fiscal.");
        }
        if (StringUtils.isEmpty(entidade.getProduto())) {
            throw new CrudException("Obrigatório preencher o nome do produto.");
        }
        return repository.save(entidade);
    }
    
}