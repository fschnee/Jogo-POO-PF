SHELL := /bin/bash
# Botar o caminho para jni.h aqui
java_includes = /home/fschnee/Programs/Java/jdk1.8.0_111/include
makedir = $(shell pwd)
# Mude isso de acordo com a sua versão
ghc_version = 8.0.1

all: test_binds build

.PHONY: test_binds
test_binds: test_1 test_2 cleanup

# Testa se C consegue chamar Haskell
.PHONY: test_1
test_1:
	cd Tests && \
	ghc -fforce-recomp -c -O HaskellTestFunctions.hs && \
	ghc -fforce-recomp --make -no-hs-main -optc-O test1_binds.c HaskellTestFunctions -o test_1 && \
	echo Rodando teste 1 -- chamando Haskell a partir de C: && \
	./test_1

# Testa se Java consegue chamar C (que está chamando Haskell)
.PHONY: test_2
test_2:
	cd Tests && \
	javac JavaHaskellTest.java && \
	javah -jni JavaHaskellTest && \
	ghc -fforce-recomp -c -O HaskellTestFunctions.hs && \
	ghc -I$(java_includes) -I$(java_includes)/linux -fforce-recomp -dynamic -shared -fPIC -lHSrts-ghc$(ghc_version)\
		test2_binds.c HaskellTestFunctions.hs -o libHaskellBinds.so && \
	echo Rodando teste2 -- chamando Haskell a partir de Java usando C como ponte: && \
	java -Djava.library.path="$(makedir)/Tests" JavaHaskellTest

# Apaga os arquivos dos Testes
.PHONY: cleanup
cleanup:
	rm -f */*_stub.h */*.o */*.hi */*.class Tests/test_1 Tests/*.h \
	*/*.so */*.out

# Apaga tudo menos o README de ../bin/
.PHONY: clean
clean:
	cd ../bin/ && \
	find . ! -name README.md -delete

.PHONY: cleanall
cleanall: cleanup clean


.PHONY: build
build:
