package com.agrotis.trainees.crud.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
        Produto produto = produtoService.buscarPorId(entidade.getProduto().getId());
        NotaFiscal nota = notaFiscalService.buscarPorId(entidade.getIdNota().getId());
        boolean existe = repository.existsByProdutoAndIdNota(produto, nota);

        try {
            entidade = alterarEstoque(entidade);

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
        entidade = tratarNulos(entidade, item);
        NotaFiscal nota = notaFiscalService.buscarPorId(entidade.getIdNota().getId());
        Produto produto = produtoService.buscarPorId(item.getProduto().getId());

        try {
            atualizarEstoque(entidade, item);
        } catch (EstoqueZeradoException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
        nota.setValorTotal(nota.getValorTotal() - item.getValorTotal());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.map(entidade, item);
        item = obterValorTotal(item);
        nota.setValorTotal(nota.getValorTotal() + item.getValorTotal());

        notaFiscalService.salvar(notaFiscalService.converterParaDto(nota));
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

    public List<NotaFiscalItem> listarPorNota(Integer idNota) {
        NotaFiscal notaFiscal = notaFiscalService.buscarPorId(idNota);
        return repository.findByNota(notaFiscal);
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("id: {} deletado com sucesso", id);
    }

    public List<NotaFiscalItem> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public NotaFiscalItem obterValorTotal(NotaFiscalItem entidade) {
        entidade.setValorTotal(entidade.getPrecoUnitario() * entidade.getQuantidade());
        return entidade;
    }

    public NotaFiscalItem alterarEstoque(NotaFiscalItem item) throws EstoqueZeradoException, ValorDiferenteException {
        NotaFiscal nota = notaFiscalService.buscarPorId(item.getIdNota().getId());
        Produto produto = produtoService.buscarPorId(item.getProduto().getId());

        if (nota.getTipo().getId() == 1) {
            produto.setEstoque(produto.getEstoque() + item.getQuantidade());
            item = validarNotaEItem(item);

            ProdutoDto produto2 = produtoService.atualizar(produto);
        } else {
            if (produto.getEstoque() - item.getQuantidade() < 0) {
                throw new EstoqueZeradoException("A quantidade em estoque não é suficiente");
            }
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            item = validarNotaEItem(item);
            ProdutoDto produto2 = produtoService.atualizar(produto);
        }
        return item;
    }

    public NotaFiscalItem validarNotaEItem(NotaFiscalItem entidade) throws ValorDiferenteException {
        NotaFiscal nota = notaFiscalService.buscarPorId(entidade.getIdNota().getId());
        Produto produto = produtoService.buscarPorId(entidade.getProduto().getId());
        NotaFiscalItem buscarPorProdutoAndId = repository.findByProdutoAndIdNota(produto, nota);

        if (buscarPorProdutoAndId != null) {
            if (buscarPorProdutoAndId.getPrecoUnitario() == entidade.getPrecoUnitario()) {
                buscarPorProdutoAndId.setQuantidade(buscarPorProdutoAndId.getQuantidade() + entidade.getQuantidade());
                obterValorTotal(buscarPorProdutoAndId);
                notaFiscalService.atualizarValorTotalNota(entidade);
                return buscarPorProdutoAndId;
            }
            throw new ValorDiferenteException("Item com preço diferente do original");
        } else {
            obterValorTotal(entidade);
            notaFiscalService.atualizarValorTotalNota(entidade);
            return entidade;
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

    private NotaFiscalItem tratarNulos(NotaFiscalItem entidade, NotaFiscalItem item) {

        Field[] fields = entidade.getClass().getDeclaredFields();
        Field[] fields2 = item.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                if (field.get(entidade) == null || field.get(entidade).equals(0) || field.get(entidade).equals(0.0)) {

                    field.set(entidade, field.get(item));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return entidade;
    }

    private NotaFiscalItem atualizarEstoque(NotaFiscalItem entidade, NotaFiscalItem item) throws EstoqueZeradoException {
        Produto produto = produtoService.buscarPorId(entidade.getProduto().getId());
        Produto produto2 = produtoService.buscarPorId(item.getProduto().getId());
        NotaFiscal nota = notaFiscalService.buscarPorId(entidade.getIdNota().getId());

        if (nota.getTipo().getId() == 1) {
            if (produto.getId() != produto2.getId()) {
                if (produto2.getEstoque() - item.getQuantidade() >= 0) {
                    produto.setEstoque(produto.getEstoque() + entidade.getQuantidade());
                    produto2.setEstoque(produto2.getEstoque() - item.getQuantidade());
                    produtoService.atualizar(produto);
                    produtoService.atualizar(produto2);
                } else {
                    throw new EstoqueZeradoException("Valor em estoque do produto indisponível");
                }

            } else if (produto2.getEstoque() + entidade.getQuantidade() - item.getQuantidade() >= 0) {
                produto.setEstoque(produto.getEstoque() + entidade.getQuantidade() - item.getQuantidade());
                produtoService.atualizar(produto);
            } else {
                throw new EstoqueZeradoException("Valor em estoque do produto indisponível");
            }

        } else {
            if (produto.getId() != produto2.getId()) {
                if (produto.getEstoque() - entidade.getQuantidade() >= 0) {
                    produto2.setEstoque(produto2.getEstoque() + item.getQuantidade());
                    produto.setEstoque(produto.getEstoque() - entidade.getQuantidade());
                    produtoService.atualizar(produto);
                    produtoService.atualizar(produto2);
                } else {
                    throw new EstoqueZeradoException("Valor em estoque do produto indisponível");
                }

            } else if (produto.getEstoque() + item.getQuantidade() - entidade.getQuantidade() >= 0) {
                produto.setEstoque(produto.getEstoque() + item.getQuantidade() - entidade.getQuantidade());
                produtoService.atualizar(produto);
            } else {
                throw new EstoqueZeradoException("Valor em estoque do produto indisponível");
            }
        }
        return entidade;
    }

}
