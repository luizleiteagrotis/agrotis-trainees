package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.NotaFiscalExcecao;
import com.agrotis.trainees.crud.helper.TipoNotaFiscal;
import com.agrotis.trainees.crud.helper.Validador;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);
    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository) {
        super();
        this.repository = notaFiscalRepository;
    }

    public NotaFiscal salvar(NotaFiscal notaFiscal) {
        try {
            validar(notaFiscal);
            LOG.info("Salvo com sucesso");
            return repository.save(notaFiscal);
        } catch (NotaFiscalExcecao nfe) {
            LOG.error(nfe.getMessage());
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
        try {
            NotaFiscal nota = this.buscarPorId(id);
            if (notaFiscal.getTipo() != null) {
                nota.setTipo(notaFiscal.getTipo());
            }
            if (notaFiscal.getParceiroNegocio() != null) {
                nota.setParceiroNegocio(notaFiscal.getParceiroNegocio());
            }
            if (notaFiscal.getData() != null) {
                nota.setData(notaFiscal.getData());
            }
            if (notaFiscal.getNumero() != 0) {
                nota.setNumero(notaFiscal.getNumero());
            }
            validar(nota);
            return repository.save(nota);

        } catch (NotaFiscalExcecao nfe) {
            LOG.error(nfe.getMessage());
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
        String tipoEntrada = TipoNotaFiscal.ENTRADA.getDescricao();
        String tipoSaida = TipoNotaFiscal.SAIDA.getDescricao();
        return tipoNota.equalsIgnoreCase(tipoEntrada) || tipoNota.equalsIgnoreCase(tipoSaida);

    }

    private void validar(NotaFiscal notaFiscal) throws NotaFiscalExcecao {
        if (notaFiscal.getParceiroNegocio() == null || !Validador.existeParceiroPorId(notaFiscal.getParceiroNegocio().getId())) {
            throw new NotaFiscalExcecao("Falha ao salvar no banco: Informe um parceiro válido.");
        }

        if (!existe(notaFiscal)) {
            throw new NotaFiscalExcecao("Falha ao salvar no banco: Já existe uma nota registrada com esses dados.");
        }

        if (!existe(notaFiscal.getTipo())) {
            throw new NotaFiscalExcecao("Falhao ao salvar no banco: Informe um tipo válido");
        }
        Integer numeroNotaFiscal = notaFiscal.getNumero();
        if (numeroNotaFiscal == null || numeroNotaFiscal < 0) {
            throw new NotaFiscalExcecao("Falha ao salvar no banco: Informe um número válido.");
        }
    }

    /*
     * busca o id da nota fiscal e todos os item vinculados a ele, fazendo o
     * calculo do valor total daquela nota e salvando caso tenha alteração
     */
    public void persistirValorTotal(int idNotaFiscal) {
        NotaFiscal notaPorId = buscarPorId(idNotaFiscal);
        List<ItemNotaFiscal> itens = notaPorId.getItemNotaFiscal();
        BigDecimal valorTotalNota = BigDecimal.valueOf(0);
        for (ItemNotaFiscal item : itens) {

            valorTotalNota = valorTotalNota.add(item.getValorTotal());

        }
        notaPorId.setValorTotal(valorTotalNota);
        repository.save(notaPorId);

    }

}
