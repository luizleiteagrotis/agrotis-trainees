package com.agrotis.trainees.crud.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@RunWith(MockitoJUnitRunner.class)
public class NotaFiscalItemServiceTest {

    @Mock
    private NotaFiscalItemRepository repository;

    @InjectMocks
    private NotaFiscalItemService service;

}
