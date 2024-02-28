package com.agrotis.trainees.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.CabecalhoNotaDto;
import com.agrotis.trainees.crud.entity.ItemNota;

@Service
public interface CabecalhoNotaService {
    CabecalhoNotaDto salvar(CabecalhoNotaDto cabecalhoDto);
    CabecalhoNotaDto buscarPorId(Integer id);
    List<CabecalhoNotaDto> listarTodos();
    CabecalhoNotaDto atualizar(Integer id, CabecalhoNotaDto dto);
    void deletarPorId(Integer id);
    void adicionarValorTotalCabecalho(ItemNota itemNota);
    

}
