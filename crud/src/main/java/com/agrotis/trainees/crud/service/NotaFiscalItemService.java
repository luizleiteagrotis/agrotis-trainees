package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItemService.class);

    private NotaFiscalItemRepository repository;
    private NotaFiscalItemConversaoService conversao;
    private CalcularValorTotalItemService calcularValorTotalItem;
    private AdicionarOuAtualizarItemNotaFiscalService atualizarNota;

    public NotaFiscalItemService(NotaFiscalItemRepository repository, NotaFiscalItemConversaoService conversao,
                    CalcularValorTotalItemService calcularValorTotalItem, AdicionarOuAtualizarItemNotaFiscalService atualizarNota) {
        super();
        this.repository = repository;
        this.conversao = conversao;
        this.calcularValorTotalItem = calcularValorTotalItem;
        this.atualizarNota = atualizarNota;
    }

    @Autowired
    public NotaFiscalItemDto salvar(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = conversao.converterParaEntidade(dto);
        entidade.setValorTotal(calcularValorTotalItem.calcularValorTotalItem(entidade));
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
        atualizarNota.atualizarItem(item);
        LOG.info("Item da nota fiscal deletado com sucesso");
    }

    public List<NotaFiscalItemDto> listarTodos() {
        List<NotaFiscalItem> entidades = repository.findAll();
        return entidades.stream().map(entidade -> conversao.converterParaDto(entidade)).collect(Collectors.toList());
    }

    public NotaFiscalItem inserir(@Valid NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = conversao.converterParaEntidade(dto);
        NotaFiscalItem itemSalvo = repository.save(entidade);
        atualizarNota.atualizarItem(itemSalvo);
        return itemSalvo;
    }

    public NotaFiscalItemDto atualizar(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = conversao.converterParaEntidade(dto);
        entidade.setValorTotal(calcularValorTotalItem.calcularValorTotalItem(entidade));
        NotaFiscalItem itemSalvo = repository.save(entidade);
        atualizarNota.atualizarItem(itemSalvo);
        return conversao.converterParaDto(itemSalvo);
    }

}