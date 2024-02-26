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
import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.TipoNota;
import com.agrotis.trainees.crud.repository.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.ItemNotaService;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.utils.DtoUtils;
import com.agrotis.trainees.crud.utils.ValidacaoUtils;

@Service
public class ItemNotaServiceImpl implements ItemNotaService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaServiceImpl.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoServiceImpl produtoService;
    private final CabecalhoNotaRepository cabecalhoNotaRepository;

    public ItemNotaServiceImpl(NotaFiscalItemRepository repository, ProdutoServiceImpl produtoService, ProdutoRepository produtoRepository,
                    CabecalhoNotaRepository cabecalhoNotaRepository) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
        this.cabecalhoNotaRepository = cabecalhoNotaRepository;
    }

//    @Override   
//    @Transactional
//    public ItemNotaDto salvar(ItemNotaDto dto) {
//        ItemNota itemNota = DtoUtils.converteParaEntidade(dto);
//        CabecalhoNota cabecalhoSalvo = salvarOuBuscarCabecalho(itemNota.getCabecalhoNota());
//        Produto produtoSalvo = salvarOuBuscarProduto(itemNota.getProduto());
//        BigDecimal quantidade = itemNota.getQuantidade();
//        produtoSalvo.setQuantidadeEstoque(quantidade);
//        itemNota.setValorTotal(calcularValorTotal(itemNota));
//        produtoSalvo.setCustoTotal(itemNota.getValorTotal());
//        BigDecimal custoMedio = CustoMedioServiceImpl.calcularCustoMedio(itemNota.getValorTotal(), quantidade);
//        produtoSalvo.setCustoMedio(custoMedio);
//        itemNota.setCabecalhoNota(cabecalhoSalvo);
//        
//        itemNota.setProduto(produtoSalvo);
//        atualizarEstoque(itemNota);
//        adicionarValorTotalCabecalho(itemNota);
//        ItemNota entidadeSalva = repository.save(itemNota);
//        return DtoUtils.converteParaDto(entidadeSalva);
//    }
    
    @Override   
    @Transactional
    public ItemNotaDto salvar(ItemNotaDto dto) {
        ItemNota itemNota = DtoUtils.converteParaEntidade(dto);
        CabecalhoNota cabecalhoSalvo = salvarOuBuscarCabecalho(itemNota.getCabecalhoNota());
        Produto produtoSalvo = salvarOuBuscarProduto(itemNota.getProduto());
        BigDecimal quantidade = itemNota.getQuantidade();
        produtoSalvo.setQuantidadeEstoque(quantidade);
        itemNota.setValorTotal(calcularValorTotal(itemNota));
        produtoSalvo.setCustoTotal(itemNota.getValorTotal());
        BigDecimal custoMedio = CustoMedioServiceImpl.calcularCustoMedio(produtoSalvo.getCustoTotal(), quantidade);
        produtoSalvo.setCustoMedio(custoMedio);
        itemNota.setCabecalhoNota(cabecalhoSalvo);
        
        itemNota.setProduto(produtoSalvo);
        atualizarEstoque(itemNota);
        adicionarValorTotalCabecalho(itemNota);
        calcularValorTotalProduto(itemNota); // Adicionando o cálculo do custo médio do produto
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

    private void atualizarEstoque(ItemNota itemNota) {
        Produto produto = itemNota.getProduto();
        BigDecimal quantidade = itemNota.getProduto().getQuantidadeEstoque();
        TipoNota tipoNota = itemNota.getCabecalhoNota().getNotaFiscalTipo();
        BigDecimal quantidadeEstoque = produto.getQuantidadeEstoque();

        ValidacaoUtils.validarQuantidadeNaoNegativa(quantidade);

        if (tipoNota == TipoNota.SAIDA) {
            ValidacaoUtils.validarQuantidadeEstoqueSuficiente(quantidadeEstoque, quantidade);
            produto.setQuantidadeEstoque(quantidadeEstoque.subtract(quantidade));
        } else if (tipoNota == TipoNota.ENTRADA) {
            produto.setQuantidadeEstoque(quantidade);
        }
    }
    
    private void adicionarValorTotalCabecalho(ItemNota itemNota) {
        CabecalhoNota cabecalhoNota = itemNota.getCabecalhoNota();
        BigDecimal valorTotalCabecalho = cabecalhoNota.getValorTotal();
        BigDecimal valorTotalItem = itemNota.getValorTotal();
        valorTotalCabecalho = valorTotalCabecalho.add(valorTotalItem);
        cabecalhoNota.setValorTotal(valorTotalCabecalho);
    }

    public Produto salvarOuBuscarProduto(Produto produto) {
        if (produto != null && produto.getId() != null) {
            return produtoRepository.findById(produto.getId()).orElseThrow(
                            () -> new EntidadeNaoEncontradaException("Produto com o ID " + produto.getId() + " não encontrado"));
        } else {
            ProdutoDto produtoSalvo = produtoService.salvar(DtoUtils.converteParaDto(produto));
            return DtoUtils.converteParaEntidade(produtoSalvo);
        }
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
        BigDecimal quantidadeTotal = produto.getQuantidadeEstoque().add(itemNota.getQuantidade());
        BigDecimal custoTotalAtualizado = produto.getCustoTotal().add(itemNota.getValorTotal());
        
        if (quantidadeTotal.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal custoMedio = custoTotalAtualizado.divide(quantidadeTotal, 2, RoundingMode.HALF_UP);
            produto.setCustoMedio(custoMedio);
        } else {
            // Se a quantidade total for zero, o custo médio também deve ser zero para evitar divisão por zero
            produto.setCustoMedio(BigDecimal.ZERO);
        }
    }


}
