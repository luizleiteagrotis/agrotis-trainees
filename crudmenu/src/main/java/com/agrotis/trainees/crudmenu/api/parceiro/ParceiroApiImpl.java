package com.agrotis.trainees.crudmenu.api.parceiro;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;

@Component
public class ParceiroApiImpl implements ParceiroApi {

	private final String URL = "http://localhost:8081/crud/api/parceiros";
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public ParceiroRetornoDto cadastrar(ParceiroCadastroDto cadastroDto) {
		ResponseEntity<Void> response = restTemplate.postForEntity(URL, cadastroDto, Void.class);
		HttpHeaders headers = response.getHeaders();
		String location = headers.getFirst(HttpHeaders.LOCATION);
		return restTemplate.getForObject(location, ParceiroRetornoDto.class);
	}
}
