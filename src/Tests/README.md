Essa pasta contém testes para ver se é possivel chamar funções de Haskell em
Java.
A ideia basica é criar uma biblioteca dinâmica que poderá ser chamada em Java
contendo as funções em Haskell e suas binds em C para que possam ser chamadas.

obs: Para rodar tudo é só voltar para a pasta `src` e chamar `make test_all`
no terminal de sua escolha.

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

O algoritmo final fica o seguinte:
1. Gerar headers Haskell <-> C com `ghc -c moduloHaskell.hs` (tem que ser
escrito com a FFI para gerar os header)
3. Escrever classe em Java com a interface nativa.
4. Gerar headers Java <-> C com `javac ClasseJava.java` e depois `javah -jni
ClasseJava`
5. Escrever o código em C para colar tudo, importanto os headers gerados, alem
de `jni.h` e `HsFFI.h`, lembrando de inicializar e fechar quando não mais
necessário o runtime do Haskell.
6. Compilar o arquivo C com o GHC incluindo o diretório que contém `jni.h`,
usando o comando `ghc -I$(DIRETORIO_DO_JNI.H) -dynamic -shared
-lHSrts-ghc$(SUA_VERSAO_DO_GHC_AQUI) arquivoEmC.c moduloHaskell.hs -o
$(NOME_DA_BIBLIOTECA_FINAL)`
7. Rodar o programa java com `java
-Djava.library.path=$(LOCAL_DA_BIBLIOTECA_FINAL) ClasseJava`
