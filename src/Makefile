SHELL := /bin/bash
makedir = $(shell pwd)
ghc_version = $(shell ghc --numeric-version)

.PHONY: all
all: test_all libs_all build

.PHONY: test_all
test_all: test_1 test_2 cleanup

# Testa se C consegue chamar Haskell
.PHONY: test_1
test_1:
	@echo Teste 1 -- chamando Haskell a partir de C: && \
	cd Tests && \
	ghc -fforce-recomp -c HaskellTestFunctions.hs && \
	ghc -fforce-recomp -no-hs-main test1_binds.c HaskellTestFunctions -o test_1 && \
	./test_1
	@echo

# Testa se Java consegue chamar C (que está chamando Haskell)
.PHONY: test_2
test_2:
	@echo Teste 2 -- chamando Haskell a partir de Java usando C como ponte: && \
	cd Tests && \
	javac JavaHaskellTest.java && \
	javah -jni JavaHaskellTest && \
	ghc -fforce-recomp -c HaskellTestFunctions.hs && \
	ghc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -fforce-recomp -dynamic -shared -lHSrts-ghc$(ghc_version) test2_binds.c HaskellTestFunctions.hs -o libHaskellBinds.so && \
	java -Djava.library.path="$(makedir)/Tests" JavaHaskellTest
	@echo

# Apaga os arquivos dos Testes
.PHONY: cleanup
cleanup:
	@rm -f */*_stub.h */*.o */*.hi */*.class Tests/test_1 Tests/*.h \
	*/*.so */*.out

.PHONY: libs_standalones
libs_standalones:
	@echo Compilando os módulos Haskell como executáveis
	cd Haskell && \
	ghc -fforce-recomp Blackjack.hs -o ../../bin/libs/bin/Blackjack
	@echo

.PHONY: libs
libs:

.PHONY: libs_all
libs_all: libs_standalones libs

# Apaga tudo menos os README e .keep de ../bin/
.PHONY: clean
clean:
	@cd ../bin/ && \
	find . -type f -not -name README.md -not -name .keep -delete

.PHONY: cleanall
cleanall: cleanup clean


.PHONY: build
build:
