package com.agrotis.trainees.crudmenu.api.produto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.agrotis.trainees.crudmenu.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.produto.ProdutoRetornoDto;

@Component
public class ProdutoApiImpl implements ProdutoApi {

	private final String URL = "http://localhost:8081/crud/api/produtos";
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public ProdutoRetornoDto cadastrar(ProdutoCadastroDto cadastroDto) {
		ResponseEntity<Void> response = restTemplate.postForEntity(URL, cadastroDto, Void.class);
		HttpHeaders headers = response.getHeaders();
		String location = headers.getFirst(HttpHeaders.LOCATION);
		return restTemplate.getForObject(location, ProdutoRetornoDto.class);
	}

	
}
