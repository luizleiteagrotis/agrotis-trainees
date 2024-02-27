package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.QuantidadeInsuficienteException;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.utils.ProdutoDTOMapper;

@Service
public class CalcularEstoqueService {

    private final ItemNotaFiscalRepository repository;

    private final ProdutoService produtoService;

    private final ProdutoDTOMapper mapperProduto;

    public CalcularEstoqueService(ItemNotaFiscalRepository repository, ProdutoService produtoService,
                    ProdutoDTOMapper mapperProduto) {
        super();
        this.repository = repository;
        this.produtoService = produtoService;
        this.mapperProduto = mapperProduto;
    }

    public void atualizarEstoque(ItemNotaFiscal item) throws QuantidadeInsuficienteException {
        Produto produto = item.getProduto();
        String tipo = item.getNotaFiscal().getNotaFiscalTipo().getNome();
        int quantidade = item.getQuantidade();
        int quantidadeProduto = produto.getQuantidadeEstoque();

        ItemNotaFiscal itemExistente = repository.findByProdutoAndNotaFiscal(item.getProduto(), item.getNotaFiscal());

        int diferencaQuantidade = itemExistente != null ? quantidade - itemExistente.getQuantidade() : quantidade;
        if (tipo.equalsIgnoreCase("SAÍDA") && (quantidadeProduto - diferencaQuantidade) < 0) {
            throw new QuantidadeInsuficienteException(
                            "Quantidade insuficiente em estoque para a saída do produto. Quantidade em estoque: "
                                            + quantidadeProduto + " " + ". Quantidade item saída: " + diferencaQuantidade);
        }

        int novaQuantidadeEstoque = tipo.equalsIgnoreCase("ENTRADA") ? quantidadeProduto + diferencaQuantidade
                        : quantidadeProduto - diferencaQuantidade;
        produto.setQuantidadeEstoque(novaQuantidadeEstoque);

        produtoService.atualizar(mapperProduto.converterParaDto(produto));
    }

}
