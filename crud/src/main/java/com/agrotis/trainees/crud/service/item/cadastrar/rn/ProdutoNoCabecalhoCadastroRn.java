package com.agrotis.trainees.crud.service.item.cadastrar.rn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRnException;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRn;

@Component
public class ProdutoNoCabecalhoCadastroRn implements ItemCadastroRn{

	private ItemNotaRepository itemRepository;
	
	@Autowired
	public ProdutoNoCabecalhoCadastroRn(ItemNotaRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public ItemNota operarSobre(ItemNota item) {
		Produto produto = item.getProduto();
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		if (itemRepository.existeInstanciaCom(produto, cabecalho)) {
			StringBuilder mensagemErro = new StringBuilder();
			mensagemErro.append("Ja existe item com idProduto: ");
			mensagemErro.append(produto.getId());
			mensagemErro.append(" e idCabecalho: ");
			mensagemErro.append(cabecalho.getId());
			throw new ItemCadastroRnException(mensagemErro.toString());
		}
		return item;
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
