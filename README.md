## OLX_CostDistanceREST

## Motivação

Projeto criado para prover serviços REST, onde três serviços são oferecidos:
	Registrar palavras;
	Buscá-las e
	Encontrar as palavras, cujos movimentos mínimos as transformam no termo passado por parâmetro.
	
Ou seja, dados uma palavra e um número, serão retornadas as  palavras onde, fazendo movimentos mínimos menores ou iguais a esse número, podem ser transformadas na palavra em questão.
 
Esses movimentos são de inserção, exclusão ou troca de caracteres.
Caso o número mínimo de movimentos não seja passado, será considerada a distância mínima igual a 3.
Para isso é utilizado o algoritmo da distância mínima de Levenshtein.

## Linguagem

A linguagem utilizada é Java.

## Serviço

Foram implementados três serviços REST que podem ser chamados via navegador da seguinte maneira:

* `http://endereco/OLXCostDistanceRest/challenge4/getallwords`
Busca todas as palavras cadastradas no banco. Resposta em JSON.

* `http://endereco/OLXCostDistanceRest/challenge4/insertword/[termoASerCadastrado]`
Insere a palavra a ser cadastrada no banco. Onde termoASerCadastrado é a palavra a ser inserida no banco, ou seja, será o parâmetro.

* `http://endereco/OLXCostDistanceRest/challenge4/getmindistance?name=[termo]&threshold=[numero]`
Busca todas as palavras com distância mínima menor ou igual ao número passado por parâmetro.
Onde termo é a palavra em questão e número é o mínimo de movimentos que se deve fazer para que as palavras no banco se transformem no termo.

## Compilação

Para facilitar a importação de bibliotecas e a compilação dos arquivos em um único pacote, foi utilizado Maven.
Para compilar gerando o pacote basta executar o comando abaixo na linha de comando.

```mvn -DskipTests compile package```

Na pasta target serão gerados vários arquivos, mas o pacote principal é gerado com o nome `OLXCostDistanceRest-1.0.0-SNAPSHOT.war`

## Banco de dados

O banco de dados usado foi o PostgreSQL.
A criação do banco está no arquivo CREATEDB.


##Testes

Para os testes foram utilizadas as bibliotecas TestNG.
Para executar os testes basta escrever na linha de comando:

 ```mvn test```


## Execução

O container utilizado para execução do projeto foi o Tomcat 8.0 com a configuração padrão.
