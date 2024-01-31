package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioTipoRepository;

@Service
public class ParceiroNegocioTipoService {

		private static final Logger LOG = LoggerFactory
				.getLogger(ParceiroNegocioTipoService.class);
		
		private final ParceiroNegocioTipoRepository repository;

		public ParceiroNegocioTipoService(ParceiroNegocioTipoRepository repository) {
			super();
			this.repository = repository;
		}

		public ParceiroNegocio salvar(ParceiroNegocio entidade) {
			return repository.save(entidade);
		}

}