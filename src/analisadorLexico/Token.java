package analisadorLexico;

public class Token {
    public final TipoToken tipo;
    public final String valor;

    public Token(TipoToken tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public Token(TipoToken tipo) {
        this.tipo = tipo;
        this.valor = null;
    }

    @Override
    public String toString() {
        if (valor == null) {
            return tipo.name();
        }
        return tipo.name() + "(" + valor + ")";
    }
}
