//package com.agrotis.trainees.crud.service;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.agrotis.trainees.crud.dto.ProdutoDto;
//import com.agrotis.trainees.crud.entity.Produto;
//import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
//import com.agrotis.trainees.crud.repository.ProdutoRepository;

//public class ProdutoServiceTest {
//	
//	    @Mock
//	    private ProdutoRepository produtoRepository;
//
//	    @Mock
//	    private ParceiroNegocioRepository parceiroNegocioRepository;
//
//	    @InjectMocks
//	    private ProdutoService service;
//	    
//	    @BeforeEach
//	    public void setUp() {
//	        MockitoAnnotations.initMocks(this);
//	    }
//

//	    @Test
//	    public void testSalvarProduto() {
//	        ProdutoDto produtoDto = new ProdutoDto();
//	        produtoDto.setId(1);
//	        produtoDto.setDescricao("Produto Teste");
//
//	        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
//	        parceiroNegocio.setId(1);
//	        parceiroNegocio.setNome("Fabricante Teste");
//
//	        produtoDto.setFabricante(parceiroNegocio);
//
//	        Produto produto = new Produto();
//	        produto.setId(1);
//	        produto.setDescricao("Produto Teste");
//	        produto.setFabricante(parceiroNegocio);
//
//	        when(parceiroNegocioRepository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);
//	        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
//
//	        ProdutoDto savedProduto = produtoService.salvar(produtoDto);
//
//	        assertEquals(1, savedProduto.getId());
//	        assertEquals("Produto Teste", savedProduto.getDescricao());
//	    }
//	    
//	    @Test
//	    public void testBuscarPorId() {
//	        Produto produto = new Produto();
//	        produto.setId(1);
//	        produto.setDescricao("Produto Teste");
//
//	        when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));
//
//	        ProdutoDto foundProduto = produtoService.buscarPorId(1);
//
//	        assertEquals(1, foundProduto.getId());
//	        assertEquals("Produto Teste", foundProduto.getDescricao());
//	    }
//
//	    @Test
//	    public void testDeletarPorId() {
//	        Produto produto = new Produto();
//	        produto.setId(1);
//	        produto.setDescricao("Produto Teste");
//
//	        when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));
//
//	        assertDoesNotThrow(() -> produtoService.deletarPorId(1));
//	    }
//
//	    @Test
//	    public void testListarTodos() {
//	        List<Produto> produtos = new ArrayList<>();
//	        produtos.add(new Produto(1, "Produto 1"));
//	        produtos.add(new Produto(2, "Produto 2"));
//
//	        when(produtoRepository.findAll()).thenReturn(produtos);
//
//	        List<ProdutoDto> listaProdutos = produtoService.listarTodos();
//
//	        assertEquals(2, listaProdutos.size());
//	    }
//
//	    @Test
//	    public void testUpdate() {
//	        ProdutoDto produtoDto = new ProdutoDto();
//	        produtoDto.setId(1);
//	        produtoDto.setDescricao("Produto Teste");
//
//	        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
//	        parceiroNegocio.setId(1);
//	        parceiroNegocio.setNome("Fabricante Teste");
//
//	        produtoDto.setFabricante(parceiroNegocio);
//
//	        Produto produto = new Produto();
//	        produto.setId(1);
//	        produto.setDescricao("Produto Teste");
//	        produto.setFabricante(parceiroNegocio);
//
//	        when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));
//	        when(parceiroNegocioRepository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);
//	        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
//
//	        ProdutoDto updatedProduto = produtoService.update(1, produtoDto);
//
//	        assertEquals(1, updatedProduto.getId());
//	        assertEquals("Produto Teste", updatedProduto.getDescricao());
//	    }
//	    
//}
	    
	    
	    
	    
	    
	    
	
	    

