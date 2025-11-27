import analisadorLexico.*;
import analisadorSintatico.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira uma expressão matemática: ");
        String input = scanner.nextLine();
        Lexico lexico = new Lexico(input);
        Sintatico sintatico = new Sintatico(lexico);
        double resultado = sintatico.regraS();
        System.out.println("Resultado: " + resultado);
        scanner.close();
    }
}

