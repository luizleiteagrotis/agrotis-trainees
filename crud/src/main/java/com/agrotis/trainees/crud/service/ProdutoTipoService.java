package com.agrotis.trainees.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoTipoRepository;

@Service
public class ProdutoTipoService {

    private final ProdutoTipoRepository produtoTipoRepository;
    private final ParceiroNegocioTipoService parceiroNegocioService;

    @Autowired
    public ProdutoTipoService(ProdutoTipoRepository produtoTipoRepository, ParceiroNegocioTipoService parceiroNegocioService) {
        this.produtoTipoRepository = Objects.requireNonNull(produtoTipoRepository, "produtoTipoRepository cannot be null");
        this.parceiroNegocioService = Objects.requireNonNull(parceiroNegocioService, "parceiroNegocioService cannot be null");
    }

    public void adicionarEntradaEstoque(Produto produto, BigDecimal quantidade, BigDecimal valorUnitario) {
        Objects.requireNonNull(quantidade, "Quantidade cannot be null");
        Objects.requireNonNull(valorUnitario, "Valor unitário cannot be null");

        if (quantidade.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        if (valorUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor unitário deve ser maior que zero");
        }

        BigDecimal custoTotal = valorUnitario.multiply(quantidade);
        BigDecimal quantidadeTotal = produto.getEstoque().add(quantidade);
        BigDecimal custoMedio = calcularCustoMedio(custoTotal, quantidadeTotal);

        produto.setCustoMedio(custoMedio.setScale(2, RoundingMode.HALF_UP));
        produto.setEstoque(quantidadeTotal);
    }

    private BigDecimal calcularCustoMedio(BigDecimal custoTotal, BigDecimal quantidadeTotal) {
        if (quantidadeTotal.compareTo(BigDecimal.ZERO) <= 0 || custoTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantidade e Custo Total devem ser maiores que zero");
        }
        return custoTotal.divide(quantidadeTotal, 2, RoundingMode.HALF_UP);
    }

    @Transactional
    public ProdutoDto inserir(ProdutoDto dto) {
        Produto entidade = converterParaEntidade(dto);
        Produto savedProduto = produtoTipoRepository.save(entidade);
        return converterParaDto(savedProduto);
    }

    public Optional<ProdutoDto> buscarPorId(Integer id) {
        return produtoTipoRepository.findById(id).map(this::converterParaDto);
    }

    public List<ProdutoDto> listarTodos() {
        List<Produto> entidades = produtoTipoRepository.findAll();
        return entidades.stream().map(this::converterParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        produtoTipoRepository.deleteById(id);
    }

    public ProdutoDto atualizar(ProdutoDto dto) {
        Produto entidade = produtoTipoRepository.findById(dto.getId()).orElse(null);
        if (entidade != null) {
            entidade.setDescricao(dto.getDescricao());
            entidade.setEstoque(dto.getEstoque());

            ParceiroNegocio fabricante = converterDtoParaParceiroNegocio(dto.getFabricante());
            entidade.setFabricante(fabricante);

            entidade.setDataFabricacao(dto.getDataFabricacao());
            entidade.setDataValidade(dto.getDataValidade());

            entidade = produtoTipoRepository.save(entidade);
            return converterParaDto(entidade);
        } else {
            return null;
        }
    }

    private ParceiroNegocio converterDtoParaParceiroNegocio(ParceiroNegocioDto dto) {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setId(dto.getId());
        parceiroNegocio.setNome(dto.getNome());
        parceiroNegocio.setInscricaoFiscal(dto.getInscricaoFiscal());
        parceiroNegocio.setEndereco(dto.getEndereco());
        parceiroNegocio.setTelefone(dto.getTelefone());
        return parceiroNegocio;
    }

    public ProdutoDto converterParaDto(Produto entidade) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setDescricao(entidade.getDescricao());
        dto.setEstoque(entidade.getEstoque());

        ParceiroNegocioDto fabricanteDto = parceiroNegocioService.converterParaDto(entidade.getFabricante());
        dto.setFabricante(fabricanteDto);

        dto.setDataFabricacao(entidade.getDataFabricacao());
        dto.setDataValidade(entidade.getDataValidade());
        return dto;
    }

    public Produto converterParaEntidade(ProdutoDto dto) {
        Produto entidade = new Produto();
        entidade.setId(dto.getId());
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setId(dto.getFabricante().getId());
        parceiroNegocio.setNome(dto.getFabricante().getNome());
        parceiroNegocio.setInscricaoFiscal(dto.getFabricante().getInscricaoFiscal());
        parceiroNegocio.setEndereco(dto.getFabricante().getEndereco());
        parceiroNegocio.setTelefone(dto.getFabricante().getTelefone());

        entidade.setFabricante(parceiroNegocio);

        entidade.setDescricao(dto.getDescricao());
        entidade.setEstoque(dto.getEstoque());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());

        return entidade;
    }
}
