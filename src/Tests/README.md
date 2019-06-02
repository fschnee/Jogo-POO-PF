Essa pasta contém testes para ver se é possivel chamar funções de Haskell em
Java.
A ideia basica é criar uma biblioteca dinâmica que poderá ser chamada em Java
contendo as funções em Haskell e suas binds em C para que possam ser chamadas.

obs: Para rodar tudo é só voltar para a pasta `src` e chamar `make test_binds`
no terminal de sua escolha.

obs2: Atente para a versão do GHC especificada no makefile, ela é usada para
compilar a bibioteca com o codigo misto, se especificar a versão errada da erro.

## Teste 1
Testa se é possivel criar um programa em C que chama uma função em Haskell.

Para chamar Haskell em C primeiro é necessário escrever o codigo em Haskell com
a Foreign Function Interface (FFI), algo que pode ser visto em
`HaskellTestFunctions.h`, fazendo um `foreign export ccall` com as funções
que devem ser exportadas. Isso é usado pelo GHC para criar arquivos `*_stub.h`
contendo as binds para fazer a chamada em C atraves do comando `ghc
-c NomeDoArquivo.hs`, que também compila o codigo Haskell para uma biblioteca
estática para ser linkado no executavel final. Após esse processo a compilação
deve ser feita com o GHC e **NÃO** com o GCC, visto que ainda é necessário linkar as
bibliotecas de runtime do Haskell.

No final de tudo isso é gerado um executável e, por isso, o resto dos arquivos
podem ser deletados.

## Teste 2
Testa se é possivel criar uma classe em Java que chama uma biblioteca com codigo
de Haskell e C misturados.

No arquivo `test2_binds.c` é possivel ver tanto a FFI do Haskell, que foi
discutida no teste anterior quanto um header `JavaHaskellTest.h`, que não está
presente na pasta. Isso acontece porque ele é um arquivo gerado automaticamente
pelo comando `javah`, que é usado para criar binds Java <---> C. Fora isso as
outras coisas interessantes à ser observar sobre esse arquivo é que ele usa o
header `jni.h` para funcionar com o java e a regra do makefile é mais
complicada, pois precisa fazer uma biblioteca unica com o codigo em Haskell e C.

Vale ressaltar que no final desse processo nenhum arquivo fora a biblioteca
gerada é necessário e, portanto, podem ser deletados posteriormente.
