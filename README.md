# Compilador: Calculadora Simples
Este repositório contém o código-fonte de um compilador simples, implementado em Java, projetado para processar expressões matemáticas básicas de uma calculadora.
## Estrutura Geral:
O projeto foi estruturado da seguinte forma:
* Analisador Léxico: Converte a string de entrada em uma sequência de tokens.
* Analisador Sintático: Verifica se a sequência de tokens está em conformidade com a sintaxe da calculadora.
## Análisador Sintático:
Esse projeto apresenta um analisador sintático Descendente Recursivo onde:
* Regra "S" trata operações de SOMA e SUBTRAÇÃO.
* Regra "A" trata operações de MULTIPLICAÇÃO e DIVISÃO.
* Regra "B" trata números, parênteses e sinais unários.
