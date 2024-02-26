package com.agrotis.trainees.crud.service;

import java.util.List;

import com.agrotis.trainees.crud.dtos.ItemNotaDto;

public interface ItemNotaService {
    ItemNotaDto salvar(ItemNotaDto dto);
    List<ItemNotaDto> listarTodos();
    ItemNotaDto atualizar(Integer id, ItemNotaDto notaFiscalItemDto);
    void deletarPorId(Integer id);
    ItemNotaDto buscarPorId(Integer id);

}
