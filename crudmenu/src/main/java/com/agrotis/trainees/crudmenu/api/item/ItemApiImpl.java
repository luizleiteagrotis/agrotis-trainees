package com.agrotis.trainees.crudmenu.api.item;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.agrotis.trainees.crudmenu.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crudmenu.dto.item.ItemRetornoDto;

@Component
public class ItemApiImpl implements ItemApi {

	private final String URL = "http://localhost:8081/crud/api/notas-fiscais/itens";
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public ItemRetornoDto cadastrar(ItemCadastroDto cadastroDto) {
		ResponseEntity<Void> response = restTemplate.postForEntity(URL, cadastroDto, Void.class);
		HttpHeaders headers = response.getHeaders();
		String location = headers.getFirst(HttpHeaders.LOCATION);
		return restTemplate.getForObject(location, ItemRetornoDto.class);
	}

}
