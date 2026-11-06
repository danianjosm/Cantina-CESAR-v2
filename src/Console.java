import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/** entrada do usuário é validada aqui. */
public class Console {
    private final Scanner entrada;
    private final PrintStream saida;

    public Console(InputStream in, PrintStream out) {
        this.entrada = new Scanner(in, StandardCharsets.UTF_8);
        this.saida = out;
    }

    public void linha(String texto) { saida.println(texto); }

    public void escrever(String texto) {
        saida.print(texto);
        saida.flush();
    }

    /** Repete a pergunta até vir um inteiro entre min e max */
    public int lerInteiro(String pergunta, int min, int max) {
        while (true) {
            escrever(pergunta);
            String texto = lerLinha().trim();
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= min && valor <= max) return valor;
                linha("Opção inválida: escolha um número de " + min + " a " + max + ".");
            } catch (NumberFormatException e) {
                linha("Entrada inválida: digite apenas um número de " + min + " a " + max + ".");
            }
        }
    }

    public void aguardarEnter(String aviso) {
        linha(aviso);
        lerLinha();
    }

    private String lerLinha() {
        if (!entrada.hasNextLine()) throw new EntradaEncerradaException();
        return entrada.nextLine();
    }
}
