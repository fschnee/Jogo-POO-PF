SHELL := /bin/bash
projdir := $(shell cd .. && pwd)
ghc_version := $(shell ghc --numeric-version)
ghc_includes := -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux \
	-I$(projdir)/bin/auto_gen/Hs -I$(projdir)/bin/auto_gen/Java
mainclass := proj.jogo.Main

.PHONY: all
all: test_all libs_standalones run



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
	ghc $(ghc_includes) test2_binds.c HaskellTestFunctions.hs -fforce-recomp \
		-dynamic -shared -lHSrts-ghc$(ghc_version) -o libHaskellBinds.so && \
	java -Djava.library.path="$(projdir)/src/Tests" JavaHaskellTest
	@echo

.PHONY: test_all
test_all: test_1 test_2



.PHONY: libs_standalones
libs_standalones:
	@echo Compilando os módulos Haskell como executáveis
	@cd Haskell && ghc -fforce-recomp Blackjack.hs -o $(projdir)/bin/libs/Blackjack
	@echo

.PHONY: libs
libs:
	@echo Compilando as libs para serem usadas em Java

	@echo Passo 1: Compilando headers dos *.hs
	@cd Haskell && ghc -fforce-recomp --make -DUSE_JAVA_BACKEND Blackjack.hs -c \
		-hidir $(projdir)/bin/auto_gen/Hs \
		-odir $(projdir)/bin/auto_gen/Hs \
		-stubdir$(projdir)/bin/auto_gen/Hs && \
	cd $(projdir)/bin/auto_gen/Hs && \
	mv Main_stub.h Blackjack_stub.h && \
	mv Main.hi Blackjack.hi && \
	mv Main.o Blackjack.o

	@echo Passo 2: Compilando headers dos cardgames/*.java
	@javac -classpath Java Java/proj/jogo/Main.java -d $(projdir)/bin/auto_gen/Java && \
	cd $(projdir)/bin/auto_gen/Java && javah -jni proj.cardgames.Blackjack

	@echo Passo 3: Compilando libBlackjack.so
	@cd Haskell && \
	ghc $(ghc_includes) Blackjack.hs ../C/Blackjack.c -fforce-recomp \
		-DUSE_JAVA_BACKEND -dynamic -shared -lHSrts-ghc$(ghc_version) \
		-o $(projdir)/bin/libBlackjack.so
	@echo

.PHONY: libs_all
libs_all: libs_standalones libs



# Apaga os arquivos de compilação da pasta src
.PHONY: cleanup
cleanup:
	@rm -f */*_stub.h */*.o */*.hi Tests/test_1 Tests/*.h */*.so */*.out *.log core
	@find . -type f -name '*.class' -delete

# Apaga tudo menos os README e .keep de ../bin/
.PHONY: clean
clean:
	@cd $(projdir)/bin/ && \
	find . -type f -not -name README.md -not -name .keep -delete

# Limpa tudo que não é arquivo de projeto
.PHONY: purge
purge: cleanup clean



.PHONY: buildall
buildall: libs build

.PHONY: build
build:
	@echo Construindo o jar
	@javac -classpath Java Java/proj/jogo/Main.java \
		-d $(projdir)/bin/auto_gen/Java && \
	cd $(projdir)/bin/auto_gen/Java && \
	jar cvfe $(projdir)/bin/Jogo.jar $(mainclass) \
		$(subst $(projdir)/bin/auto_gen/Java/,,$(shell find $(projdir)/bin/auto_gen/Java -name '*.class')) && \
	cd $(projdir) && \
	jar -uf $(projdir)/bin/Jogo.jar \
		$(subst $(projdir)/,,$(shell find $(projdir)/assets -type f -not -name README.md))
	@echo

.PHONY: justrun
justrun:
	@echo Rodando o projeto com o comando: \
	java -jar -Djava.library.path='$(projdir)/bin' $(projdir)/bin/Jogo.jar
	@java -jar -Djava.library.path='$(projdir)/bin' $(projdir)/bin/Jogo.jar

.PHONY: run
run: buildall justrun

.PHONY: images
images:
	@cd Haxe && \
	haxe -neko Main.n -lib format -main Main && \
	neko Main.n $(projdir)/assets/img/final \
		$(shell find $(projdir)/assets/img/original -type f -name '*.png')
