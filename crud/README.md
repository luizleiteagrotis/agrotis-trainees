# Aprendendo CRUD na prática

CRUD é abreviação para os termos em inglês: **C**reate, **R**ead, **U**pdate e **D**elete.  
Esse termo se popularizou para se referir a camada de persisência em banco de dados.  
Quando ouvimos "é preciso criar um CRUD", entendemos que precisamos criar no mínimo uma...
- Tabela (arquivo .sql com o CREATE TABLE...)
- Classe Entidade (Entity com todos os campos da tabela)
- Classe DAO¹ (Repository com todos os métodos de CRUD a nível de Banco)
- Classe RN² (Service com todos os métodos de CRUD a nível de Regra de Negócio)

¹DAO = Data Access Object  
²RN = Regra de Negócio

## Objetivo

O objetivo do projeto crud aqui proposto é dar um exemplo prático de CRUD para servir de base para a construção de um sistema de controle de estoque.  
Este sistema deverá fazer as movimentações de estoque por meio de Notas Fiscais de Entrada e Saída.
Abaixo temos as orientações técnicas básicas, já que toda regra de negócio estará documentada apenas nas issues do JIRA.

## Instalando SQL Server Local com Docker
O primeiro passo é garantir que tenha feito [este tutorial](https://portalagrotis.com/des-plat/sql-server-local/) para podermos criar a estrutura de tabelas localmente para persistência do nosso sistema.

## Passos esperados para cada issue do Jira

1. Baixar branch crud (git fetch && git checkout crud && git pull)
2. Criar branch crud-seu-nome (git checkout -b crud-seu-nome)
3. Ler todas as informações presentes na issue (issue pai, título, descrição, anexos, comentários, links externos, etc)
4. Num comentário, descrever o seu entendimento ou as dúvidas a serem levantadas, marcando outras pessoas se necessário
5. Num novo comentário, iniciar sua análise, modelagem e estruturação da solução, isso envolve:
   * Modelar a tabela
   * Estudar quais classes serão necessárias (usando tabela nota_fiscal_tipo como exemplo)
   * Estimar um tempo em horas para o desenvolvimento desta solução
6. Após sua proposta de solução documentada, pedir a revisão de um colega
7. Uma vez revisado isso, ai sim pode partir para o desenvolvimento do código
8. Organizar o código da seguinte forma:
   * Comandos SQL devem ser criados em /src/main/resources/sql
   * Entidades em /src/main/java/com/agrotis/trainees/crud/entity
   * DAO em /src/main/java/com/agrotis/trainees/crud/repository
   * RN em /src/main/java/com/agrotis/trainees/crud/service
9. Após implementar um método CRUD
   * Testar via método main()
   * Dando sucesso, efetuar commit seguindo [o nosso padrão](https://portalagrotis.com/des-plat/des-plat/doc/padroes-codigo/padrao-msg-commit/)
   * Partir para o próprio método e repetir esse processo
10. Ao final queremos no mínimo um commit para cada operação, ex:
   * Criação
   * Leitura
   * Atualização
   * Deleção
