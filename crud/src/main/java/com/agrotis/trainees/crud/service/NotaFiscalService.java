package com.agrotis.trainees.crud.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final NotaFiscalRepository repository;

    private final ParceiroNegocioRepository parceiroNegocioRepository;
    

    public NotaFiscalService(NotaFiscalRepository repository, ParceiroNegocioRepository parceiroNegocioRepository) {
        super();
        this.repository = repository;
        this.parceiroNegocioRepository = parceiroNegocioRepository;
        
    }


    public NotaFiscalDto salvar(NotaFiscalDto cabecalhoDto) {
        NotaFiscal cabecalho = converteParaEntidade(cabecalhoDto);

        for (ItemNotaFiscal item : cabecalho.getItens()) {
            BigDecimal valor = item.getQuantidade().multiply(item.getPrecoUnitario());
            item.setValorTotal(valor);
        }

        ParceiroNegocio parceiroNegocio = cabecalho.getParceiroNegocio();
        if (parceiroNegocio != null && parceiroNegocio.getId() == null) {
            parceiroNegocio = parceiroNegocioRepository.save(parceiroNegocio);
            cabecalho.setParceiroNegocio(parceiroNegocio);
        }

        BigDecimal valorTotal = calcularValorTotal(cabecalho);
        cabecalho.setValorTotal(valorTotal);

        NotaFiscal entidadeSalva = repository.save(cabecalho);
        
     

        return converteParaDto(entidadeSalva);
    }
    
   
    
    
    
    public NotaFiscalDto buscarPorId(Integer id) {
        NotaFiscal cabecalho = repository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));

        BigDecimal valorTotal = calcularValorTotal(cabecalho);
        cabecalho.setValorTotal(valorTotal);

        return converteParaDto(cabecalho);
    }
    

    public NotaFiscalDto update(Integer id, NotaFiscalDto dto) {
        return repository.findById(id).map(cabecalhoExistente -> {

            LOG.debug("DTO: {}", dto);

            cabecalhoExistente.setData(dto.getData());
            cabecalhoExistente.setTipo(dto.getTipo());
            cabecalhoExistente.setNumero(dto.getNumero());

            ParceiroNegocio parceiroNegocioExistente = dto.getParceiroNegocio().getId() != null
                            ? parceiroNegocioRepository.findById(dto.getParceiroNegocio().getId()).orElse(null)
                            : null;

            LOG.debug("ParceiroNegocio entity before update: {}", parceiroNegocioExistente);

            if (parceiroNegocioExistente == null) {
                ParceiroNegocio novoParceiroNegocio = dto.getParceiroNegocio();
                parceiroNegocioExistente = parceiroNegocioRepository.save(novoParceiroNegocio);
            }

            LOG.debug("ParceiroNegocio entity after update: {}", parceiroNegocioExistente);

            cabecalhoExistente.setParceiroNegocio(parceiroNegocioExistente);

            NotaFiscal cabecalhoAtualizado = repository.save(cabecalhoExistente);

            LOG.debug("CabecalhoNota entity after update: {}", cabecalhoAtualizado);

            return converteParaDto(cabecalhoAtualizado);
        }).orElseThrow(() -> {
            LOG.info("Não foi possível encontrar o cabecalho pelo ID {}", id);
            return new EntidadeNaoEncontradaException("Cabechalho com o ID " + id + " não encontrado");
        });
    }
    
    
    

    public List<NotaFiscalDto> listarTodos() {
        return repository.findAll().stream().map(NotaFiscalService::converteParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(nota -> {
            repository.deleteById(id);
            LOG.info("Nota Fiscal" + id + "Deletada com sucesso");
            return nota;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("NOTA FISCAL com o ID " + id + " não foi encontrada!"));
    }

//    public BigDecimal calcularValorTotal(NotaFiscal cabecalho) {
//        return cabecalho.getItens().stream()
//                .map(item -> item.getQuantidade().multiply(item.getPrecoUnitario()))
//                .reduce(BigDecimal.ZERO, (subtotal, element) -> subtotal.add(element));
//    }
    

        public BigDecimal calcularValorTotal(NotaFiscal cabecalho) {
            return cabecalho.getItens().stream()
                    .map(item -> item.getQuantidade().multiply(item.getPrecoUnitario()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
       
    

    public static NotaFiscal converteParaEntidade(NotaFiscalDto dto) {
        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setData(dto.getData());
        entidade.setNumero(dto.getNumero());
        entidade.setTipo(dto.getTipo());
        entidade.setParceiroNegocio(dto.getParceiroNegocio());
        entidade.setValorTotal(dto.getValorTotal());
       

        return entidade;
    }

    public static NotaFiscalDto converteParaDto(NotaFiscal entidade) {
        NotaFiscalDto dto = new NotaFiscalDto();

        dto.setId(entidade.getId());
        dto.setData(entidade.getData());
        dto.setNumero(entidade.getNumero());
        dto.setTipo(entidade.getTipo());
        dto.setParceiroNegocio(entidade.getParceiroNegocio());
        dto.setValorTotal(entidade.getValorTotal());
        

        return dto;
    }

}
