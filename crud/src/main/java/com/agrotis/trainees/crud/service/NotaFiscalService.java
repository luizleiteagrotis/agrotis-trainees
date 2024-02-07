package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.helper.Validador;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);
    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository) {
        super();
        this.repository = notaFiscalRepository;
    }

    public NotaFiscal salvar(NotaFiscal notaFiscal) {

        if (Validador.existeParceiroPorId(notaFiscal.getParceiroNegocio().getId()) && existe(notaFiscal)
                        && existe(notaFiscal.getTipo())) {
            LOG.info("Salvo no banco");
            return repository.save(notaFiscal);

        } else {
            LOG.error("Não foi possivel salvar a nota fiscal");
            return null;
        }

    }

    public NotaFiscal buscarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar uma nota fiscal com este id {}", id);
            return null;
        });
    }

    public List<NotaFiscal> buscarPorParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        return repository.findByParceiroNegocio(parceiroNegocio);
    }

    public List<NotaFiscal> buscarPorTipoNotaFiscal(String tipo) {
        return repository.findByTipo(tipo);

    }

    public List<NotaFiscal> buscarPorNumero(int numero) {
        return repository.findByNumero(numero);
    }

    public List<NotaFiscal> buscarPorData(Date data) {
        return repository.findByData(data);
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public NotaFiscal atualizar(NotaFiscal notaFiscal, int id) {
        NotaFiscal nota = this.buscarPorId(id);
        Integer numeroNotaFiscal = notaFiscal.getNumero();
        if (nota != null) {
            if (notaFiscal.getTipo() != null) {
                nota.setTipo(notaFiscal.getTipo());
            }
            if (notaFiscal.getParceiroNegocio() != null) {
                nota.setParceiroNegocio(notaFiscal.getParceiroNegocio());
            }
            if (numeroNotaFiscal != null && numeroNotaFiscal != 0) {
                nota.setNumero(numeroNotaFiscal);
            }
            if (notaFiscal.getData() != null) {
                nota.setData(notaFiscal.getData());
            }
            LOG.info("Registro atualizado");
            if (existe(notaFiscal)) {
                return repository.save(nota);
            } else {
                LOG.error("Não foi possivel atualizar este registro");
                return null;
            }
        } else {
            LOG.error("Não foi possivel atualizar este registro");
            return null;
        }
    }

    public void deletarPorId(int id) {
        if (this.buscarPorId(id) != null) {
            repository.deleteById(id);
            LOG.info("Deletedado com sucesso");
        } else {
            LOG.error("Registro não encontrado");
        }

    }

    private boolean existe(NotaFiscal notaFiscal) {
        return repository.findByNumeroAndTipo(notaFiscal.getNumero(), notaFiscal.getTipo()).isEmpty();
    }

    private boolean existe(String tipoNota) {
        return tipoNota.equalsIgnoreCase("entrada") || tipoNota.equalsIgnoreCase("saida");

    }

    /*
     * busca o id da nota fiscal e todos os item vinculados a ele, fazendo o
     * calculo do valor total daquela nota e salvando caso tenha alteração
     */
    public void persistirValorTotal(int idNotaFiscal) {
        NotaFiscal notaPorId = buscarPorId(idNotaFiscal);
        List<ItemNotaFiscal> itens = notaPorId.getItemNotaFiscal();
        double valorTotal = 0;
        for (ItemNotaFiscal item : itens) {

            valorTotal += item.getValorTotal();
        }
        notaPorId.setValorTotal(valorTotal);
        repository.save(notaPorId);

    }

}
