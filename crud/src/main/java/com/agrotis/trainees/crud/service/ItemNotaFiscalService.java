package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class ItemNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);

    private final ItemNotaFiscalRepository repository;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository, ProdutoService produtoService,
                    NotaFiscalService notaFiscalService) {
        super();
        this.repository = repository;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
    }

    public ItemNotaFiscal salvar(ItemNotaFiscal entidade) {
        atualizarEstoque(entidade);
        calcularValorTotal(entidade);
        addValorTotal(entidade);
        return repository.save(entidade);
    }

    public List<ItemNotaFiscal> buscarTodos() {
        return repository.findAll();
    }

    public ItemNotaFiscal buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException("Entidade nao encontrada pelo id ");
        });
    }

    public ItemNotaFiscal update(ItemNotaFiscal itemNotaFiscal) {
        return repository.save(itemNotaFiscal);
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso!");
    }

    private void addValorTotal(ItemNotaFiscal item) {
        NotaFiscal notaFiscal = item.getNotaFiscal();
        Double valorTotalItem = item.getValorTotal();
        Double valorTotal = notaFiscal.getValorTotal();
        notaFiscal.setValorTotal(valorTotal);
        notaFiscalService.salvar(notaFiscal);

    }

    public void calcularValorTotal(ItemNotaFiscal itemNotaFiscal) {
        Integer quantidadeNotaFiscal = itemNotaFiscal.getQuantidade();
        Double precoUnitarioItemNotaFiscal = itemNotaFiscal.getPrecoUnitario();
        if (quantidadeNotaFiscal != null && precoUnitarioItemNotaFiscal != null) {
            Double valorTotal = quantidadeNotaFiscal * precoUnitarioItemNotaFiscal;
            itemNotaFiscal.setValorTotal(valorTotal);
        }
    }

    public void atualizarEstoque(ItemNotaFiscal itemNotaFiscal) {

        Produto produto = itemNotaFiscal.getProduto();
        Integer quantidade = itemNotaFiscal.getQuantidade();
        com.agrotis.trainees.crud.entity.enums.NotaFiscalTipo notaFiscalTipo = itemNotaFiscal.getNotaFiscal().getTipo();
        Integer estoque = itemNotaFiscal.getProduto().getEstoque();
        if (notaFiscalTipo == NotaFiscalTipo.ENTRADA) {
            produto.setEstoque(estoque + quantidade);
            System.out.println("Nota Fiscal TIPO Entrada");
        } else {
            if (estoque - quantidade >= 0) {
                produto.setEstoque(estoque - quantidade);
                System.out.println("Nota Fiscal TIPO Saida");
            } else {
                throw new IllegalArgumentException("Nao e possivel remover mais itens que o disponivel em estoque.");
            }
        }
        produtoService.salvar(produtoService.converteParaDto(produto));

    }

}
