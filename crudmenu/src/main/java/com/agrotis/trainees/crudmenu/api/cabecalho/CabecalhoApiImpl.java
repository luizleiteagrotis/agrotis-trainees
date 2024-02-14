package com.agrotis.trainees.crudmenu.api.cabecalho;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoRetornoDto;

@Component
public class CabecalhoApiImpl implements CabecalhoApi {

	private final String URL = "http://localhost:8081/crud/api/notas-fiscais/cabecalhos";
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public CabecalhoRetornoDto cadastrar(CabecalhoCadastroDto cadastroDto) {
		ResponseEntity<Void> response = restTemplate.postForEntity(URL, cadastroDto, Void.class);
		HttpHeaders headers = response.getHeaders();
		String location = headers.getFirst(HttpHeaders.LOCATION);
		return restTemplate.getForObject(location, CabecalhoRetornoDto.class);
	}

	@Override
	public CabecalhoRetornoDto buscarPor(Long id) {
		String url = URL + "/" + id;
		return restTemplate.getForObject(url, CabecalhoRetornoDto.class);
	}

}
