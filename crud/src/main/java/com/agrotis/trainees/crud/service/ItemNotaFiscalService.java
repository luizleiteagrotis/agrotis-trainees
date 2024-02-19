package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ItemNotaFiscalDto;
import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ItemDuplicadoException;
import com.agrotis.trainees.crud.exception.ItemNaoEncontrado;
import com.agrotis.trainees.crud.exception.ProdutoDuplicadoException;
import com.agrotis.trainees.crud.exception.QuantidadeInsuficienteException;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.utils.ItemNotaFiscalDTOMapper;
import com.agrotis.trainees.crud.utils.NotaFiscalDTOMapper;
import com.agrotis.trainees.crud.utils.ProdutoDTOMapper;

@Service
public class ItemNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);

    private final ItemNotaFiscalRepository repository;

    private final ItemNotaFiscalDTOMapper mapper;

    private final ProdutoService produtoService;

    private final ProdutoDTOMapper mapperProduto;

    private final NotaFiscalService notaService;

    private final NotaFiscalDTOMapper mapperNotaFiscal;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository, ProdutoService produtoService, NotaFiscalService notaService,
                    ItemNotaFiscalDTOMapper mapper, NotaFiscalDTOMapper mapperNotaFiscal, ProdutoDTOMapper mapperProduto) {
        super();
        this.repository = repository;
        this.mapper = mapper;
        this.produtoService = produtoService;
        this.mapperProduto = mapperProduto;
        this.notaService = notaService;
        this.mapperNotaFiscal = mapperNotaFiscal;
    }

    public ItemNotaFiscalDto salvar(ItemNotaFiscalDto dto) {

        ItemNotaFiscal entidade = mapper.convertarParaEntidade(dto);

        NotaFiscalDto notaFiscalDto = notaService.buscarPorId(entidade.getNotaFiscal().getId());
        entidade.setNotaFiscal(mapperNotaFiscal.converterParaEntidade(notaFiscalDto));

        ProdutoDto produtoDto = produtoService.buscarPorId(entidade.getProduto().getId());
        entidade.setProduto(mapperProduto.converterParaEntidade(produtoDto));

        entidade.setValorTotal(entidade.getValorUnitario().multiply(BigDecimal.valueOf(entidade.getQuantidade())));

        if (repository.existsByProdutoAndNotaFiscal(entidade.getProduto(), entidade.getNotaFiscal())) {
            throw new ItemDuplicadoException("O item já existe na nota");
        }

        atualizarEstoque(entidade);
        ItemNotaFiscal entidadeSalva = repository.save(entidade);
        notaService.adicionarItem(entidade);
        return mapper.converterParaDto(entidadeSalva);
    }

    public ItemNotaFiscalDto atualizar(ItemNotaFiscalDto dto) {

        ItemNotaFiscal entidade = mapper.convertarParaEntidade(dto);

        NotaFiscalDto notaFiscalDto = notaService.buscarPorId(entidade.getNotaFiscal().getId());
        entidade.setNotaFiscal(mapperNotaFiscal.converterParaEntidade(notaFiscalDto));

        ProdutoDto produtoDto = produtoService.buscarPorId(entidade.getProduto().getId());
        entidade.setProduto(mapperProduto.converterParaEntidade(produtoDto));

        entidade.setValorTotal(entidade.getValorUnitario().multiply(BigDecimal.valueOf(entidade.getQuantidade())));

        if (repository.existsByProdutoAndNotaFiscalAndIdNot(entidade.getProduto(), entidade.getNotaFiscal(), entidade.getId())) {
            throw new ProdutoDuplicadoException("Já existe um item de nota fiscal com o mesmo produto e nota fiscal");
        }

        atualizarEstoque(entidade);
        ItemNotaFiscal entidadeSalva = repository.save(entidade);
        notaService.atualizarValorTotal(entidadeSalva.getNotaFiscal());
        return mapper.converterParaDto(entidadeSalva);
    }

    public ItemNotaFiscalDto buscarPorId(Integer id) {
        return repository.findById(id).map(mapper::converterParaDto)
                        .orElseThrow(() -> new ItemNaoEncontrado("Item de nota fiscal não encontrado para o id " + id));
    }

    public List<ItemNotaFiscalDto> buscarPorProduto(Integer id) {
        ProdutoDto produtoDto = produtoService.buscarPorId(id);
        Produto produto = mapperProduto.converterParaEntidade(produtoDto);
        List<ItemNotaFiscal> entidades = repository.findAllByProduto(produto);
        if (entidades.isEmpty()) {
            throw new ItemNaoEncontrado("Item de nota fiscal não encontrada para o produto de nome: " + produto.getDescricao()
                            + " e id: " + produto.getId());
        }
        return entidades.stream().map(mapper::converterParaDto).collect(Collectors.toList());
    }

    public List<ItemNotaFiscalDto> listarTodos() {
        List<ItemNotaFiscal> entidades = repository.findAll();
        return entidades.stream().map(mapper::converterParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        ItemNotaFiscalDto dto = buscarPorId(id);
        ItemNotaFiscal entidade = mapper.convertarParaEntidade(dto);

        entidade.setQuantidade(0);
        entidade.setValorTotal(entidade.getValorUnitario().multiply(BigDecimal.valueOf(entidade.getQuantidade())));

        atualizarEstoque(entidade);
        notaService.removerItem(entidade, entidade.getNotaFiscal());
        repository.deleteById(id);
    }

    public void atualizarEstoque(ItemNotaFiscal item) throws QuantidadeInsuficienteException {
        Produto produto = item.getProduto();
        String tipo = item.getNotaFiscal().getNotaFiscalTipo().getNome();
        int quantidade = item.getQuantidade();
        int quantidadeProduto = produto.getQuantidadeEstoque();

        ItemNotaFiscal itemExistente = repository.findByProdutoAndNotaFiscal(item.getProduto(), item.getNotaFiscal());

        int diferencaQuantidade = itemExistente != null ? quantidade - itemExistente.getQuantidade() : quantidade;

        if (tipo.equalsIgnoreCase("SAÍDA") && (quantidadeProduto - diferencaQuantidade) < 0) {
            throw new QuantidadeInsuficienteException(
                            "Quantidade insuficiente em estoque para a saída do produto. Quantidade em estoque: "
                                            + quantidadeProduto + " " + ". Quantidade item saída: " + diferencaQuantidade);
        }

        int novaQuantidadeEstoque = tipo.equalsIgnoreCase("ENTRADA") ? quantidadeProduto + diferencaQuantidade
                        : quantidadeProduto - diferencaQuantidade;
        produto.setQuantidadeEstoque(novaQuantidadeEstoque);

        produtoService.atualizar(mapperProduto.converterParaDto(produto));
    }

}
