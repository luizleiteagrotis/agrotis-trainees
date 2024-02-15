package com.agrotis.trainees.crud.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.exception.EstoqueZeradoException;
import com.agrotis.trainees.crud.exception.ValorDiferenteException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItem.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;

    public NotaFiscalItemService(NotaFiscalItemRepository repository, ProdutoService produtoService,
                    NotaFiscalService notaFiscalService) {
        super();
        this.repository = repository;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;

    }

    public NotaFiscalItemDto salvar(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = converterParaEntidade(dto);
        try {
            alterarEstoque(entidade);

        } catch (EstoqueZeradoException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        } catch (ValorDiferenteException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
        entidade = repository.save(entidade);
        return converterParaDto(entidade);
    }

    public NotaFiscalItemDto atualizar(NotaFiscalItem entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do produto.");
        }

        NotaFiscalItem item = buscarPorId(entidade.getId());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.map(entidade, item);

        return converterParaDto(repository.save(item));
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

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("id: {} deletado com sucesso", id);
    }

    public List<NotaFiscalItem> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public NotaFiscalItem obterValorTotal(NotaFiscalItem entidade, NotaFiscalItem entidadeNova) {
        NotaFiscalItem buscarPorProdutoAndId = repository.findByProdutoAndIdNota(entidade.getProduto(), entidade.getIdNota());

        entidade.setValorTotal(entidade.getPrecoUnitario() * entidade.getQuantidade());
        notaFiscalService.atualizarValorTotalNota(entidadeNova);
        return entidade;
    }

    public NotaFiscalItem alterarEstoque(NotaFiscalItem item) throws EstoqueZeradoException, ValorDiferenteException {
        NotaFiscal nota = item.getIdNota();
        Produto produto = produtoService.buscarPorId(item.getProduto().getId());

        if (nota.getTipo().getId() == 1) {
            produto.setEstoque(produto.getEstoque() + item.getQuantidade());
            validarNotaEItem(item);

            ProdutoDto produto2 = produtoService.salvar(produtoService.converterParaDto(produto));
        } else {
            if (produto.getEstoque() - item.getQuantidade() < 0) {
                throw new EstoqueZeradoException("A quantidade em estoque não é suficiente");
            }
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            validarNotaEItem(item);
            ProdutoDto produto2 = produtoService.salvar(produtoService.converterParaDto(produto));
        }
        return item;
    }

    public NotaFiscalItem validarNotaEItem(NotaFiscalItem entidade) throws ValorDiferenteException {
        NotaFiscalItem buscarPorProdutoAndId = repository.findByProdutoAndIdNota(entidade.getProduto(), entidade.getIdNota());

        if (buscarPorProdutoAndId != null) {
            if (buscarPorProdutoAndId.getPrecoUnitario() == entidade.getPrecoUnitario()) {
                buscarPorProdutoAndId.setQuantidade(buscarPorProdutoAndId.getQuantidade() + entidade.getQuantidade());
                obterValorTotal(buscarPorProdutoAndId, entidade);
                return repository.save(buscarPorProdutoAndId);
            }
            throw new ValorDiferenteException("Item com preço diferente do original");
        } else {
            obterValorTotal(entidade, entidade);
            return repository.save(entidade);
        }

    }

    private NotaFiscalItemDto converterParaDto(NotaFiscalItem entidade) {
        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(entidade.getId());
        dto.setProduto(entidade.getProduto());
        dto.setQuantidade(entidade.getQuantidade());
        dto.setPrecoUnitario(entidade.getPrecoUnitario());
        dto.setValorTotal(entidade.getValorTotal());
        dto.setIdNota(entidade.getIdNota());

        return dto;
    }

    private NotaFiscalItem converterParaEntidade(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(dto.getId());
        entidade.setProduto(dto.getProduto());
        entidade.setQuantidade(dto.getQuantidade());
        entidade.setPrecoUnitario(dto.getPrecoUnitario());
        entidade.setValorTotal(dto.getValorTotal());
        entidade.setIdNota(dto.getIdNota());

        return entidade;
    }
}
