package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);
    private NotaFiscalConversaoService conversao;
    private NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalConversaoService conversao, NotaFiscalRepository repository) {
        super();
        this.conversao = conversao;
        this.repository = repository;
    }

    @Transactional
    public NotaFiscal salvar(NotaFiscal entidade) {
        if (entidade.getNumero() == null) {
            throw new IllegalArgumentException("O número da nota fiscal é obrigatório.");
        }
        return repository.save(entidade);
    }

    public NotaFiscalDto salvar(NotaFiscalDto dto) {
        NotaFiscal entidade = conversao.converterParaEntidade(dto);
        NotaFiscal notaFiscalSalva = repository.save(entidade);
        List<NotaFiscalItem> itens = notaFiscalSalva.getItens();
        atualizarNotaFiscal(itens);
        LOG.info("Salva nota fiscal {}", notaFiscalSalva.getNumero());
        return conversao.converterParaDto(notaFiscalSalva);
    }

    public NotaFiscalDto buscarPorId(Integer id) throws NotFoundException {
        NotaFiscal entidade = repository.findById(id).orElseThrow(() -> new NotFoundException());
        return conversao.converterParaDto(entidade);
    }

    // ajustar métodos de busca

    public NotaFiscalDto buscarPorTipo(NotaFiscalTipo tipo) throws NotFoundException {
        NotaFiscal entidade = repository.findByTipo(tipo).orElseThrow(() -> {
            LOG.error("Nota Fiscal não encontrada para o tipo de {}.", tipo);
            return new NotFoundException(); // não estou conseguindo inserir
                                            // mensagens quando faço esse tipo
                                            // de chamada por conta do
                                            // javassist, que não me deixa
                                            // importar o
                                            // NotFountException(String);
        });

        return conversao.converterParaDto(entidade);
    }

    public NotaFiscalDto buscarPorParceiroNegocio(ParceiroNegocioDto parceiroNegocioDto) throws NotFoundException {
        NotaFiscal entidade = repository.findByParceiroNegocio(parceiroNegocioDto).orElseThrow(() -> {
            LOG.error("Nota Fiscal não encontrada para o parceiro {}.", parceiroNegocioDto);
            return new NotFoundException();
        });

        return conversao.converterParaDto(entidade);
    }

    public NotaFiscalDto buscarPorNumero(Integer numero) throws NotFoundException {
        NotaFiscal entidade = repository.findByNumero(numero).orElseThrow(() -> {
            LOG.error("Nota Fiscal não encontrada para o número {}.", numero);
            return new NotFoundException();
        });

        return conversao.converterParaDto(entidade);
    }

    public NotaFiscalDto buscarPorData(LocalDate data) throws NotFoundException {
        NotaFiscal entidade = repository.findByData(data).orElseThrow(() -> {
            LOG.error("Nota Fiscal não encontrada para a data de {}.", data);
            return new NotFoundException();
        });

        return conversao.converterParaDto(entidade);
    }

    public List<NotaFiscalDto> listarTodos() {
        List<NotaFiscal> entidades = repository.findAll();
        return entidades.stream().map(entidade -> conversao.converterParaDto(entidade)).collect(Collectors.toList());
    }

    public void deletarPorId(Integer integer) {
        repository.deleteById(integer);
        LOG.info("Deletado com sucesso");
    }

    public NotaFiscal inserir(@Valid NotaFiscalDto dto) {
        NotaFiscal entidade = conversao.converterParaEntidade(dto);
        return repository.save(entidade);
    }

    public NotaFiscalDto atualizar(NotaFiscalDto dto) {
        NotaFiscal entidade = conversao.converterParaEntidade(dto);
        return conversao.converterParaDto(repository.save(entidade));
    }

    @Transactional
    public void atualizarNotaFiscal(List<NotaFiscalItem> itens) {
        if (itens.isEmpty()) {
            // Lista vazia para crrigir erro
            return;
        }

        NotaFiscal notaFiscal = itens.get(0).getNotaFiscal();
        BigDecimal novoValorTotal = BigDecimal.ZERO;

        for (NotaFiscalItem item : itens) {
            BigDecimal precoUnitario = item.getPrecoUnitario();
            BigDecimal quantidade = new BigDecimal(item.getQuantidade());
            BigDecimal valorItem = precoUnitario.multiply(quantidade);

            if (notaFiscal.getNotaFiscalTipo() == NotaFiscalTipo.ENTRADA) {
                novoValorTotal = novoValorTotal.add(valorItem);
            } else if (notaFiscal.getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
                novoValorTotal = novoValorTotal.subtract(valorItem);
            }
        }

        notaFiscal.setValorTotal(novoValorTotal);
        repository.save(notaFiscal);

    }

}