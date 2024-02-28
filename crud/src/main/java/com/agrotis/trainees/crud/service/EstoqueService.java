package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.DescricaoExisteException;
import com.agrotis.trainees.crud.exception.EstoqueZeradoException;
import com.agrotis.trainees.crud.exception.ValorDiferenteException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.wrapper.NotaFiscalWrapper;
import com.agrotis.trainees.crud.wrapper.ProdutoWrapper;

@Component
public class EstoqueService {

    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;
    private final ItemService itemService;
    private final ProdutoWrapper wrapper;
    private final NotaFiscalWrapper notaFiscalWrapper;
    private final NotaFiscalItemRepository notaFiscalItemRepository;

    public EstoqueService(ProdutoService produtoService, NotaFiscalService notaFiscalService, ItemService itemService,
                    ProdutoWrapper wrapper, NotaFiscalWrapper notaFiscalWrapper,
                    NotaFiscalItemRepository notaFiscalItemRepository) {
        super();
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
        this.itemService = itemService;
        this.wrapper = wrapper;
        this.notaFiscalWrapper = notaFiscalWrapper;
        this.notaFiscalItemRepository = notaFiscalItemRepository;
    }

    public NotaFiscalItem atualizarEstoque(NotaFiscalItem entidade, NotaFiscalItem item)
                    throws EstoqueZeradoException, DescricaoExisteException {
        Produto produto = produtoService.buscarPorId(entidade.getProduto().getId());
        Produto produto2 = produtoService.buscarPorId(item.getProduto().getId());
        NotaFiscal nota = notaFiscalService.buscarPorId(entidade.getIdNota().getId());

        if (nota.getTipo().getId() == 1) {
            if (produto.getId() != produto2.getId()) {
                if (produto2.getEstoque() - item.getQuantidade() >= 0) {
                    produto.setEstoque(produto.getEstoque() + entidade.getQuantidade());
                    produto2.setEstoque(produto2.getEstoque() - item.getQuantidade());
                    produtoService.atualizar(wrapper.converterParaDto(produto));
                    produtoService.atualizar(wrapper.converterParaDto(produto2));
                } else {
                    throw new EstoqueZeradoException("Valor em estoque do produto indisponível");
                }

            } else if (produto2.getEstoque() + entidade.getQuantidade() - (item.getQuantidade()) >= 0) {

                produto.setEstoque(produto.getEstoque() + entidade.getQuantidade() - item.getQuantidade());
                produtoService.atualizar(wrapper.converterParaDto(produto));
            } else {
                throw new EstoqueZeradoException("Valor em estoque do produto indisponível");
            }

        } else {
            if (produto.getId() != produto2.getId()) {
                if (produto.getEstoque() - entidade.getQuantidade() >= 0) {
                    produto2.setEstoque(produto2.getEstoque() + item.getQuantidade());
                    produto.setEstoque(produto.getEstoque() - entidade.getQuantidade());
                    produtoService.atualizar(wrapper.converterParaDto(produto));
                    produtoService.atualizar(wrapper.converterParaDto(produto2));
                } else {
                    throw new EstoqueZeradoException("Valor em estoque do produto indisponível");
                }

            } else if (produto.getEstoque() + item.getQuantidade() - entidade.getQuantidade() >= 0) {
                produto.setEstoque(produto.getEstoque() + item.getQuantidade() - entidade.getQuantidade());
                produtoService.atualizar(wrapper.converterParaDto(produto));
            } else {
                throw new EstoqueZeradoException("Valor em estoque do produto indisponível");
            }
        }
        return entidade;
    }

    public NotaFiscalItem alterarEstoque(NotaFiscalItem item)
                    throws EstoqueZeradoException, ValorDiferenteException, DescricaoExisteException {
        NotaFiscal nota = notaFiscalService.buscarPorId(item.getIdNota().getId());
        Produto produto = produtoService.buscarPorId(item.getProduto().getId());

        if (nota.getTipo().getId() == 1) {
            produto.setEstoque(produto.getEstoque() + item.getQuantidade());
            item = itemService.validarNotaEItem(item);

            ProdutoDto produto2 = produtoService.atualizar(wrapper.converterParaDto(produto));
        } else {
            if (produto.getEstoque() - item.getQuantidade() < 0) {
                throw new EstoqueZeradoException("A quantidade em estoque não é suficiente");
            }
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            item = itemService.validarNotaEItem(item);
            ProdutoDto produto2 = produtoService.atualizar(wrapper.converterParaDto(produto));
        }
        return item;
    }

    public void deletarEstoque(Integer id) throws DescricaoExisteException, EstoqueZeradoException {
        NotaFiscalItem item = notaFiscalItemRepository.findById(id).orElse(null);
        NotaFiscal nota = notaFiscalService.buscarPorId(item.getIdNota().getId());
        Produto produto = produtoService.buscarPorId(item.getProduto().getId());

        try {
            if (nota.getTipo().getId() == 1) {

                if (produto.getEstoque() - item.getQuantidade() < 0) {
                    throw new EstoqueZeradoException("A quantidade em estoque não é suficiente");
                }
                produto.setEstoque(produto.getEstoque() - item.getQuantidade());

                ProdutoDto produto2 = produtoService.atualizar(wrapper.converterParaDto(produto));

                nota.setValorTotal(nota.getValorTotal().subtract(item.getValorTotal()));
                NotaFiscalDto nota2 = notaFiscalService.atualizar(notaFiscalWrapper.converterParaDto(nota));
                notaFiscalItemRepository.deleteById(id);
            } else {
                produto.setEstoque(produto.getEstoque() + item.getQuantidade());
                ProdutoDto produto2 = produtoService.atualizar(wrapper.converterParaDto(produto));
                nota.setValorTotal(nota.getValorTotal().add(item.getValorTotal()));
                NotaFiscalDto nota2 = notaFiscalService.atualizar(notaFiscalWrapper.converterParaDto(nota));
                notaFiscalItemRepository.deleteById(id);
            }
        } catch (EstoqueZeradoException e) {
            System.out.println("Erro: " + e.getMessage());
            throw e;
        }

    }
}
