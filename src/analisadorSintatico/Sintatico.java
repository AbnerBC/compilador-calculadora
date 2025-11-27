package analisadorSintatico;

import analisadorLexico.*;

public class Sintatico {
    private Lexico lexico;
    private Token tokenAtual;

    public Sintatico(Lexico lexico) {
        this.lexico = lexico;
        this.tokenAtual = lexico.pegaProximoToken();
    }

    private void verificador(TipoToken tipo) {
        if (tokenAtual.tipo == tipo) {
            tokenAtual = lexico.pegaProximoToken();
        } else {
            throw new ExcecaoSintatica("Erro Sintático! Tipo Esperado: " + tipo + " - Tipo Encontrado: " + tokenAtual.tipo);
        }
    }

/**
 * Trata a regra de maior precedência: números, parênteses e sinais unários.
 * Nova Gramática: B → ((SOMA | SUBTRACAO))? FATOR
 * FATOR → Número | (S)
 */
    private double regraB() {
        double sinal = 1.0;

        // 1. Verifica se há um operador unário opcional (+ ou -)
        if (tokenAtual.tipo == TipoToken.SOMA) {
            verificador(TipoToken.SOMA);
        } else if (tokenAtual.tipo == TipoToken.SUBTRACAO) {
            verificador(TipoToken.SUBTRACAO);
            sinal = -1.0;
        }

        double resultado;
        Token token = tokenAtual;

        // 2. Procura pelo FATOR (Número ou expressão em parênteses)
        if (token.tipo == TipoToken.NUMERO) {
            verificador(TipoToken.NUMERO);
            resultado = Double.parseDouble(token.valor);
        } else if (token.tipo == TipoToken.PARENTESES_ESQUERDO) {
            verificador(TipoToken.PARENTESES_ESQUERDO);
            resultado = regraS();
            verificador(TipoToken.PARENTESES_DIREITO);
        } else {
            throw new ExcecaoSintatica("Erro Sintático na Regra B: Esperado NÚMERO ou '(', mas encontrado " + token.tipo);
        }

        return sinal * resultado;
    }

    // A → B ((Multiplicação OU Divisão) B)*
    private double regraA() {
        double resultado = regraB();
        while (tokenAtual.tipo == TipoToken.MULTIPLICACAO 
        || tokenAtual.tipo == TipoToken.DIVISAO) {
            Token token = tokenAtual;
            if (token.tipo == TipoToken.MULTIPLICACAO) {
                verificador(TipoToken.MULTIPLICACAO);
                resultado *= regraB();
            } else if (token.tipo == TipoToken.DIVISAO) {
                verificador(TipoToken.DIVISAO);
                double divisor = regraB();
                if (divisor == 0) {
                    throw new RuntimeException("Erro Semântico: Divisão por zero.");
                }
                resultado /= divisor;
            }
        }
        return resultado;
    }

    // S → A ((Soma OU Subtração) A)*
    public double regraS() {
        double resultado = regraA();
        while (tokenAtual.tipo == TipoToken.SOMA
        || tokenAtual.tipo == TipoToken.SUBTRACAO) {
            Token token = tokenAtual;
            if (token.tipo == TipoToken.SOMA) {
                verificador(TipoToken.SOMA);
                resultado += regraA();
            } else if (token.tipo == TipoToken.SUBTRACAO) {
                verificador(TipoToken.SUBTRACAO);
                resultado -= regraA();
            }
        }
        return resultado;
    }
}

