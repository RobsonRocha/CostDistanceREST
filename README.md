## OLX_CostDistanceREST

## Motiva��o

Projeto criado para prover servi�os REST, onde tr�s servi�os s�o oferecidos:
	Registrar palavras;
	Busc�-las e
	Encontrar as palavras, cujos movimentos m�nimos as transformam no termo passado por par�metro.
	
Ou seja, dados uma palavra e um n�mero, ser�o retornadas as  palavras onde, fazendo movimentos m�nimos menores ou iguais a esse n�mero, podem ser transformadas na palavra em quest�o.
 
Esses movimentos s�o de inser��o, exclus�o ou troca de caracteres.
Caso o n�mero m�nimo de movimentos n�o seja passado, ser� considerada a dist�ncia m�nima igual a 3.
Para isso � utilizado o algoritmo da dist�ncia m�nima de Levenshtein.

## Linguagem

A linguagem utilizada � Java.

## Servi�o

Foram implementados tr�s servi�os REST que podem ser chamados via navegador da seguinte maneira:

* `http://endereco/OLXCostDistanceRest/challenge4/getallwords`
Busca todas as palavras cadastradas no banco. Resposta em JSON.

* `http://endereco/OLXCostDistanceRest/challenge4/insertword/[termoASerCadastrado]`
Insere a palavra a ser cadastrada no banco. Onde termoASerCadastrado � a palavra a ser inserida no banco, ou seja, ser� o par�metro.

* `http://endereco/OLXCostDistanceRest/challenge4/getmindistance?name=[termo]&threshold=[numero]`
Busca todas as palavras com dist�ncia m�nima menor ou igual ao n�mero passado por par�metro.
Onde termo � a palavra em quest�o e n�mero � o m�nimo de movimentos que se deve fazer para que as palavras no banco se transformem no termo.

## Compila��o

Para facilitar a importa��o de bibliotecas e a compila��o dos arquivos em um �nico pacote, foi utilizado Maven.
Para compilar gerando o pacote basta executar o comando abaixo na linha de comando.

```mvn -DskipTests compile package```

Na pasta target ser�o gerados v�rios arquivos, mas o pacote principal � gerado com o nome `OLXCostDistanceRest-1.0.0-SNAPSHOT.war`

## Banco de dados

O banco de dados usado foi o PostgreSQL.
A cria��o do banco est� no arquivo CREATEDB.


##Testes

Para os testes foram utilizadas as bibliotecas TestNG.
Para executar os testes basta escrever na linha de comando:

 ```mvn test```


## Execu��o

O container utilizado para execu��o do projeto foi o Tomcat 8.0 com a configura��o padr�o.
