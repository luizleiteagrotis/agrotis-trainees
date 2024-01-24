# Modelagem CRUD Trainees

TODO - Melhorar este arquivo com as orientações gerais, e deixar a quebra das atividades para fazermos direto no JIRA, ou seja, aqui terá apenas orientações mais técnicas e exemplos de código, no JIRA teremos as regras de negócio que precisam ser implementadas...

- Tipo de Nota Fiscal: ID, Nome (TODO Entidade Modelo sem BaseEntity e com .sql de exemplo, demais tabelas não vamos dar o SQL, vamos pedir pros trainees modelarem, para avaliarmos a parte analítica deles, que estão se formando em Análise e Desenvolvimento)
```
package br.com.agrotis.erp.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.agrotis.sb.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "erp_bandeira_cartao")
@Data
@EqualsAndHashCode(callSuper = false)
public class BandeiraCartao extends BaseEntity {

    private static final long serialVersionUID = -1683867047730924773L;
    private Boolean ativo;
    private String nome;
    private String codigoErp;
}package br.com.agrotis.erp.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.agrotis.sb.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "erp_bandeira_cartao")
@Data
@EqualsAndHashCode(callSuper = false)
public class BandeiraCartao extends BaseEntity {

    private static final long serialVersionUID = -1683867047730924773L;
    private Boolean ativo;
    private String nome;
    private String codigoErp;
}
```



- Parceiro de Negócio: ID, Nome, Inscrição Fiscal
- Produto: ID, Descrição (Estoque, Custo Médio, Custo de Última Compra)
- Nota Fiscal: 
-- Cabeçalho: ID, Número, ID do Parceiro, Data, Tipo, Valor Total
-- Itens: ID, ID do Produto, Quantidade, Preço Unitário, Valor Total

0ª Instalar SQL Server Developer:

Regras:
- Modelar em arquivo .sql dentro do projeto (TODO qual pasta?)
- 

1ª Cadastros Básicos
- Parceiro
- Produto (sem estoque)

2ª Nota Fiscal
- Cabeçalho 
- Itens

3º Controle de estoque

4º Custo Médio

5º Custo de Última compra

6º Refatoração - Entidade Tipo de Nota Fiscal vira enum (EnumTipoNotaFiscal - ENTRADA, SAIDA)