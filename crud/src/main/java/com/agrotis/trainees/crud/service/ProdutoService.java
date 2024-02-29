package com.agrotis.trainees.crud.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.CampoEmptyOrNullException;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository repository;
    private final ParceiroNegocioRepository parceiroNegocioRepository;

    public ProdutoService(ProdutoRepository repository, ParceiroNegocioRepository parceiroNegocioRepository) {
        super();
        this.repository = repository;
        this.parceiroNegocioRepository = parceiroNegocioRepository;
    }

    public ProdutoDto salvar(ProdutoDto produto) {
    	if (produto.getId() == null) {
            throw new CampoEmptyOrNullException("ID do produto não pode ser nulo.");
        }
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new CampoEmptyOrNullException("Nome do produto não pode ser nulo ou vazio.");
        }
        if (produto.getFabricante() == null || produto.getFabricante().getId() == null) {
            throw new CampoEmptyOrNullException("ID do Fabricante do produto não pode ser nulo.");
        }
        if (produto.getDataFabricacao() == null) {
            throw new CampoEmptyOrNullException("Data de fabricação do produto não pode ser nula.");
        }
        if (produto.getDataValidade() == null) {
            throw new CampoEmptyOrNullException("Data de validade do produto não pode ser nula.");
        }
        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            throw new CampoEmptyOrNullException("Descrição do produto não pode ser nula ou vazia.");
        }
        if (produto.getEstoque() == null) {
            throw new CampoEmptyOrNullException("Estoque do produto não pode ser nulo.");
        }
        if (produto.getDataValidade().isBefore(produto.getDataFabricacao())) {
            throw new CampoEmptyOrNullException("Data de validade não pode ser anterior à data de fabricação.");
        }
        
        

        Produto entidade = converteParaEntidade(produto);
        ParceiroNegocio fabricanteSalvo = parceiroNegocioRepository.save(entidade.getFabricante());
        entidade.setFabricante(fabricanteSalvo);
        Produto produtoSalvo = repository.save(entidade);
        LOG.info("Salvando o produto {}", produto.getDescricao());

        return converteParaDto(produtoSalvo);
    }

    public ProdutoDto buscarPorId(Integer id) {
    	 if (id < 0) {
    	        throw new EntidadeNaoEncontradaException("ID do produto não é válido.");
    	    }

        return repository.findById(id).map(ProdutoService::converteParaDto)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("O produto nao foi encontrado pelo id {}. "));

    }

    
    public void deletarPorId(Integer id) {
        repository.findById(id).ifPresentOrElse(
            produto -> {
                repository.deleteById(id);
                LOG.info("Produto Deletado com sucesso");
            },
            () -> {
                LOG.info("Produto com o ID {} não foi encontrado pelo id", id);
                throw new EntidadeNaoEncontradaException("Produto com o ID " + id + " não foi encontrado pelo id");
            }
        );
    }

    public List<ProdutoDto> listarTodos() {
        return repository.findAll().stream().map(ProdutoService::converteParaDto).collect(Collectors.toList());
    }

    public ProdutoDto update(Integer id, ProdutoDto dto) {
        return repository.findById(id).map(produtoExistente -> {
            produtoExistente.setDescricao(dto.getDescricao());

            ParceiroNegocio fabricante = dto.getFabricante();
            if (fabricante != null && fabricante.getId() != null) {
                ParceiroNegocio fabricanteExistente = parceiroNegocioRepository.findById(fabricante.getId())
                                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                                                "Fabricante com o ID " + fabricante.getId() + " não encontrado"));

                fabricanteExistente.setNome(fabricante.getNome());
                fabricanteExistente.setEndereco(fabricante.getEndereco());

                produtoExistente.setFabricante(fabricanteExistente);
            } else {

                ParceiroNegocio fabricanteSalvo = parceiroNegocioRepository.save(fabricante);
                produtoExistente.setFabricante(fabricanteSalvo);
            }

            produtoExistente.setEstoque(dto.getEstoque());
            produtoExistente.setDataValidade(dto.getDataValidade());
            produtoExistente.setDataFabricacao(dto.getDataFabricacao());

            LOG.info("Atualizando o produto: {}", produtoExistente.getDescricao());

            Produto produtoAtualizado = repository.save(produtoExistente);
            return converteParaDto(produtoAtualizado);
        }).orElseThrow(() -> {

            LOG.info("Não foi possível encontrar o produto pelo ID {}", id);
            return new EntidadeNaoEncontradaException("Produto com o ID " + id + " não encontrado");
        });
    }
    
    

    
    
    public ProdutoDto atualizarEstoque(ItemNotaFiscal itemNotaFiscal) {
        Produto produto = itemNotaFiscal.getProduto();
        BigDecimal quantidade = itemNotaFiscal.getQuantidade();
        BigDecimal custoTotal = itemNotaFiscal.getValorTotal();
        NotaFiscalTipo notaFiscalTipo = itemNotaFiscal.getNotaFiscal().getTipo();
        BigDecimal estoque = produto.getEstoque();
        BigDecimal novoEstoque;
        BigDecimal novoCustoMedio;
        if (notaFiscalTipo == NotaFiscalTipo.ENTRADA) {
            BigDecimal custoMedioAtual = produto.getCustoMedio();
            novoEstoque = estoque.add(quantidade);
            BigDecimal novoCustoTotal = custoMedioAtual.multiply(estoque).add(custoTotal);
            novoCustoMedio = novoCustoTotal.divide(novoEstoque, 2, RoundingMode.HALF_UP);
        } else {
            if (estoque.subtract(quantidade).compareTo(BigDecimal.ZERO) >= 0) {
                novoEstoque = estoque.subtract(quantidade);
                novoCustoMedio = produto.getCustoMedio(); // Não altera o custo médio em uma saída
            } else {
                throw new IllegalArgumentException("Não é possível remover mais itens do que o disponível em estoque.");
            }
        }
        produto.setEstoque(novoEstoque);
        produto.setCustoMedio(novoCustoMedio);
        return salvarProdutoAtualizado(produto);
    }
    private ProdutoDto salvarProdutoAtualizado(Produto produto) {
        Produto produtoAtualizado = repository.save(produto);
        return converteParaDto(produtoAtualizado);
    }
    
   


    public static ProdutoDto converteParaDto(Produto entidade) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setDescricao(entidade.getDescricao());
        dto.setNome(entidade.getNome());
        dto.setFabricante(entidade.getFabricante());
        dto.setDataFabricacao(entidade.getDataFabricacao());
        dto.setDataValidade(entidade.getDataValidade());
        dto.setEstoque(entidade.getEstoque());
        return dto;
    }

    public static Produto converteParaEntidade(ProdutoDto dto) {
    	  
    	  
        Produto entidade = new Produto();
        entidade.setId(dto.getId());
        entidade.setDescricao(dto.getDescricao());
        entidade.setNome(dto.getNome());
        entidade.setFabricante(dto.getFabricante());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setEstoque(dto.getEstoque());
        return entidade;
    }
}
