package com.agrotis.trainees.crud.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.ItemNotaDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.service.CabecalhoNotaService;
import com.agrotis.trainees.crud.service.ItemNotaService;
import com.agrotis.trainees.crud.service.ProdutoService;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.utils.DtoUtils;

@Service
public class ItemNotaServiceImpl implements ItemNotaService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaServiceImpl.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoService produtoService;
    private final CabecalhoNotaRepository cabecalhoNotaRepository;
    private final CabecalhoNotaService cabecalhoNotaService;

    public ItemNotaServiceImpl(NotaFiscalItemRepository repository, ProdutoServiceImpl produtoService,
                    CabecalhoNotaRepository cabecalhoNotaRepository, CabecalhoNotaService cabecalhoNotaService) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.cabecalhoNotaRepository = cabecalhoNotaRepository;
        this.cabecalhoNotaService = cabecalhoNotaService;
    }

    @Override
    @Transactional
    public ItemNotaDto salvar(ItemNotaDto dto) {
        ItemNota itemNota = DtoUtils.converteParaEntidade(dto);
        CabecalhoNota cabecalhoSalvo = salvarOuBuscarCabecalho(itemNota.getCabecalhoNota());
        Produto produtoSalvo = produtoService.salvarOuBuscarProduto(itemNota.getProduto());
        calcularValorTotal(itemNota);
        produtoSalvo.setCustoTotal(itemNota.getValorTotal());
        itemNota.setCabecalhoNota(cabecalhoSalvo);

        itemNota.setProduto(produtoSalvo);
        produtoService.atualizarEstoque(itemNota);
        cabecalhoNotaService.adicionarValorTotalCabecalho(itemNota);
        calcularValorTotalProduto(itemNota);

        ItemNota entidadeSalva = repository.save(itemNota);
        return DtoUtils.converteParaDto(entidadeSalva);
    }

    @Override
    public List<ItemNotaDto> listarTodos() {
        return repository.findAll().stream().map(DtoUtils::converteParaDto).collect(Collectors.toList());
    }

    @Override
    public ItemNotaDto buscarPorId(Integer id) {
        return repository.findById(id).map(DtoUtils::converteParaDto)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
    }

    @Override
    @Transactional
    public ItemNotaDto atualizar(Integer id, ItemNotaDto notaFiscalItemDto) {
        ItemNota itemNotaExistente = repository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));

        atualizarItemNota(itemNotaExistente, notaFiscalItemDto);

        ItemNota itemNotaAtualizada = repository.save(itemNotaExistente);
        return DtoUtils.converteParaDto(itemNotaAtualizada);
    }

    @Override
    public void deletarPorId(Integer id) {
        repository.findById(id).map(entidade -> {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
            return entidade;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));

    }

    private void atualizarItemNota(ItemNota itemNota, ItemNotaDto notaFiscalItemDto) {
        itemNota.setCabecalhoNota(notaFiscalItemDto.getCabecalhoNota());
        itemNota.setPrecoUnitario(notaFiscalItemDto.getPrecoUnitario());
        itemNota.setProduto(notaFiscalItemDto.getProduto());
        itemNota.setQuantidade(notaFiscalItemDto.getQuantidade());
        itemNota.setValorTotal(notaFiscalItemDto.getValorTotal());
    }

    public BigDecimal calcularValorTotal(ItemNota notaFiscalItem) {
        BigDecimal quantidade = notaFiscalItem.getQuantidade();
        BigDecimal precoUnitario = notaFiscalItem.getPrecoUnitario();

        if (quantidade != null && precoUnitario != null) {
            BigDecimal valorTotal = quantidade.multiply(precoUnitario);
            notaFiscalItem.setValorTotal(valorTotal);
            return valorTotal;
        }

        return BigDecimal.ZERO;
    }

    private CabecalhoNota salvarOuBuscarCabecalho(CabecalhoNota cabecalhoNota) {
        if (cabecalhoNota.getId() != null) {
            return cabecalhoNotaRepository.findById(cabecalhoNota.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException(
                            "Cabeçalho com o ID " + cabecalhoNota.getId() + " não encontrado"));
        } else {
            return cabecalhoNotaRepository.save(cabecalhoNota);
        }
    }

    private void calcularValorTotalProduto(ItemNota itemNota) {
        Produto produto = itemNota.getProduto();
        BigDecimal quantidadeTotal = produto.getQuantidadeEstoque();
        BigDecimal custoTotalDoProduto = produto.getCustoTotal();
        BigDecimal custoTotalDaNota = itemNota.getValorTotal();
        
        if (custoTotalDoProduto.compareTo(BigDecimal.ZERO) > 0) {
            produto.setCustoTotal(custoTotalDoProduto);
        }else {
        produto.setCustoTotal(custoTotalDoProduto.add(custoTotalDaNota));
        }

        if (quantidadeTotal.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal custoMedio = custoTotalDoProduto.divide(quantidadeTotal, 2, RoundingMode.HALF_UP);
            produto.setCustoMedio(custoMedio);
        } else {
            produto.setCustoMedio(BigDecimal.ZERO);
        }
    }

}
