package com.agrotis.trainees.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;

@Service
public interface ProdutoService {
    
    ProdutoDto salvar(ProdutoDto produtoDto);
    ProdutoDto buscaPeloId(Integer id);
    List<ProdutoDto> buscarTodos();
    void deletarPorId(Integer id);
    ProdutoDto atualizar(Integer id, ProdutoDto produtoDto);
    Produto salvarOuBuscarProduto(Produto produto);
    void atualizarEstoque(ItemNota itemNota);

}
