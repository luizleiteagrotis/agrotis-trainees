package com.agrotis.trainees.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.ItemNotaDto;

@Service
public interface ItemNotaService {
    ItemNotaDto salvar(ItemNotaDto dto);
    List<ItemNotaDto> listarTodos();
    ItemNotaDto atualizar(Integer id, ItemNotaDto notaFiscalItemDto);
    void deletarPorId(Integer id);
    ItemNotaDto buscarPorId(Integer id);

}
