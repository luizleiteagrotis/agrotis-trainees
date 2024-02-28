package com.agrotis.trainees.crud.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.exception.DescricaoExisteException;
import com.agrotis.trainees.crud.exception.EstoqueZeradoException;
import com.agrotis.trainees.crud.exception.ValorDiferenteException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.wrapper.NotaFiscalItemWrapper;
import com.agrotis.trainees.crud.wrapper.NotaFiscalWrapper;

@Service
public class NotaFiscalItemService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItem.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;
    private final NotaFiscalItemWrapper notaFiscalItemWrapper;
    private final NotaFiscalWrapper notaFiscalWrapper;
    private final EstoqueService estoqueService;
    private final ItemService itemService;

    public NotaFiscalItemService(NotaFiscalItemRepository repository, ProdutoService produtoService,
                    NotaFiscalService notaFiscalService, NotaFiscalItemWrapper notaFiscalItemWrapper,
                    NotaFiscalWrapper notaFiscalWrapper, ItemService itemService, EstoqueService estoqueService) {
        super();
        this.repository = repository;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
        this.notaFiscalItemWrapper = notaFiscalItemWrapper;
        this.notaFiscalWrapper = notaFiscalWrapper;
        this.estoqueService = estoqueService;
        this.itemService = itemService;

    }

    public NotaFiscalItemDto inserir(NotaFiscalItemDto dto)
                    throws DescricaoExisteException, EstoqueZeradoException, ValorDiferenteException {
        NotaFiscalItem entidade = notaFiscalItemWrapper.converterParaEntidade(dto);
        try {
            entidade = estoqueService.alterarEstoque(entidade);

        } catch (EstoqueZeradoException e) {
            System.out.println("Erro: " + e.getMessage());
            throw e;
        } catch (ValorDiferenteException e) {
            System.out.println("Erro: " + e.getMessage());
            throw e;
        }
        entidade = repository.save(entidade);
        return notaFiscalItemWrapper.converterParaDto(entidade);
    }

    public NotaFiscalItemDto atualizar(NotaFiscalItemDto dto) throws DescricaoExisteException, EstoqueZeradoException {
        NotaFiscalItem entidade = notaFiscalItemWrapper.converterParaEntidade(dto);

        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do item.");
        }
        NotaFiscalItem item = buscarPorId(entidade.getId());
        entidade = itemService.tratarNulos(entidade, item);
        NotaFiscal nota = notaFiscalService.buscarPorId(entidade.getIdNota().getId());
        Produto produto = produtoService.buscarPorId(item.getProduto().getId());

        try {
            estoqueService.atualizarEstoque(entidade, item);
        } catch (EstoqueZeradoException e) {
            System.out.println("Erro: " + e.getMessage());
            throw e;
        }

        nota.setValorTotal(nota.getValorTotal().subtract(item.getValorTotal()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.map(entidade, item);
        item = itemService.obterValorTotal(item);
        nota.setValorTotal(nota.getValorTotal().add(item.getValorTotal()));

        notaFiscalService.atualizar(notaFiscalWrapper.converterParaDto(nota));
        return notaFiscalItemWrapper.converterParaDto(repository.save(item));
    }

    public NotaFiscalItem buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            LOG.error("Informações não encontradas para o id {}", id);
            throw new NoSuchElementException("Informações não encontradas para o id " + id);
        });
    }

    public NotaFiscalItem buscarPorProduto(Produto produto) {
        return repository.findByProduto(produto).orElseThrow(() -> {
            LOG.error("Informações não encontradas para o produto {}", produto);
            throw new NoSuchElementException("Informações não encontradas para o produto " + produto.getDescricao());
        });
    }

    public List<NotaFiscalItem> listarPorNota(Integer idNota) {
        NotaFiscal notaFiscal = notaFiscalService.buscarPorId(idNota);
        return repository.findByNota(notaFiscal);
    }

    public List<NotaFiscalItem> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) throws DescricaoExisteException, EstoqueZeradoException {
        estoqueService.deletarEstoque(id);
        LOG.info("id: {} deletado com sucesso", id);
    }

}
