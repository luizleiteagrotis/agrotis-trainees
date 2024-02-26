package com.agrotis.trainees.crud.service;

import java.util.List;

import com.agrotis.trainees.crud.dtos.ProdutoDto;

public interface ProdutoService {
    
    ProdutoDto salvar(ProdutoDto produtoDto);
    ProdutoDto buscaPeloId(Integer id);
    List<ProdutoDto> buscarTodos();
    void deletarPorId(Integer id);
    ProdutoDto atualizar(Integer id, ProdutoDto produtoDto);

}
