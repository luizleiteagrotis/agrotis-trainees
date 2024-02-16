package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class ItemNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);

    private final ItemNotaFiscalRepository repository;
    private final ParceiroNegocioRepository parceiroNegocio;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;
    private final ParceiroNegocioService parceiroService;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository, ParceiroNegocioRepository parceiroNegocio,
                    ProdutoRepository produtoRepository, ProdutoService produtoService, NotaFiscalService notaFiscalService,
                    ParceiroNegocioService parceiroService) {
        super();
        this.repository = repository;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
        this.parceiroService = parceiroService;
        this.parceiroNegocio = parceiroNegocio;
        this.produtoRepository = produtoRepository;
    }

    /*
     * public NotaFiscalItemDto salvar(NotaFiscalItemDto dto) {
     * 
     * }
     */

    public List<NotaFiscalItemDto> listarTodos() {
        return repository.findAll().stream().map(ItemNotaFiscalService::converteParaDto).collect(Collectors.toList());
    }

    public NotaFiscalItemDto buscarPorId(Integer id) {
        return repository.findById(id).map(ItemNotaFiscalService::converteParaDto).orElseThrow(
                        () -> new EntidadeNaoEncontradaException("A NOTA FISCAL com o ID: " + id + "nao foi encontrada!"));
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
        notaFiscal.setValorTotal(valorTotalItem);

        notaFiscalService.salvar(notaFiscalService.converteParaDto(notaFiscal));
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

    public static ItemNotaFiscal converteParaEntidade(NotaFiscalItemDto dto) {
        ItemNotaFiscal entidade = new ItemNotaFiscal();

        entidade.setPrecoUnitario(dto.getPrecoUnitario());
        entidade.setQuantidade(dto.getQuantidade());
        entidade.setValorTotal(dto.getValorTotal());
        entidade.setNotaFiscal(dto.getNotaFiscal());
        entidade.setProduto(dto.getProduto());

        return entidade;
    }

    public static NotaFiscalItemDto converteParaDto(ItemNotaFiscal entidade) {
        NotaFiscalItemDto dto = new NotaFiscalItemDto();

        dto.setId(entidade.getId());
        dto.setPrecoUnitario(entidade.getPrecoUnitario());
        dto.setQuantidade(dto.getQuantidade());
        dto.setValorTotal(entidade.getValorTotal());
        dto.setNotaFiscal(entidade.getNotaFiscal());
        dto.setProduto(entidade.getProduto());

        return dto;
    }

}
