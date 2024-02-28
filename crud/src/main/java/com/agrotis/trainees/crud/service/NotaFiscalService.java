package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import com.agrotis.trainees.crud.convert.NotaFiscalConversor;
import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.exception.NotaFiscalExcecao;
import com.agrotis.trainees.crud.helper.TipoNotaFiscal;
import com.agrotis.trainees.crud.helper.Validador;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);
    private final NotaFiscalRepository repository;
    private final NotaFiscalConversor notaFiscalConversor;
    private final Validador validador;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository, NotaFiscalConversor notaFiscalConversor,
                    Validador validador) {
        super();
        this.repository = notaFiscalRepository;
        this.notaFiscalConversor = notaFiscalConversor;
        this.validador = validador;
    }

    public NotaFiscalDto salvar(NotaFiscalDto notaFiscal) throws NotaFiscalExcecao {
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
            throw nfe;
        }
    }

    public NotaFiscalDto buscarPorId(int id) {
        NotaFiscal entidade = repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar uma nota fiscal com este id {}", id);
            return null;
        });
        return notaFiscalConversor.converter(entidade);
    }

    public List<NotaFiscalDto> buscarPorTipoNotaFiscal(String tipo) throws NotaFiscalExcecao {
        try {
            List<NotaFiscal> entidade = repository.findByTipo(tipo);
            if (entidade == null || entidade.isEmpty()) {
                throw new NotaFiscalExcecao("Não possui nenhuma nota fiscal cadastrada com este tipo");
            }
            return notaFiscalConversor.converter(entidade);
        } catch (NotaFiscalExcecao nfe) {
            LOG.error(nfe.getMessage());
            throw nfe;
        }

    }

    public List<NotaFiscalDto> buscarPorNumero(int numero) throws NotaFiscalExcecao {
        try {
            List<NotaFiscal> entidade = repository.findByNumero(numero);
            if (entidade == null || entidade.isEmpty()) {
                throw new NotaFiscalExcecao("Não possui nenhuma nota fiscal cadastrada com este numero");
            }
            return notaFiscalConversor.converter(entidade);
        } catch (NotaFiscalExcecao nfe) {
            LOG.error(nfe.getMessage());
            throw nfe;
        }
    }

    public List<NotaFiscalDto> buscarPorData(LocalDate data) throws NotaFiscalExcecao {
        try {
            List<NotaFiscal> entidade = repository.findByData(data);
            if (entidade == null || entidade.isEmpty()) {
                throw new NotaFiscalExcecao("Não possui nenhuma nota fiscal cadastrada com este numero");
            }
            return notaFiscalConversor.converter(entidade);
        } catch (NotaFiscalExcecao nfe) {
            LOG.error(nfe.getMessage());
            throw nfe;
        }
    }

    public List<NotaFiscalDto> listarTodos() throws NotaFiscalExcecao {
        try {
            List<NotaFiscal> entidades = repository.findAll();
            if (entidades.isEmpty()) {
                throw new NotaFiscalExcecao("Não foi possivel encontrar nenhuma nota fiscal cadastrada.");
            }
            return notaFiscalConversor.converter(entidades);
        } catch (NotaFiscalExcecao nfe) {
            LOG.error(nfe.getMessage());
            throw nfe;
        }
    }

    public NotaFiscalDto atualizar(NotaFiscalDto entidade, int id) {
        try {
            NotaFiscal notaFiscal = notaFiscalConversor.converter(entidade);
            NotaFiscal notaAtualizar = verificarPorId(id);
            atribuirCamposNaoNulos(notaFiscal, notaAtualizar);
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
        if (notaFiscal.getParceiroNegocio() == null || !validador.existeParceiroPorId(notaFiscal.getParceiroNegocio().getId())) {
            throw new NotaFiscalExcecao("Falha ao salvar no banco: Informe um parceiro válido.");
        }

        if (notaFiscal.getTipo() == null || !existe(notaFiscal.getTipo())) {
            throw new NotaFiscalExcecao("Falhao ao salvar no banco: Informe um tipo válido");
        }
        if (notaFiscal.getNumero() <= 0) {
            throw new NotaFiscalExcecao("Falha ao salvar no banco: Informe um número válido.");
        }
    }

    private void atribuirCamposNaoNulos(NotaFiscal notaFiscal, NotaFiscal notaAtualizar) {
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
    }

    /*
     * busca o id da nota fiscal e todos os item vinculados a ele, fazendo o
     * calculo do valor total daquela nota e salvando caso tenha alteração
     */
    public BigDecimal persistirValorTotal(int idNotaFiscal) {
        NotaFiscal entidade = verificarPorId(idNotaFiscal);
        List<ItemNotaFiscal> itens = entidade.getItemNotaFiscal();
        BigDecimal valorTotalNota = BigDecimal.ZERO;
        for (ItemNotaFiscal item : itens) {

            valorTotalNota = valorTotalNota.add(item.getValorTotal());

        }
        entidade.setValorTotal(valorTotalNota);
        repository.save(entidade);
        return valorTotalNota.setScale(2, RoundingMode.HALF_UP);
    }
}
