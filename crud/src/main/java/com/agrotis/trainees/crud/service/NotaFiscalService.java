package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.agrotis.trainees.crud.convert.NotaFiscalConversor;
import com.agrotis.trainees.crud.dto.NotaFiscalDto;
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
    private final NotaFiscalConversor notaFiscalConversor;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository, NotaFiscalConversor notaFiscalConversor) {
        super();
        this.repository = notaFiscalRepository;
        this.notaFiscalConversor = notaFiscalConversor;
    }

    public NotaFiscalDto salvar(NotaFiscalDto notaFiscal) {
        try {
            NotaFiscal entidade = notaFiscalConversor.converter(notaFiscal);
            if (!existe(entidade)) {
                throw new NotaFiscalExcecao("Falha ao salvar no banco: Já existe uma nota registrada com esses dados.");
            }

            validar(entidade);
            LOG.info("Salvo com sucesso");
            repository.save(entidade);
            return notaFiscalConversor.converter(entidade);
        } catch (NotaFiscalExcecao nfe) {
            LOG.error(nfe.getMessage());
            return null;
        }
    }

    public NotaFiscalDto buscarPorId(int id) {
        NotaFiscal entidade = repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar uma nota fiscal com este id {}", id);
            return null;
        });
        return notaFiscalConversor.converter(entidade);
    }

    // TO-DO
    public List<NotaFiscal> buscarPorParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        return repository.findByParceiroNegocio(parceiroNegocio);
    }

    public List<NotaFiscalDto> buscarPorTipoNotaFiscal(String tipo) {
        List<NotaFiscal> entidade = repository.findByTipo(tipo);
        return notaFiscalConversor.converter(entidade);

    }

    public List<NotaFiscalDto> buscarPorNumero(int numero) {
        List<NotaFiscal> entidade = repository.findByNumero(numero);
        return notaFiscalConversor.converter(entidade);
    }

    public List<NotaFiscalDto> buscarPorData(LocalDate data) {
        List<NotaFiscal> entidade = repository.findByData(data);
        return notaFiscalConversor.converter(entidade);
    }

    public List<NotaFiscalDto> listarTodos() {
        List<NotaFiscal> entidades = repository.findAll();
        return notaFiscalConversor.converter(entidades);
    }

    // TO-DO
    public NotaFiscalDto atualizar(NotaFiscalDto entidade, int id) {
        try {
            NotaFiscal notaFiscal = notaFiscalConversor.converter(entidade);
            NotaFiscal notaAtualizar = verificarPorId(id);

            if (notaFiscal.getTipo() != null) {
                notaAtualizar.setTipo(notaFiscal.getTipo());
            }
            if (notaFiscal.getParceiroNegocio() != null) {
                notaAtualizar.setParceiroNegocio(notaFiscal.getParceiroNegocio());
            }
            if (notaFiscal.getData() != null) {
                notaAtualizar.setData(notaFiscal.getData());
            }
            if (notaFiscal.getNumero() != 0) {
                notaAtualizar.setNumero(notaFiscal.getNumero());
            }
            validar(notaAtualizar);
            if (!repository.findByIdAndNumeroAndTipo(notaAtualizar.getId(), notaAtualizar.getNumero(), notaAtualizar.getTipo())
                            .isEmpty()) {

                repository.save(notaAtualizar);
            } else {
                throw new NotaFiscalExcecao("Falha ao salvar no banco: Já existe uma nota registrada com esses dados.");

            }
            return notaFiscalConversor.converter(notaAtualizar);

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

    protected NotaFiscal verificarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar uma nota fiscal com este id {}", id);
            return null;
        });
    }

    private void validar(NotaFiscal notaFiscal) throws NotaFiscalExcecao {
        if (notaFiscal.getParceiroNegocio() == null || !Validador.existeParceiroPorId(notaFiscal.getParceiroNegocio().getId())) {
            throw new NotaFiscalExcecao("Falha ao salvar no banco: Informe um parceiro válido.");
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
        NotaFiscal entidade = verificarPorId(idNotaFiscal);
        List<ItemNotaFiscal> itens = entidade.getItemNotaFiscal();
        BigDecimal valorTotalNota = BigDecimal.valueOf(0);
        for (ItemNotaFiscal item : itens) {

            valorTotalNota = valorTotalNota.add(item.getValorTotal());

        }
        entidade.setValorTotal(valorTotalNota);
        repository.save(entidade);

    }
}
