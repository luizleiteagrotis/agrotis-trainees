package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItemService.class);

    private NotaFiscalItemRepository repository;
    private NotaFiscalItemConversaoService conversao;
    private ProdutoConversaoService conversaoProduto;
    private ProdutoService produtoService;
    private NotaFiscalService notaFiscalService;

    public NotaFiscalItemService(NotaFiscalItemRepository repository, NotaFiscalItemConversaoService conversao,
                    ProdutoConversaoService conversaoProduto) {
        super();
        this.repository = repository;
        this.conversao = conversao;
        this.conversaoProduto = conversaoProduto;
    }

    @Autowired
    public NotaFiscalItemDto salvar(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = conversao.converterParaEntidade(dto);
        entidade.setValorTotal(calcularValorTotalItem(entidade));
        NotaFiscalItem itemSalvo = repository.save(entidade);
        LOG.info("Salvo item {}", itemSalvo.getId());
        return conversao.converterParaDto(itemSalvo);
    }

    public NotaFiscalItemDto buscarPorId(Integer id) throws NotFoundException {
        NotaFiscalItem entidade = repository.findById(id).orElseThrow(() -> new NotFoundException());
        return conversao.converterParaDto(entidade);
    }

    public void deletarPorId(Integer id) throws NotFoundException {
        NotaFiscalItem item = repository.findById(id).orElseThrow(() -> new NotFoundException());
        repository.deleteById(id);
        adicionarOuAtualizarItemNotaFiscal(item);
        LOG.info("Item da nota fiscal deletado com sucesso");
    }

    public List<NotaFiscalItemDto> listarTodos() {
        List<NotaFiscalItem> entidades = repository.findAll();
        return entidades.stream().map(entidade -> conversao.converterParaDto(entidade)).collect(Collectors.toList());
    }

    public NotaFiscalItem inserir(@Valid NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = conversao.converterParaEntidade(dto);
        NotaFiscalItem itemSalvo = repository.save(entidade);
        adicionarOuAtualizarItemNotaFiscal(itemSalvo);
        return itemSalvo;
    }

    public NotaFiscalItemDto atualizar(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = conversao.converterParaEntidade(dto);
        entidade.setValorTotal(calcularValorTotalItem(entidade));
        NotaFiscalItem itemSalvo = repository.save(entidade);
        adicionarOuAtualizarItemNotaFiscal(itemSalvo);
        return conversao.converterParaDto(itemSalvo);
    }

    public void adicionarOuAtualizarItemNotaFiscal(NotaFiscalItem item) {
        if (item != null && item.getNotaFiscal() != null && item.getProduto() != null) {
            BigDecimal valorTotalItem = calcularValorTotalItem(item);
            NotaFiscal notaFiscal = item.getNotaFiscal();
            if (notaFiscal != null) {
                atualizarValorTotalNotaFiscal(notaFiscal, valorTotalItem);
                controlarEstoque(item);
            } else {
                throw new IllegalArgumentException("Nota fiscal não pode ser nula");
            }
        } else {
            throw new IllegalArgumentException("O item da nota fiscal, nota fiscal e produto devem ser fornecidos");
        }
    }

    private void atualizarValorTotalNotaFiscal(NotaFiscal notaFiscal, BigDecimal valorTotalItem) {
        if (notaFiscal == null) {
            throw new IllegalArgumentException("Nota fiscal não pode ser nula");
        } else {
            BigDecimal valorTotalAtual = notaFiscal.getValorTotal() != null ? notaFiscal.getValorTotal() : BigDecimal.ZERO;
            BigDecimal novoValorTotal = valorTotalAtual.add(valorTotalItem);
            notaFiscal.setValorTotal(novoValorTotal);
            notaFiscalService.salvar(notaFiscal);
        }
    }

    private BigDecimal calcularValorTotalItem(NotaFiscalItem item) {
        BigDecimal quantidade = BigDecimal.valueOf(item.getQuantidade());
        BigDecimal precoUnitario = item.getPrecoUnitario();
        return quantidade.multiply(precoUnitario);
    }

    private void controlarEstoque(NotaFiscalItem item) {
        ProdutoDto produtoDto = conversaoProduto.converterParaDto(item.getProduto());
        produtoDto.setId(item.getProduto().getId());

        int quantidade = item.getQuantidade();

        if (item.getNotaFiscal().getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
            if (produtoDto.getEstoque() < quantidade || (produtoDto.getEstoque() - quantidade) < 0) {
                throw new CrudException("Estoque insuficiente para o produto: " + produtoDto.getNome());
            }
            produtoDto.setEstoque(produtoDto.getEstoque() - quantidade);
        } else {
            produtoDto.setEstoque(produtoDto.getEstoque() + quantidade);
        }

        produtoService.salvar(produtoDto);
    }

}