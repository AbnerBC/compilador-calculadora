package analisadorSintatico;

public class ExcecaoSintatica extends RuntimeException {
    public ExcecaoSintatica(String mensagem) {
        super(mensagem);
    }
}
