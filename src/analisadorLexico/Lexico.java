package analisadorLexico;

public class Lexico {
    private final String texto;
    private int pos = 0;
    private char caractereAtual;

    public Lexico(String texto) {
        this.texto = texto;
        if (texto.length() > 0) {
            caractereAtual = texto.charAt(0);
        }
        else {
            caractereAtual = '\0';
        }
    }

    private void avanco() {
        pos++;
        if (pos >= texto.length()) {
            caractereAtual = '\0';
        } else {
            caractereAtual = texto.charAt(pos);
        }
    }

    private void ignoraEspaco() {
        while (caractereAtual == ' ') {
            avanco();
        }
    }

    private Token numero() {
        StringBuilder resultado = new StringBuilder();
        while ((caractereAtual >= '0' && caractereAtual <= '9') || (caractereAtual == '.')) {
            resultado.append(caractereAtual);
            avanco();
        }
        return new Token(TipoToken.NUMERO, resultado.toString());
    }

    public Token pegaProximoToken() {
        while (caractereAtual != '\0') {

            if (caractereAtual == ' ') {
                ignoraEspaco();
                continue;
            }

            if (caractereAtual >= '0' && caractereAtual <= '9') {
                return numero();
            }

            if (caractereAtual == '+') {
                avanco();
                return new Token(TipoToken.SOMA);
            }

            if (caractereAtual == '-') {
                avanco();
                return new Token(TipoToken.SUBTRACAO);
            }

            if (caractereAtual == '*') {
                avanco();
                return new Token(TipoToken.MULTIPLICACAO);
            }

            if (caractereAtual == '/') {
                avanco();
                return new Token(TipoToken.DIVISAO);
            }

            if (caractereAtual == '(') {
                avanco();
                return new Token(TipoToken.PARENTESES_ESQUERDO);
            }

            if (caractereAtual == ')') {
                avanco();
                return new Token(TipoToken.PARENTESES_DIREITO);
            }

            throw new ExcecaoLexica("Erro Léxico! Caractere Encontrado: " + caractereAtual);
        }

        return new Token(TipoToken.EOF);
    }
}
