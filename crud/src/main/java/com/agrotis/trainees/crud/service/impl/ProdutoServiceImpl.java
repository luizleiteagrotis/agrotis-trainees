package com.agrotis.trainees.crud.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.TipoNota;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.ProdutoService;
import com.agrotis.trainees.crud.service.exceptions.CampoVazioOuNuloException;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.utils.DtoUtils;
import com.agrotis.trainees.crud.utils.ValidacaoUtils;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoServiceImpl.class);

    private final ProdutoRepository produtoRepository;
    private final ParceiroNegocioRepository parceiroNegocioRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ParceiroNegocioRepository parceiroNegocioRepository) {
        this.produtoRepository = produtoRepository;
        this.parceiroNegocioRepository = parceiroNegocioRepository;
    }

    @Override
    public ProdutoDto salvar(ProdutoDto produtoDto) {
        validarProdutoDto(produtoDto);
        Produto produto = DtoUtils.converteParaEntidade(produtoDto);
        ParceiroNegocio fabricanteSalvo = salvarOuBuscarFabricante(produto.getFabricante());
        produto.setFabricante(fabricanteSalvo);
        Produto produtoSalvo = produtoRepository.save(produto);
        LOG.info("Salvando o produto {}", produtoDto.getDescricao());
        return DtoUtils.converteParaDto(produtoSalvo);
    }

    @Override
    public ProdutoDto buscaPeloId(Integer id) {
        Produto produto = produtoRepository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
        return DtoUtils.converteParaDto(produto);
    }

    @Override
    public List<ProdutoDto> buscarTodos() {
        return produtoRepository.findAll().stream().map(DtoUtils::converteParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        produtoRepository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto com o ID " + id + " não encontrado"));
        produtoRepository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

    public ProdutoDto atualizar(Integer id, ProdutoDto produtoDto) {
        Produto produtoExistente = produtoRepository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto com o ID " + id + " não encontrado"));

        atualizarProdutoExistente(produtoExistente, produtoDto);

        LOG.info("Atualizando o produto: {}", produtoExistente.getDescricao());

        Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        return DtoUtils.converteParaDto(produtoAtualizado);
    }

    private void validarProdutoDto(ProdutoDto produtoDto) {
        if (ValidacaoUtils.isProdutoFieldEmptyOrNull(produtoDto)) {
            throw new CampoVazioOuNuloException("Preencha todos os campos obrigatórios de produto.");
        }
    }

    public ParceiroNegocio salvarOuBuscarFabricante(ParceiroNegocio fabricante) {
        if (fabricante.getId() != null) {
            return parceiroNegocioRepository.findById(fabricante.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException(
                            "Fabricante com o ID " + fabricante.getId() + " não encontrado"));
        } else {
            return parceiroNegocioRepository.save(fabricante);
        }
    }

    public void atualizarProdutoExistente(Produto produtoExistente, ProdutoDto produtoDto) {
        produtoExistente.setDescricao(produtoDto.getDescricao());

        ParceiroNegocio fabricanteDto = produtoDto.getFabricante();
        if (fabricanteDto != null && fabricanteDto.getId() != null) {
            ParceiroNegocio fabricanteExistente = salvarOuBuscarFabricante(fabricanteDto);
            produtoExistente.setFabricante(fabricanteExistente);
        }

        produtoExistente.setQuantidadeEstoque(produtoDto.getQuantidadeEstoque());
        produtoExistente.setDataValidade(produtoDto.getDataValidade());
        produtoExistente.setDataFabricacao(produtoDto.getDataFabricacao());
    }

    public void atualizarEstoque(ItemNota itemNota) {
        Produto produto = itemNota.getProduto();
        BigDecimal quantidadeNota = itemNota.getQuantidade();
        BigDecimal quantidadeEstoque = produto.getQuantidadeEstoque();
        TipoNota tipoNota = itemNota.getCabecalhoNota().getNotaFiscalTipo();

        ValidacaoUtils.validarQuantidadeNaoNegativa(quantidadeNota);

        if (tipoNota == TipoNota.SAIDA) {
            ValidacaoUtils.validarQuantidadeEstoqueSuficiente(quantidadeEstoque, quantidadeNota);
            produto.setQuantidadeEstoque(quantidadeEstoque.subtract(quantidadeNota));
        } else if (tipoNota == TipoNota.ENTRADA) {
            if (quantidadeEstoque == null || quantidadeEstoque.equals(BigDecimal.ZERO)) {
                produto.setQuantidadeEstoque(quantidadeNota);
            } else {
                produto.setQuantidadeEstoque(quantidadeEstoque.add(quantidadeNota));

            }

        }
    }

    public Produto salvarOuBuscarProduto(Produto produto) {
        if (produto != null && produto.getId() != null) {
            return produtoRepository.findById(produto.getId()).orElseThrow(
                            () -> new EntidadeNaoEncontradaException("Produto com o ID " + produto.getId() + " não encontrado"));
        } else {
            ProdutoDto produtoSalvo = salvar(DtoUtils.converteParaDto(produto));
            return DtoUtils.converteParaEntidade(produtoSalvo);
        }
    }

}
