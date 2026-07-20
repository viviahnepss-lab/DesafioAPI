#DESAFIO RestAssured 
viviahnepss-project

## Objetivo do Projeto:
Este projeto visa demonstrar o Skill técnico do candidato em automação de teste para API e implementar melhorias no projeto.

## Documentação das APIS: 
https://sicredi-desafio-qe.readme.io/reference/home

## Pré - Requisitos:

Para execução deste projeto é necessário ter as seguintes ferramentas e confurações:
* IntelliJ IDEA 2021.2.3 (Community Edition) ou superior
* JAVA SDK 17 

## Tecnologias Utilizadas:
* Rest Assured 
* Junit 4

## Repositório do projeto :
[GITLAB](https://gitlab.com/viviahnepss-group/viviahnepss-project/-/tree/main?ref_type=heads)

## Estrutura do projeto:
Projeto foi dividido em 03 entidades Usuários,Login e Produtos, para cada pacote ha uma classe com esse nome com a sua respectiva função

src/test/java/Model - Classes Bean do projeto 
src/test/java/Runners- Classes de execução em lote de teste por Categoria ou Funcionalidade.
src/test/java/Service- Classe de envio de request e response
src/test/java/Test - Classes com a lógica do passo a passo ara execução dos testes  da funcionalidade
src/test/java/Utils - Classe de suporte metodos do projeto.

## Como executar o projeto:

E possivel executar teste no projeto de duas formas Execução de um teste especifico um roteiro completo de testes, sendo este ultimo apenas via linha de comando.
Abaixo segue passo a passo para execução: 

   ##  Execução de um teste especifico:
     2. Acesse o pacote [Steps](src/test/java/Step);
     2.1 Abra a classe que voce deseja executar (Login,Produtos ou Usuario);
     2.2. Escolha o cenário desejado e clique em RUN ou na seta verde ao lado do nome do metodoo do teste.

  ## * Execução do roteiro completo de testes
    3. Acesse o pacote [Runners](src/test/java/Runners);
    3.1 Abra a classe Runner;
    3.2 Escolha o tipo que deseja executar por CATEGORIA ou FUNCIONALIDADE
    3.3 Caso deseje rodar por categoria: coloque no parametro @Categories.IncludeCategory(SuaCategoria.class)
    3.3 Caso deseje rodar por categoria: coloque no parametro @@Suite.SuiteClasses({(SuaCategoria.class})


## Plano de Testes:
Para este projeto foi definido o plano de testes funcionais abaixo,.

* CTU001 - Validar busca pelo dados do usuario com sucesso
* CTU002 - Validar busca pelo dados do usuario por ID com sucesso
* CTU003 - Validar busca pelo dados do usuario por ID inexistente
* CTU004 - Validar busca pelo dados do usuario por ID inválido
* CTL005 - Efetuar login com usuario com sucesso
* CTL006 - Efetuar login com usuario inexistente
* CTL007 - Efetuar login com usuario invalido
* CTL008 - Efetuar login com senha invalida
* CTL009 - Validar adição de produto com sucesso
* CTP010 - Validar busca de produto
* CTP011 - Validar busca de produto por ID válido
* CTP012 - Validar busca de produto por ID invalido
* CTP013 - Validar busca de produto por ID inexistente
* CTP014 - Validar busca de produto com autorização com sucesso

## O que está fora do escopo
N"ao foram desenvolvidos features cucumber para este projeto.

## Pontos de melhoria identificados
 - Inserção de logs para acompanhamento do andamento dos testes.
 - Implementação do report de teste.
 - Inserir Cucumber 
 - Validaçoes com hamcrest
## BUG:


***

