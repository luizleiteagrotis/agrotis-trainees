package com.agrotis.trainees.crud.service;

import java.util.List;

import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;

public interface ParceiroNegocioService {
    
    ParceiroNegocioDto salvar( ParceiroNegocioDto negocio);
    ParceiroNegocioDto buscarPorId(Integer id);
    List<ParceiroNegocioDto> listarTodos();
    ParceiroNegocioDto buscarPorNome(String nome);
    void deletarPorId(Integer id);
    ParceiroNegocioDto atualizar(Integer id, ParceiroNegocioDto dto);
    

}
