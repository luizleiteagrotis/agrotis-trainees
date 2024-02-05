package com.agrotis.trainees.crud.repository.notafiscal.tipo;

import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@Service
public class TipoNotaRepository extends JpaRepositoryWrapper<
										NotaFiscalTipo, 
										Integer> {

	public TipoNotaRepository(NotaFiscalTipoJpaRepository repository) {
		super(repository, nomeLogger(TipoNotaRepository.class));
	}
}
