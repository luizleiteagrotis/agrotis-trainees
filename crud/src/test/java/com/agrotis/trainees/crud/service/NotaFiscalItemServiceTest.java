package com.agrotis.trainees.crud.service;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class NotaFiscalItemServiceTest {
	
	    @Mock
	    private ItemNotaFiscalRepository repository;

	    @Mock
	    private ProdutoRepository produtoRepository;

	    @Mock
	    private NotaFiscalRepository notaFiscalRepository;

	    @InjectMocks
	    private ItemNotaFiscalService service;
	    
  
	   

}
