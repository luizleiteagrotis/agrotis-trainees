# CRUD

CRUD é abreviação para os termos em inglês: Create, Read, Update e Delete.
Esse termo se popularizou para se referir a camada de persisência em banco de dados.
Quando ouvimos "é preciso criar um CRUD", entendemos que precisamos criar no mínimo uma...
- Tabela (arquivo .sql com o CREATE TABLE...)
- Classe Entidade (Entity com todos os campos da tabela)
- Classe DAO (Repository com todos os métodos de CRUD a nível de Banco)
- Classe RN (Service com todos os métodos de CRUD a nível de Regra de Negócio)


## Instalando SQL Server Local com Docker
Siga [este tutorial](https://portalagrotis.com/des-plat/sql-server-local/)

## Para cada issue
### Modelar tabela (arquivo nome_tabela.sql em /src/main/resources/sql)
### Criar Entity
### Criar Repository
### Criar Service
### Incluir execução ao final do main()
### Garantir que esteja Criando, Lendo, Atualizando e Deletando registros na tabela.

TODO - Melhorar este arquivo com as orientações gerais, e deixar a quebra das atividades para fazermos direto no JIRA, ou seja, aqui terá apenas orientações mais técnicas e exemplos de código, no JIRA teremos as regras de negócio que precisam ser implementadas...

- Tipo de Nota Fiscal: ID, Nome (TODO Entidade Modelo sem BaseEntity e com .sql de exemplo, demais tabelas não vamos dar o SQL, vamos pedir pros trainees modelarem, para avaliarmos a parte analítica deles, que estão se formando em Análise e Desenvolvimento)
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

6º Formação de Preço

7º Refatoração - Entidade Tipo de Nota Fiscal vira enum (EnumTipoNotaFiscal - ENTRADA, SAIDA)

8º Exceptions ao invés de Logs

9º Scanner para interação com o usuário no terminal escolhendo as ações a serem feitas