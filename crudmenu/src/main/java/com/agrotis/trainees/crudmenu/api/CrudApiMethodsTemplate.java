package com.agrotis.trainees.crudmenu.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.agrotis.trainees.crudmenu.exception.ApiMethodException;
import com.agrotis.trainees.crudmenu.exception.BadRequestException;
import com.agrotis.trainees.crudmenu.exception.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crudmenu.exception.RecursoNaoEncontradoException;

public abstract class CrudApiMethodsTemplate<CadastroDto, RetornoDto> implements CrudApiMethods<CadastroDto, RetornoDto> {
	
	private final RestTemplate REST_TEMPLATE;
	
	public CrudApiMethodsTemplate() {
		REST_TEMPLATE = new RestTemplate();
	}

	@Override
	public RetornoDto cadastrar(CadastroDto cadastroDto) throws ApiMethodException {
		try {
			ResponseEntity<String> response = REST_TEMPLATE.postForEntity(getUrl(), cadastroDto, String.class);
			HttpHeaders headers = response.getHeaders();
			String location = headers.getFirst(HttpHeaders.LOCATION);
			return REST_TEMPLATE.getForObject(location, getRetornoDtoClass());
		} catch (HttpClientErrorException.BadRequest e) {
			throw new BadRequestException(e.getMessage());
		} catch (ResourceAccessException e) {
			throw new RecursoNaoEncontradoException("Verifique se a API esta online");
		} catch (RestClientException e) {
			throw new ApiMethodException(e.getMessage());
		}
	}
	
	@Override
	public RetornoDto buscarPor(Long id) throws ApiMethodException {
		String idUrl = getUrl() + "/" + id;
		try {
			ResponseEntity<RetornoDto> response = REST_TEMPLATE.getForEntity(idUrl, getRetornoDtoClass(), getRetornoDtoClass());
			return response.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new EntidadeNaoEncontradaException("Entidade com id " + id + " nao existe");
		} catch (ResourceAccessException e) {
			throw new RecursoNaoEncontradoException("Verifique se a API esta online");
		} catch (RestClientException e) {
			throw new ApiMethodException(e.getMessage());
		}
	}
	
	protected abstract String getUrl();
	
	protected abstract Class<RetornoDto> getRetornoDtoClass();
}
