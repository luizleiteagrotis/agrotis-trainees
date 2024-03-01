package com.agrotis.trainees.crud.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
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
    private final NotaFiscalRepository notaFiscalRepository;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository, ParceiroNegocioRepository parceiroNegocio,
                    ProdutoRepository produtoRepository, ProdutoService produtoService, NotaFiscalService notaFiscalService,
                    ParceiroNegocioService parceiroService, NotaFiscalRepository notaFiscalRepository) {
        super();
        this.repository = repository;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
        this.parceiroService = parceiroService;
        this.parceiroNegocio = parceiroNegocio;
        this.produtoRepository = produtoRepository;
        this.notaFiscalRepository = notaFiscalRepository;
    }


    
    public NotaFiscalItemDto salvar(NotaFiscalItemDto dto) {
        ItemNotaFiscal entidade = converteParaEntidade(dto);

        NotaFiscal notaFiscal = entidade.getNotaFiscal();
        
        Optional<NotaFiscal> byId = notaFiscalRepository.findById(notaFiscal.getId());
        NotaFiscal notaFiscalDois = new NotaFiscal();
        notaFiscalDois.setId(notaFiscalDois.getId());
        notaFiscalDois.setData(notaFiscalDois.getData());
        notaFiscalDois.setNumero(notaFiscalDois.getNumero());
        notaFiscalDois.setValorTotal(notaFiscalDois.getValorTotal());
        notaFiscalDois.setTipo(notaFiscalDois.getTipo());
        notaFiscalDois.setParceiroNegocio(notaFiscalDois.getParceiroNegocio());
        
        repository.save(null)
        entidade.setNotaFiscal(notaFiscalDois);
        
        Produto produto = entidade.getProduto();
        
        Optional<Produto> produtoById = produtoRepository.findById(notaFiscal.getId());
        Produto produtoDois = new Produto ();
        produtoDois.setId(produtoDois.getId());
        produtoDois.setDataFabricacao(produtoDois.getDataFabricacao());
        produtoDois.setDataValidade(produtoDois.getDataValidade());
        produtoDois.setDescricao(produtoDois.getDescricao());
        produtoDois.setEstoque(produtoDois.getEstoque());
        produtoDois.setNome(produtoDois.getNome());
        produtoDois.setFabricante(produtoDois.getFabricante());

        
        entidade.setProduto(produtoDois);

        calcularValorTotal(entidade);
        produtoService.atualizarEstoque(entidade); // Chamada para o método atualizarEstoque de ProdutoService
        adicionarValorTotalCabecalho(entidade);

        addValorTotal(entidade);
        repository.save(entidade);

        return converteParaDto(entidade);
    }
    
   
    
    
    public List<NotaFiscalItemDto> listarTodos() {
        return repository.findAll().stream().map(ItemNotaFiscalService::converteParaDto).collect(Collectors.toList());
    }

    public NotaFiscalItemDto buscarPorId(Integer id) {
        return repository.findById(id).map(ItemNotaFiscalService::converteParaDto).orElseThrow(
                        () -> new EntidadeNaoEncontradaException("A NOTA FISCAL com o ID: " + id + "nao foi encontrada!"));
    }

    public NotaFiscalItemDto update(Integer id, NotaFiscalItemDto notaFiscalItemDto) {
        ItemNotaFiscal itemNotaExistente = repository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));

        atualizarItemNota(itemNotaExistente, notaFiscalItemDto);

        ItemNotaFiscal itemNotaAtualizada = repository.save(itemNotaExistente);
        return converteParaDto(itemNotaAtualizada);
    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(item -> {
            repository.deleteById(id);
            LOG.info("O item da nota fiscal " + id + " Foi Deletado com sucesso");
            return item;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("O Item da nota fiscal " + id + " nao foi encontrado"));
    }

    public void addValorTotal(ItemNotaFiscal item) {
        NotaFiscal notaFiscal = item.getNotaFiscal();
        BigDecimal valorTotalItem = item.getValorTotal();
        notaFiscal.setValorTotal(valorTotalItem);

        notaFiscalService.salvar(NotaFiscalService.converteParaDto(notaFiscal));
    }

    public void calcularValorTotal(ItemNotaFiscal itemNotaFiscal) {
        BigDecimal quantidadeNotaFiscal = itemNotaFiscal.getQuantidade(); 
        BigDecimal precoUnitarioItemNotaFiscal = itemNotaFiscal.getPrecoUnitario();
        
        if (quantidadeNotaFiscal != null && precoUnitarioItemNotaFiscal != null) {
            BigDecimal valorTotal = quantidadeNotaFiscal.multiply(precoUnitarioItemNotaFiscal);
            
            itemNotaFiscal.setValorTotal(valorTotal);
        }
    }

   

    public void adicionarValorTotalCabecalho(ItemNotaFiscal itemNota) {
        NotaFiscal cabecalhoNota = itemNota.getNotaFiscal();
        BigDecimal valorTotalCabecalho = cabecalhoNota.getValorTotal();
        BigDecimal valorTotalItem = itemNota.getValorTotal();

        if (valorTotalCabecalho != null && valorTotalItem != null) {
            if (cabecalhoNota.getTipo() == NotaFiscalTipo.ENTRADA) {
                // Se for uma nota fiscal de entrada, atualiza o valor total do cabeçalho
                valorTotalCabecalho = valorTotalCabecalho.add(valorTotalItem);
                cabecalhoNota.setValorTotal(valorTotalCabecalho);
            }
        } else {
            System.err.println("Um dos valores é nulo. Não foi possível calcular o valor total do cabeçalho.");
        }
    }

    
    private void atualizarItemNota(ItemNotaFiscal itemNota, NotaFiscalItemDto notaFiscalItemDto) {
        NotaFiscal cabecalhoNota = notaFiscalItemDto.getNotaFiscal();
        itemNota.setNotaFiscal(cabecalhoNota);
        itemNota.setPrecoUnitario(notaFiscalItemDto.getPrecoUnitario());
        itemNota.setProduto(notaFiscalItemDto.getProduto());
        itemNota.setQuantidade(notaFiscalItemDto.getQuantidade());
        itemNota.setValorTotal(notaFiscalItemDto.getValorTotal());
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
        dto.setQuantidade(entidade.getQuantidade());
        dto.setValorTotal(entidade.getValorTotal());
        dto.setNotaFiscal(entidade.getNotaFiscal());
        dto.setProduto(entidade.getProduto());

        return dto;
    }

}
