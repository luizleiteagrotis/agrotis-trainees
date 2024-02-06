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

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);
    private final ItemNotaFiscalRepository repository;
    private final NotaFiscalService notaFiscalService;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository, NotaFiscalService notaFiscalService) {
        super();
        this.repository = repository;
        this.notaFiscalService = notaFiscalService;

    }

    public ItemNotaFiscal salvar(ItemNotaFiscal itemNotaFiscal) {
        if (repository.findByNotaFiscalAndProduto(itemNotaFiscal.getNotaFiscal(), itemNotaFiscal.getProduto()).isEmpty()) {
            ItemNotaFiscal itemNota = repository.save(itemNotaFiscal);
            // verifica tabela da nota fiscal
            notaFiscalService.verificarValorTotal(itemNotaFiscal.getNotaFiscal().getId());
            LOG.info("Item cadastrado com sucesso");
            return itemNota;
        } else {
            LOG.error("Não foi possivel cadastrar o item na nota fiscal");
            return null;
        }

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
        if (itemAtualizar != null) {
            if (item.getNotaFiscal() != null && Validador.validarNotaFiscal(item.getNotaFiscal().getId())) {
                itemAtualizar.setNotaFiscal(item.getNotaFiscal());
            }
            if (item.getQuantidade() != 0 && item.getQuantidade() > 0) {
                itemAtualizar.setQuantidade(item.getQuantidade());
            }

            if (item.getProduto() != null && Validador.validarProduto(item.getProduto().getId())) {
                itemAtualizar.setProduto(item.getProduto());
            }

            if (item.getPrecoUnitario() != 0 && item.getPrecoUnitario() > 0) {
                itemAtualizar.setPrecoUnitario(item.getPrecoUnitario());
            }

            itemAtualizar.setValorTotal();
            LOG.info("Item Atualizado");

            repository.save(itemAtualizar);

            notaFiscalService.verificarValorTotal(itemAtualizar.getNotaFiscal().getId());
            return itemAtualizar;
        } else {
            LOG.error("O item que deseja atualiza, não existe.");
            return null;
        }

    }

    public void deletarPorId(int id) {

        if (buscarPorId(id) != null) {
            ItemNotaFiscal itemNotaFiscal = buscarPorId(id);
            repository.deleteById(id);
            notaFiscalService.verificarValorTotal(itemNotaFiscal.getNotaFiscal().getId());
            LOG.info("Deletado com sucesso");
        } else {
            LOG.info("Registro não encontrado");
        }

    }

    public static double calcularValorTotal(double quantidade, double precoUnitario) {
        return quantidade * precoUnitario;

    }

}
