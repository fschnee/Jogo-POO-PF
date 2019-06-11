# Jogo-POO-PF
Repositorio criado para o desenvolvimento do jogo do terceiro semetre (2019-1)
para as disciplinas POO (Programação orientada à objetos) e PF (Programação funcional).

## Explicação
O projeto é basicamente uma união entre o trabalho final da disciplina de POO e
de PF, sendo que em cada uma é necessário implementar um jogo. Para isso foi
criada uma estrutura para unir as duas linguagens (Java e Haskell) por meio de
uma ponte feita em C. Toda lógica do jogo será implementada em Java enquanto
a lógica dos jogos de carta (Blackjack, etc) será implementada em Haskell.

Um fato importante de ressaltar é que a lógica do jogo em si não influencia
diretamente a lógica dos jogos de carta, fazendo assim que o programa em Haskell
possa ser compilado separadamente e rodado usando a função main do respectivo
jogo.

## Organização
Informações detalhadas sobre as pastas do projeto estão inclusas nas próprias
porém podem ser definidas brevemente da seguinte maneira:

1. src:
  Contém o codigo fonte e arquivos de build.

2. assets:
Contém todas as imagens e qualquer tipo de arquivo necessário ao funcionamento
do projeto que não seja codigo fonte

3. bin:
Contém os arquivos finais (resultado da compilação)

## Dependências
Seguem as dependências do projeto (apenas as versões testadas)
###### Haskell
  * GHC (== 8.0.1)

###### Java
  * openJDK (== 1.8.0_181)

###### C
  * GCC (== 6.3.0) -> Usado pelo GHC

## Intruções de compilação
Para compilar somente o necessário para rodar o projeto basta ir à pasta `src`
e rodar o comando `make run`, ja para compilar tudo (`make run` + testes +
bibliotecas Haskell como executáveis) é necessário rodar o comando `make all` e
para rodar sem recompilar use `make justrun`
