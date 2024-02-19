package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        super();
        this.repository = repository;
    }

    public ProdutoDto salvar(ProdutoDto dto) {
        Produto entidade = converterParaEntidade(dto);
        repository.save(entidade);
        LOG.info("Salvo Produto {}", entidade.getNome());
        return converterParaDto(entidade);
    }

    public ProdutoDto buscarPorId(Integer id) throws NotFoundException {
        Produto entidade = repository.findById(id).orElseThrow(() -> new NotFoundException());
        return converterParaDto(entidade);
    }

    // ajustar métodos de busca

    public Produto buscarPorParceiro(ParceiroNegocio parceiroNegocio) {
        return repository.findByParceiroNegocio(parceiroNegocio).orElseGet(() -> {
            LOG.error("Produto não encontrado para o parceiro {}.", parceiroNegocio);
            return null;
        });
    }

    public Produto buscarPorFabricante(String fabricante) {
        return repository.findByFabricante(fabricante).orElseGet(() -> {
            LOG.error("Produto não encontrado para o fabricante {}.", fabricante);
            return null;
        });
    }

    public Produto buscarPorDataFabricacao(LocalDate dataFabricacao) {
        return repository.findByDataFabricacao(dataFabricacao).orElseGet(() -> {
            LOG.error("Produto não encontrado para data de fabricacao {}.", dataFabricacao);
            return null;
        });
    }

    public Produto buscarPorDataValidade(LocalDate dataValidade) {
        return repository.findByDataValidade(dataValidade).orElseGet(() -> {
            LOG.error("Produto não encontrado para data de validade {}.", dataValidade);
            return null;
        });
    }

    public List<ProdutoDto> listarTodos() {
        List<Produto> entidades = repository.findAll();
        return entidades.stream().map(entidade -> converterParaDto(entidade)).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Produto deletado com sucesso");
    }

    public Produto inserir(@Valid ProdutoDto dto) {
        Produto entidade = converterParaEntidade(dto);
        return repository.save(entidade);
    }

    public ProdutoDto atualizar(ProdutoDto dto) {
        Produto entidade = converterParaEntidade(dto);
        return converterParaDto(repository.save(entidade));
    }

    public static ProdutoDto converterParaDto(Produto entidade) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setDescricao(entidade.getDescricao());
        dto.setParceiroNegocio(entidade.getParceiroNegocio());
        dto.setFabricante(entidade.getFabricante());
        dto.setDataFabricacao(entidade.getDataFabricacao());
        dto.setDataValidade(entidade.getDataValidade());
        dto.setEstoque(entidade.getEstoque());

        return dto;
    }

    public static Produto converterParaEntidade(ProdutoDto dto) {
        Produto entidade = new Produto();
        entidade.setId(dto.getId());
        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
        entidade.setParceiroNegocio(dto.getParceiroNegocio());
        entidade.setFabricante(dto.getFabricante());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setEstoque(dto.getEstoque());

        return entidade;
    }

}
