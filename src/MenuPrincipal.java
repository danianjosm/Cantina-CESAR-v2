import java.util.List;

/** Mostra os modos disponíveis e executa o escolhido. */
public class MenuPrincipal {
    private final Console console;
    private final List<Modo> modos;

    public MenuPrincipal(Console console, List<Modo> modos) {
        this.console = console;
        this.modos = List.copyOf(modos);
    }

    public void executar() {
        console.linha("=== Cantina CESAR ===");
        int posicao = 1;
        for (Modo modo : modos) console.linha(posicao++ + ") " + modo.titulo());
        int opcao = console.lerInteiro("Escolha: ", 1, modos.size());
        modos.get(opcao - 1).executar();
    }
}
