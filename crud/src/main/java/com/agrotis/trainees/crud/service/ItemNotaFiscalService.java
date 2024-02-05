package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.helper.Validador;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;

@Service
public class ItemNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);
    private final ItemNotaFiscalRepository repository;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository) {
        super();
        this.repository = repository;
    }

    public ItemNotaFiscal salvar(ItemNotaFiscal itemNotaFiscal) {
        return repository.save(itemNotaFiscal);
    }

    public ItemNotaFiscal buscarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um registro");
            return null;
        });
    }

    public List<ItemNotaFiscal> buscarPorProduto(Produto produto) {
        return repository.findByProduto(produto);
    }

    public List<ItemNotaFiscal> buscarPorNotaFiscal(NotaFiscal notaFiscal) {
        return repository.findByNotaFiscal(notaFiscal);
    }

    public List<ItemNotaFiscal> buscarPorQuantidade(double quantidade) {
        return repository.findByQuantidade(quantidade);
    }

    public List<ItemNotaFiscal> buscarPorPreco(double preco) {
        return repository.findByPrecoUnitario(preco);
    }

    public List<ItemNotaFiscal> buscarPorValorTotal(double valor) {
        return repository.findByValorTotal(valor);
    }

    public List<ItemNotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public ItemNotaFiscal atualizar(ItemNotaFiscal item, int id) {
        ItemNotaFiscal itemAtualizar = buscarPorId(id);
        int idNotaFiscal = item.getNotaFiscal().getId();
        int idProduto = item.getProduto().getId();
        if (itemAtualizar != null) {
            if (item.getNotaFiscal() != null && Validador.validarNotaFiscal(idNotaFiscal)) {
                itemAtualizar.setNotaFiscal(item.getNotaFiscal());
            }
            if (item.getQuantidade() != 0 && item.getQuantidade() > 0) {
                itemAtualizar.setQuantidade(item.getQuantidade());
            }

            if (item.getProduto() != null && Validador.validarProduto(idProduto)) {
                itemAtualizar.setProduto(item.getProduto());
            }

            if (item.getPrecoUnitario() != 0 && item.getPrecoUnitario() > 0) {
                itemAtualizar.setPrecoUnitario(item.getPrecoUnitario());
            }

            itemAtualizar.calcularValorTotal(itemAtualizar.getPrecoUnitario(), itemAtualizar.getQuantidade());
            LOG.info("Item Atualizado");

            repository.save(itemAtualizar);

            return itemAtualizar;
        } else {
            LOG.error("O item que deseja atualiza, não existe.");
            return null;
        }

    }

}
