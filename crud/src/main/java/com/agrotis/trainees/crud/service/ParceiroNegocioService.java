package com.agrotis.trainees.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;

@Service
public interface ParceiroNegocioService {
    
    ParceiroNegocioDto salvar( ParceiroNegocioDto negocio);
    ParceiroNegocioDto buscarPorId(Integer id);
    List<ParceiroNegocioDto> listarTodos();
    ParceiroNegocioDto buscarPorNome(String nome);
    void deletarPorId(Integer id);
    ParceiroNegocioDto atualizar(Integer id, ParceiroNegocioDto dto);
    

}
