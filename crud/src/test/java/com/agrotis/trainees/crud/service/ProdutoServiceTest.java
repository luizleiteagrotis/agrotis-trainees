package com.agrotis.trainees.crud.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.CampoEmptyOrNullException;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;


@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ParceiroNegocioRepository parceiroNegocioRepository;

    @InjectMocks
    private ProdutoService service;

    @Test
    void saveProductWhenDtoValido() {
        ProdutoDto produtoDto = criarProdutoDto();
        Produto produtoSalvo = criarProduto();
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        ProdutoDto result = service.salvar(produtoDto);

        assertNotNull(result);
        assertEquals(produtoSalvo.getId(), result.getId());
        verify(produtoRepository).save(any(Produto.class));
    }

    @Test
    void salvarCampoEmptyOrNullWhenDtoInvalid() {
        ProdutoDto produtoDto = new ProdutoDto();
        assertThrows(CampoEmptyOrNullException.class, () -> {
            service.salvar(produtoDto);
        });
    }
    
//      @Test
//       void salvarCampoEmptyOrNullWhenDtoInvalid() {
//        // Crie um ProdutoDto inválido (sem dados)
//        ProdutoDto produtoDto = new ProdutoDto();
//
//        // Configure o comportamento do seu serviço para lançar a exceção CampoEmptyOrNullException
//        // quando um ProdutoDto inválido for passado como argumento para o método salvar()
//        when(service.salvar(any(ProdutoDto.class))).thenThrow(CampoEmptyOrNullException.class);
//
//        // Verifique se o serviço está lançando a exceção esperada
//        assertThrows(CampoEmptyOrNullException.class, () -> {
//            service.salvar(produtoDto);
//        });
//    }
   
    

    @Test
    void buscarPeloIdRetornarProdutoDto() {
        int id = 1;
        Produto produto = criarProduto();
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        ProdutoDto result = service.buscarPorId(id);

        assertNotNull(result);
        assertEquals(produto.getDescricao(), result.getDescricao());
    }

    @Test
    void buscarPeloIdLancaExcecao_SeProdutoNaoEncontrado() {
        int id = 1;
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.buscarPorId(id);
        });
    }

    @Test
    void buscarTodosRetornaListDeProdutosDto() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(criarProduto());
        when(produtoRepository.findAll()).thenReturn(produtos);

        List<ProdutoDto> result = service.listarTodos();

        assertNotNull(result);
        assertEquals(produtos.size(), result.size());
        assertEquals(produtos.get(0).getDescricao(), result.get(0).getDescricao());
    }

    @Test
    void deletarPorIdDeletaProduto_WhenFound() {
        int id = 1;
        Produto produto = criarProduto();
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        service.deletarPorId(id);

        verify(produtoRepository).deleteById(id);
    }

    @Test
    void deletarPorIdLancaExcecao_IfProductNotFound() {
        int id = 1;
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.deletarPorId(id);
        });
    }

    @Test
    void atualizarProduct_WhenFindProduct() {
        int id = 1;
        Produto produtoExistente = criarProduto();
        ProdutoDto produtoDto = criarProdutoDto();
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.save(any(Produto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProdutoDto result = service.update(id, produtoDto);

        assertNotNull(result);
        assertEquals(produtoDto.getDescricao(), result.getDescricao());
        verify(produtoRepository).save(any(Produto.class));
    }

    @Test
    void atualizar_LancarExceptionWhenProductNotFound() {
        int id = 1;
        ProdutoDto produtoDto = criarProdutoDto();
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.update(id, produtoDto);
        });
    }

    private ProdutoDto criarProdutoDto() {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(dto.getId());
        dto.setDescricao("Produto Teste");
        dto.setEstoque(new BigDecimal(10));
        dto.setDataFabricacao(LocalDate.of(2024, 2, 21));
        dto.setDataValidade(LocalDate.of(2025, 2, 21));
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setNome("Fabricante AgroFertil LTDA");
        dto.setFabricante(fabricante);
        return dto;
    }

    private Produto criarProduto() {
        Produto produto = new Produto();
        produto.setId(produto.getId());
        produto.setDescricao("Produto Algodao Fertil");
        produto.setEstoque(new BigDecimal(10));
        produto.setDataFabricacao(LocalDate.of(2024, 2, 21));
        produto.setDataValidade(LocalDate.of(2025, 2, 21));
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setNome("Fabricante AgroFertil Algodoes");
        produto.setFabricante(fabricante);
        return produto;
    }

}
	   




	    
	    
	    
	    
	    
	    
	
	    

