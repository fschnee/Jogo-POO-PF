Implementa apenas um pequeno [subconjunto](https://www.json.org/) dos arquivos
`.json`, considerando:
  * Apenas numeros inteiros (e não em notação científica);
  * Strings codificadas em ASCII;
  * Uso somente de espaços na codificação e não *tabs*;
  * Pares **valor**-**dado** separados somente por um caracter dois pontos (:),
  sem uso de espaços;
  * Elementos de vetores sempre possuindo pelomenos um espaço depois
  (independente se tem ou não elemento depois)
Alem disso tem um leve incremento, todas as strings que inciam pelo caractere `;`
são consideradas de multiplas linhas, como pode ser visto em [`Devroom.json`][1]

[1]: ../../../../assets/text/spaces/Devroom.json
