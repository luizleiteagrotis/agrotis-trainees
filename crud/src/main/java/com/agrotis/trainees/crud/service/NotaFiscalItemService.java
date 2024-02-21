package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
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

    private final NotaFiscalItemRepository repository;
    private final NotaFiscalService notaFiscalService;
    private final ProdutoTipoService produtoService;

    @Autowired
    public NotaFiscalItemService(NotaFiscalItemRepository repository, NotaFiscalService notaFiscalService,
                    ProdutoTipoService produtoService) {
        this.repository = repository;
        this.notaFiscalService = notaFiscalService;
        this.produtoService = produtoService;
    }

    @Transactional
    public NotaFiscalItemDto salvar(@Valid NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = converterParaEntidade(dto);
        entidade.setValorTotal(calcularValorTotal(entidade));
        NotaFiscalItem savedItem = repository.save(entidade);
        adicionarOuAtualizarItemNotaFiscal(savedItem);
        LOG.info("Salvo item {}", savedItem.getId());
        return converterParaDto(savedItem);
    }

    public NotaFiscalItemDto buscarPorId(Integer id) {
        NotaFiscalItem entidade = repository.findById(id)
                        .orElseThrow(() -> new CrudException("Item da nota fiscal não encontrado"));
        return converterParaDto(entidade);
    }

    public void deletarPorId(Integer id) {
        NotaFiscalItem item = repository.findById(id).orElseThrow(() -> new CrudException("Item da nota fiscal não encontrado"));
        repository.deleteById(id);
        adicionarOuAtualizarItemNotaFiscal(item);
        LOG.info("Item da nota fiscal deletado com sucesso");
    }

    public List<NotaFiscalItemDto> listarTodos() {
        List<NotaFiscalItem> entidades = repository.findAll();
        return entidades.stream().map(this::converterParaDto).collect(Collectors.toList());
    }

    public List<NotaFiscalItemDto> buscarPorNotaFiscal(Integer notaFiscalId) {
        List<NotaFiscalItem> itens = repository.findByNotaFiscalId(notaFiscalId);
        return itens.stream().map(this::converterParaDto).collect(Collectors.toList());
    }

    private void adicionarOuAtualizarItemNotaFiscal(NotaFiscalItem item) {
        if (item != null && item.getNotaFiscal() != null && item.getProduto() != null) {
            double valorTotalItem = calcularValorTotal(item);
            NotaFiscal notaFiscal = item.getNotaFiscal();
            atualizarValorTotalNotaFiscal(notaFiscal, valorTotalItem);
            controlarEstoque(item);
        } else {
            throw new CrudException("O item da nota fiscal, nota fiscal e produto devem ser fornecidos");
        }
    }

    private void atualizarValorTotalNotaFiscal(NotaFiscal notaFiscal, Double valorTotalItem) {
        double novoValorTotal = notaFiscal.getValorTotal() + valorTotalItem;
        notaFiscal.setValorTotal(novoValorTotal);
        notaFiscalService.salvar(notaFiscal);
    }

    private double calcularValorTotal(NotaFiscalItem item) {
        return item.getQuantidade() * item.getPrecoUnitario();
    }

    private void controlarEstoque(NotaFiscalItem item) {
        ProdutoDto produtoDto = produtoService.converterParaDto(item.getProduto());
        produtoDto.setId(item.getProduto().getId());

        int quantidade = item.getQuantidade();

        if (item.getNotaFiscal().getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
            if (produtoDto.getEstoque() < quantidade || (produtoDto.getEstoque() - quantidade) < 0) {
                throw new CrudException("Estoque insuficiente para o produto: " + produtoDto.getDescricao());
            }
            produtoDto.setEstoque(produtoDto.getEstoque() - quantidade);
        } else {
            produtoDto.setEstoque(produtoDto.getEstoque() + quantidade);
        }

        produtoService.salvar(produtoDto);
    }

    public static NotaFiscalItemDto converterParaDto(NotaFiscalItem entidade) {
        return new NotaFiscalItemDto(entidade);
    }

    public static NotaFiscalItem converterParaEntidade(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(dto.getId());
        entidade.setNotaFiscal(dto.getNotaFiscal());
        entidade.setProduto(dto.getProduto());
        entidade.setQuantidade(dto.getQuantidade());
        entidade.setPrecoUnitario(dto.getPrecoUnitario());
        entidade.setValorTotal(dto.getValorTotal());

        return entidade;
    }

    public Object atualizar(@Valid NotaFiscalItemDto item) {
        // TODO Auto-generated method stub
        return null;
    }
}
