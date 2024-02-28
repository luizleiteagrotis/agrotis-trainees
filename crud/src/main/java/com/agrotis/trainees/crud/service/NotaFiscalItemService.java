package com.agrotis.trainees.crud.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public NotaFiscalItemDto inserir(NotaFiscalItemDto dto) throws DescricaoExisteException {
        NotaFiscalItem entidade = notaFiscalItemWrapper.converterParaEntidade(dto);
        try {
            entidade = estoqueService.alterarEstoque(entidade);

        } catch (EstoqueZeradoException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        } catch (ValorDiferenteException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
        entidade = repository.save(entidade);
        return notaFiscalItemWrapper.converterParaDto(entidade);
    }

    public NotaFiscalItemDto atualizar(NotaFiscalItemDto dto) throws DescricaoExisteException {
        NotaFiscalItem entidade = notaFiscalItemWrapper.converterParaEntidade(dto);

        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do produto.");
        }
        NotaFiscalItem item = buscarPorId(entidade.getId());
        entidade = itemService.tratarNulos(entidade, item);
        NotaFiscal nota = notaFiscalService.buscarPorId(entidade.getIdNota().getId());
        Produto produto = produtoService.buscarPorId(item.getProduto().getId());

        try {
            estoqueService.atualizarEstoque(entidade, item);
        } catch (EstoqueZeradoException e) {
            System.out.println("Erro: " + e.getMessage());
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
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Informações não encontradas para o id {}", id);
            return null;
        });
    }

    public NotaFiscalItem buscarPorProduto(Produto produto) {
        return repository.findByProduto(produto).orElseGet(() -> {
            LOG.error("Informações não encontradas para o produto {}", produto);
            return null;
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
