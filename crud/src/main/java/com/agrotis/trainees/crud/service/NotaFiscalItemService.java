package com.agrotis.trainees.crud.service;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalItemService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItemService.class);

    private final NotaFiscalRepository notaFiscalRepository;
    private final NotaFiscalItemRepository notaFiscalItemRepository;

    public NotaFiscalItemService(NotaFiscalRepository notaFiscalRepository, NotaFiscalItemRepository notaFiscalItemRepository) {
        this.notaFiscalRepository = notaFiscalRepository;
        this.notaFiscalItemRepository = notaFiscalItemRepository;
    }

    @Transactional
    public NotaFiscalItemDto salvar(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = converterParaEntidade(dto);
        entidade.setValorTotal(calcularValorTotal(entidade));
        NotaFiscalItem savedItem = notaFiscalItemRepository.save(entidade);
        adicionarOuAtualizarItemNotaFiscal(savedItem);
        LOG.info("Salvo item {}", savedItem.getId());
        return converterParaDto(savedItem);
    }

    public void adicionarOuAtualizarItemNotaFiscal(NotaFiscalItem item) {
        if (item != null && item.getNotaFiscal() != null && item.getProduto() != null) {
            double valorTotalItem = calcularValorTotal(item);
            NotaFiscal notaFiscal = item.getNotaFiscal();
            atualizarValorTotal(notaFiscal, valorTotalItem);
            controlarEstoque(item);
        } else {
            throw new CrudException("O item da nota fiscal, nota fiscal e produto devem ser fornecidos");
        }
    }

    public NotaFiscalItemDto buscarPorId(Integer id) throws NotFoundException {
        NotaFiscalItem entidade = notaFiscalItemRepository.findById(id).orElseThrow(() -> new NotFoundException());
        Hibernate.initialize(entidade.getNotaFiscal());
        return converterParaDto(entidade);
    }

    public NotaFiscal buscarPorNotaFiscalTipo(NotaFiscalTipo tipoNota) {
        return notaFiscalRepository.findByNotaFiscalTipo(tipoNota).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o tipo de {}.", tipoNota);
            return null;
        });
    }

    public NotaFiscal buscarPorParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        return notaFiscalRepository.findByParceiroNegocio(parceiroNegocio).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o parceiro {}.", parceiroNegocio);
            return null;
        });
    }

    public NotaFiscal buscarPorNumero(Integer numero) {
        return notaFiscalRepository.findByNumero(numero).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para o número {}.", numero);
            return null;
        });
    }

    public NotaFiscal buscarPorData(LocalDate dataEmissao) {
        return notaFiscalRepository.findByDataEmissao(dataEmissao).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para a data de {}.", dataEmissao);
            return null;
        });
    }

    public List<NotaFiscalItemDto> listarTodos() {
        List<NotaFiscalItem> entidades = notaFiscalItemRepository.findAll();
        return entidades.stream().map(this::converterParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        notaFiscalItemRepository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

    @Transactional
    public void atualizarValorTotal(Integer id, Double novoValorTotal) throws NotFoundException {
        NotaFiscalItem notaFiscalItem = notaFiscalItemRepository.findById(id).orElseThrow(() -> new NotFoundException());

        double valorItem = notaFiscalItem.getPrecoUnitario();
        NotaFiscal notaFiscal = notaFiscalItem.getNotaFiscal();

        if (notaFiscal.getNotaFiscalTipo() == NotaFiscalTipo.ENTRADA) {
            novoValorTotal += valorItem;
        } else if (notaFiscal.getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
            novoValorTotal -= valorItem;
        }

        notaFiscalItem.setValorTotal(novoValorTotal);
        notaFiscalItemRepository.save(notaFiscalItem);
    }

    @Transactional
    public NotaFiscal inserir(@Valid NotaFiscalDto dto) throws NotFoundException {
        NotaFiscal entidade = converterParaEntidade(dto);
        double novoValorTotal = calcularValorTotal(dto.getItensNota());
        entidade.setValorTotal(novoValorTotal);

        return notaFiscalRepository.save(entidade);
    }

    @Transactional
    public NotaFiscalDto atualizar(NotaFiscalDto dto) throws NotFoundException {
        NotaFiscal entidade = converterParaEntidade(dto);
        double novoValorTotal = calcularValorTotal(dto.getItensNota());
        entidade.setValorTotal(novoValorTotal);

        return converterParaDto(notaFiscalRepository.save(entidade));
    }

    private double calcularValorTotal(NotaFiscalItem entidade) {
        return entidade.getQuantidade() * entidade.getPrecoUnitario();
    }

    public NotaFiscalItemDto converterParaDto(NotaFiscalItem entidade) {
        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(entidade.getId());
        dto.setQuantidade(entidade.getQuantidade());
        dto.setPrecoUnitario(entidade.getPrecoUnitario());
        dto.setProduto(entidade.getProduto());
        dto.setValorTotal(entidade.getValorTotal());

        return dto;
    }

    public NotaFiscalItem converterParaEntidade(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(dto.getId());
        entidade.setQuantidade(dto.getQuantidade());
        entidade.setPreco_unitario(dto.getPrecoUnitario());
        entidade.setProduto(dto.getProduto());
        entidade.setNotaFiscal(dto.getNotaFiscal());
        entidade.setValorTotal(dto.getValorTotal());

        return entidade;
    }

    private void controlarEstoque(NotaFiscalItem item) {
        ProdutoDto produtoDto = ProdutoTipoService.converterParaDto(item.getProduto());
        produtoDto.setId(item.getProduto().getId());

        int quantidade = item.getQuantidade();

        if (item.getNotaFiscal().getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
            if (produtoDto.getQuantidadeEstoque() < quantidade || (produtoDto.getQuantidadeEstoque() - quantidade) < 0) {
                throw new CrudException("Estoque insuficiente para o produto: " + produtoDto.getDescricao());
            }
            produtoDto.setQuantidadeEstoque(produtoDto.getQuantidadeEstoque() - quantidade);
        } else {
            produtoDto.setQuantidadeEstoque(produtoDto.getQuantidadeEstoque() + quantidade);
        }

        produtoTipoService.salvar(produtoDto);
    }
}
