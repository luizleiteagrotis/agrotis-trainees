package com.agrotis.trainees.crud.repository.notafiscal.cabecalho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@Repository
public class CabecalhoNotaRepository extends JpaRepositoryWrapper<
										  NotaFiscalService, 
										  CabecalhoNota, 
										  Long, 
										  CabecalhoNotaJpaRepository> {

	@Autowired
	public CabecalhoNotaRepository(CabecalhoNotaJpaRepository repository, 
			Class<CabecalhoNota> entity,
			Class<NotaFiscalService> service) {
		super(repository, entity, service);
	}
}
