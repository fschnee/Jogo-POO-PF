SHELL := /bin/bash
makedir = $(shell pwd)
projdir = ..# $(shell `cd .. && pwd`)
ghc_version = $(shell ghc --numeric-version)

.PHONY: all
all: test_all libs_standalones build

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

# Apaga os arquivos de compilação da pasta src
.PHONY: cleanup
cleanup:
	@rm -f */*_stub.h */*.o */*.hi Tests/test_1 Tests/*.h */*.so */*.out
	@find . -type f -name '*.class' -delete

.PHONY: libs_standalones
libs_standalones:
	@echo Compilando os módulos Haskell como executáveis
	@cd Haskell && ghc -fforce-recomp Blackjack.hs -o ../../bin/libs/Blackjack
	@echo

.PHONY: libs
libs:
	@# TODO: deixar mais enxuto
	@echo Compilando as libs para serem usadas em Java
	@ghc -fforce-recomp -DUSE_JAVA_BACKEND Haskell/Deck.hs Haskell/BitField.hs \
	Haskell/CommonIO.hs Haskell/Blackjack.hs -c \
	-hidir ../bin/auto_gen/Hs -odir ../bin/auto_gen/Hs -stubdir ../bin/auto_gen/Hs && \
	mv ../bin/auto_gen/Hs/Main_stub.h ../bin/auto_gen/Hs/Blackjack_stub.h && \
	mv ../bin/auto_gen/Hs/Main.hi ../bin/auto_gen/Hs/Blackjack.hi && \
	mv ../bin/auto_gen/Hs/Main.o ../bin/auto_gen/Hs/Blackjack.o
	@javac -classpath Java Java/cardgames/Blackjack.java -d ../bin/auto_gen/Java && \
	cd ../bin/auto_gen/Java && javah -jni cardgames.Blackjack
	@ghc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux \
	-I$(projdir)/bin/auto_gen/Hs -I$(projdir)/bin/auto_gen/Java -fforce-recomp -dynamic \
	-shared -lHSrts-ghc$(ghc_version) -DUSE_JAVA_BACKEND C/Blackjack.c Haskell/Blackjack.hs \
	Haskell/Deck.hs Haskell/BitField.hs Haskell/CommonIO.hs -o ../bin/libBlackjack.so


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
build: libs
	@java -Djava.library.path="../bin" \
	-cp ../bin/auto_gen/Java cardgames.Blackjack
