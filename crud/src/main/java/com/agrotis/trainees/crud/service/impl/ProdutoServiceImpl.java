package com.agrotis.trainees.crud.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.ProdutoService;
import com.agrotis.trainees.crud.service.exceptions.CampoVazioOuNuloException;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.utils.DtoUtils;
import com.agrotis.trainees.crud.utils.ValidacaoUtils;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoServiceImpl.class);

    private final ProdutoRepository produtoRepository;
    private final ParceiroNegocioRepository parceiroNegocioRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ParceiroNegocioRepository parceiroNegocioRepository) {
        this.produtoRepository = produtoRepository;
        this.parceiroNegocioRepository = parceiroNegocioRepository;
    }

    public ProdutoDto salvar(ProdutoDto produtoDto) {
        validarProdutoDto(produtoDto);

        Produto produto = DtoUtils.converteParaEntidade(produtoDto);
        ParceiroNegocio fabricante = produto.getFabricante();
        ParceiroNegocio fabricanteSalvo = salvarOuBuscarFabricante(fabricante);
        produto.setFabricante(fabricanteSalvo);
        Produto produtoSalvo = produtoRepository.save(produto);
        LOG.info("Salvando o produto {}", produtoDto.getDescricao());
        return DtoUtils.converteParaDto(produtoSalvo);
    }


    public ProdutoDto buscaPeloId(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
        return DtoUtils.converteParaDto(produto);
    }

    public List<ProdutoDto> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(DtoUtils::converteParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        Produto produto = produtoRepository.findById(id)
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

    private ParceiroNegocio salvarOuBuscarFabricante(ParceiroNegocio fabricante) {
        if (fabricante.getId() != null) {
            return parceiroNegocioRepository.findById(fabricante.getId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Fabricante com o ID " + fabricante.getId() + " não encontrado"));
        } else {
            return parceiroNegocioRepository.save(fabricante);
        }
    }
    
    private void atualizarProdutoExistente(Produto produtoExistente, ProdutoDto produtoDto) {
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
}
