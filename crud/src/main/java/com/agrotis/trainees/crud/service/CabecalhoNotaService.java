package com.agrotis.trainees.crud.service;

import java.util.List;

import com.agrotis.trainees.crud.dtos.CabecalhoNotaDto;

public interface CabecalhoNotaService {
    CabecalhoNotaDto salvar(CabecalhoNotaDto cabecalhoDto);
    CabecalhoNotaDto buscarPorId(Integer id);
    List<CabecalhoNotaDto> listarTodos();
    CabecalhoNotaDto atualizar(Integer id, CabecalhoNotaDto dto);
    void deletarPorId(Integer id);
    

}
