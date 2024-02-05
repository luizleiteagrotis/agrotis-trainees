package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;

@Service
public class ItemNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);

    private final ItemNotaFiscalRepository repository;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository) {
        super();
        this.repository = repository;
    }

    public ItemNotaFiscal salvar(ItemNotaFiscal entidade) {
        entidade.calcularValorTotal();
        return repository.save(entidade);
    }

    public ItemNotaFiscal buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Item não encontrado para id {}", id);
            return null;
        });
    }

    public List<ItemNotaFiscal> buscarPorProduto(Produto produto) {
        return repository.findAllByProduto(produto);
    }

    public List<ItemNotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

}
