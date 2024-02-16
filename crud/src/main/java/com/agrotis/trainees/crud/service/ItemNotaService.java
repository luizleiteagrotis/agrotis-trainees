package com.agrotis.trainees.crud.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.CabecalhoNotaDto;
import com.agrotis.trainees.crud.dtos.ItemNotaDto;
import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;
import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.TipoNota;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.utils.DtoUtils;
import com.agrotis.trainees.crud.utils.ValidacaoUtils;

@Service
public class ItemNotaService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaService.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoService produtoService;
    private final CabecalhoNotaService cabecalhoNotaService;
    private final ParceiroNegocioService parceiroNegocioService;

    public ItemNotaService(NotaFiscalItemRepository repository, ProdutoService produtoService,
                    CabecalhoNotaService cabecalhoNotaService,ParceiroNegocioService parceiroNegocioService) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.cabecalhoNotaService = cabecalhoNotaService;
        this.parceiroNegocioService = parceiroNegocioService;
    }
    
    @Transactional
    public ItemNotaDto salvar(ItemNotaDto dto) {
        ItemNota entidade = DtoUtils.converteParaEntidade(dto);

        CabecalhoNota cabecalhoNota = entidade.getCabecalhoNota();
        CabecalhoNotaDto cabecalhoNotaDtoSalvo = cabecalhoNotaService.salvar(DtoUtils.converteParaDto(cabecalhoNota));
        entidade.setCabecalhoNota(DtoUtils.converteParaEntidade(cabecalhoNotaDtoSalvo));

        calcularValorTotal(entidade);
        atualizarEstoque(entidade);

        entidade = repository.save(entidade);
        
        adicionarValorTotalCabecalho(entidade);

        return DtoUtils.converteParaDto(entidade);
    }

    public List<ItemNotaDto> listarTodos() {
        return repository.findAll()
                        .stream()
                        .map(DtoUtils::converteParaDto)
                        .collect(Collectors.toList());
    }

    public ItemNotaDto buscarPorId(Integer id) {
        return repository.findById(id)
                        .map(DtoUtils::converteParaDto)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
    }

    @Transactional
    public ItemNotaDto atualizar(Integer id, ItemNotaDto notaFiscalItemDto) {
        ItemNota itemNotaExistente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));

        atualizarItemNota(itemNotaExistente, notaFiscalItemDto);

        ItemNota itemNotaAtualizada = repository.save(itemNotaExistente);
        return DtoUtils.converteParaDto(itemNotaAtualizada);
    }

    private void atualizarItemNota(ItemNota itemNota, ItemNotaDto notaFiscalItemDto) {
        CabecalhoNota cabecalhoNota = notaFiscalItemDto.getCabecalhoNota();
        itemNota.setCabecalhoNota(cabecalhoNota);
        itemNota.setPrecoUnitario(notaFiscalItemDto.getPrecoUnitario());
        itemNota.setProduto(notaFiscalItemDto.getProduto());
        itemNota.setQuantidade(notaFiscalItemDto.getQuantidade());
        itemNota.setValorTotal(notaFiscalItemDto.getValorTotal());
    }



    public void deletarPorId(Integer id) {
        repository.findById(id).map(entidade -> {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
            return entidade;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));

    }

    public void calcularValorTotal(ItemNota notaFiscalItem) {
        Integer quantidade = notaFiscalItem.getQuantidade();
        Double precoUnitario = notaFiscalItem.getPrecoUnitario();
        if (quantidade != null && precoUnitario != null) {
            Double valorTotal = quantidade * precoUnitario;
            notaFiscalItem.setValorTotal(valorTotal);
        }
    }

    private void atualizarEstoque(ItemNota itemNota) {
        Produto produto = itemNota.getProduto();
        Integer quantidade = itemNota.getQuantidade();
        TipoNota notaFiscalTipo = itemNota.getCabecalhoNota().getNotaFiscalTipo();
        Integer quantidadeProduto = produto.getQuantidadeEstoque();

        ValidacaoUtils.validarQuantidadeNaoNegativa(quantidade);

        if (notaFiscalTipo == TipoNota.SAIDA) {
            ValidacaoUtils.validarQuantidadeEstoqueSuficiente(quantidadeProduto, quantidade);
        }

        if (notaFiscalTipo == TipoNota.ENTRADA) {
            produto.setQuantidadeEstoque(quantidadeProduto + quantidade);
        } else {
            produto.setQuantidadeEstoque(quantidadeProduto - quantidade);
        }

        produtoService.salvar(DtoUtils.converteParaDto(produto));
    }

    private void adicionarValorTotalCabecalho(ItemNota item) {
        CabecalhoNota cabecalho = item.getCabecalhoNota();
        Double valorTotalItem = item.getValorTotal();
        cabecalho.setValorTotal(valorTotalItem);
    }
    


}
