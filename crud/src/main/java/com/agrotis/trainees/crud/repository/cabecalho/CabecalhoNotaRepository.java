package com.agrotis.trainees.crud.repository.cabecalho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;

@Repository
public class CabecalhoNotaRepository extends JpaRepositoryWrapper<
										  CabecalhoNota, 
										  Long> {

	@Autowired
	public CabecalhoNotaRepository(CabecalhoNotaJpaRepository repository) {
		super(repository, nomeLogger(CabecalhoNotaRepository.class));
	}
	
	public boolean existeInstanciaCom(TipoNota tipo, long numero) {
		LOG.info("Tentando encontrar {} com tipo {} e numero {}", 
				NOME_ENTITY, tipo, numero);
		CabecalhoNotaJpaRepository repository = (CabecalhoNotaJpaRepository) REPOSITORY;
		boolean encontrado = repository.existsByTipoAndNumero(tipo, numero);
		if (encontrado) {
			LOG.info("Encontrado {} com tipo {} e numero {}", 
					NOME_ENTITY, tipo, numero);
		} else {
			LOG.error("Nao encontrado {} com tipo {} e numero {}", 
					NOME_ENTITY, tipo, numero);
		}
		return encontrado;
	}
}
