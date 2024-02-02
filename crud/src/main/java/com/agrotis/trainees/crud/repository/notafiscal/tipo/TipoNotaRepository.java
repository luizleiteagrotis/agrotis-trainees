package com.agrotis.trainees.crud.repository.notafiscal.tipo;

import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@Service
public class TipoNotaRepository extends JpaRepositoryWrapper<
										NotaFiscalService, 
										NotaFiscalTipo, 
										Integer, 
										NotaFiscalTipoJpaRepository> {

	public TipoNotaRepository(NotaFiscalTipoJpaRepository repository, 
			Class<NotaFiscalTipo> entity,
			Class<NotaFiscalService> service) {
		super(repository, entity, service);
	}
}
