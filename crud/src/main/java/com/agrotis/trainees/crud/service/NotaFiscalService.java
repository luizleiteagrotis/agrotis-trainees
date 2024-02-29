package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioTipoRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository, NotaFiscalItemRepository notaFiscalItemRepository,
                    ParceiroNegocioTipoRepository parceiroNegocioTipoRepository) {
        this.notaFiscalRepository = notaFiscalRepository;
    }

    @Transactional
    public NotaFiscal salvar(Integer id) throws NotFoundException {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id).orElseThrow(NotFoundException::new);
        return notaFiscalRepository.save(notaFiscal);
    }

    public NotaFiscalDto salvar(NotaFiscalDto dto) throws NotFoundException {
        NotaFiscal entidade = converterParaEntidade(dto);
        NotaFiscal savedNotaFiscal = notaFiscalRepository.save(entidade);
        atualizarValorTotal(savedNotaFiscal);
        LOG.info("Salva nota fiscal {}", savedNotaFiscal.getNumero());
        return converterParaDto(savedNotaFiscal);
    }

    public NotaFiscalDto buscarPorId(Integer id) throws NotFoundException {
        NotaFiscal entidade = notaFiscalRepository.findById(id).orElseThrow(NotFoundException::new);
        return converterParaDto(entidade);
    }

    public NotaFiscal buscarPorNotaFiscalTipo(NotaFiscalTipo tipoNota) {
        return notaFiscalRepository.findByNotaFiscalTipo(tipoNota).orElse(null);
    }

    public Optional<NotaFiscal> buscarPorParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        return notaFiscalRepository.findByParceiroNegocio(parceiroNegocio);
    }

    public NotaFiscal buscarPorNumero(Integer numero) {
        return notaFiscalRepository.findByNumero(numero).orElse(null);
    }

    public List<NotaFiscalDto> listarTodos() {
        List<NotaFiscal> entidades = notaFiscalRepository.findAll();
        return entidades.stream().map(this::converterParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        notaFiscalRepository.deleteById(id);
        LOG.info("Deletada nota fiscal com ID: {}", id);
    }

    @Transactional
    public void atualizarValorTotal(Integer id) throws NotFoundException {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id).orElseThrow(() -> new NotFoundException());

        BigDecimal valorTotal = BigDecimal.ZERO;

        if (notaFiscal.getItensNota() != null) {
            valorTotal = notaFiscal.getItensNota().stream().map(NotaFiscalItem::getValorTotal).reduce(BigDecimal.ZERO,
                            BigDecimal::add);
        }

        notaFiscal.setValorTotal(valorTotal);
        notaFiscalRepository.save(notaFiscal);
    }

    @Transactional
    public NotaFiscalDto atualizar(Integer id, NotaFiscalDto dto) throws NotFoundException {
        NotaFiscal entidade = converterParaEntidade(dto);
        entidade.setId(id);
        BigDecimal novoValorTotal = calcularValorTotal(dto.getItensNota());
        entidade.setValorTotal(novoValorTotal);

        atualizarValorTotal(entidade);

        return converterParaDto(notaFiscalRepository.save(entidade));
    }

    private BigDecimal calcularValorTotal(List<NotaFiscalItem> itens) {
        if (itens == null) {
            return BigDecimal.ZERO;
        }

        return itens.stream().filter(item -> item.getPrecoUnitario() != null).map(NotaFiscalItem::getPrecoUnitario)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public NotaFiscalDto converterParaDto(NotaFiscal entidade) {
        NotaFiscalDto notaDto = new NotaFiscalDto();
        notaDto.setId(entidade.getId());
        notaDto.setNotaFiscalTipo(entidade.getNotaFiscalTipo());
        ParceiroNegocio parceiroNegocio = entidade.getParceiroNegocio();
        ParceiroNegocioDto parceiroNegocioDto = new ParceiroNegocioDto();
        parceiroNegocioDto.setId(parceiroNegocio.getId());
        notaDto.setParceiroNegocio(parceiroNegocioDto);
        notaDto.setNumero(entidade.getNumero());
        notaDto.setDataEmissao(entidade.getDataEmissao());
        notaDto.setItensNota(entidade.getItensNota());
        notaDto.setValorTotal(entidade.getValorTotal());

        return notaDto;
    }

    public NotaFiscal converterParaEntidade(NotaFiscalDto dto) throws NotFoundException {
        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setNotaFiscalTipo(dto.getNotaFiscalTipo());

        ParceiroNegocioDto parceiroNegocioDto = dto.getParceiroNegocio();
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setId(parceiroNegocioDto.getId());
        parceiroNegocio.setNome(parceiroNegocioDto.getNome());
        parceiroNegocio.setInscricaoFiscal(parceiroNegocioDto.getInscricaoFiscal());

        entidade.setParceiroNegocio(parceiroNegocio);
        entidade.setNumero(dto.getNumero());
        entidade.setDataEmissao(dto.getDataEmissao());
        entidade.setItensNota(dto.getItensNota());
        entidade.setValorTotal(dto.getValorTotal());

        return entidade;
    }

}
