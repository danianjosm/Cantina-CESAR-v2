import java.util.List;
import java.util.Locale;

/** Prova com números: 30 alunos em 15 minutos, fila única x filas separadas (mesmos 2 atendentes). */
public class ModoSimulador implements Modo {
    private static final int ALUNOS = 30;
    private static final int DURACAO = 15;
    private static final long SEMENTE = 42;
    private static final int RODADAS = 1000;

    private final Console console;
    private final Cardapio cardapio;

    public ModoSimulador(Console console, Cardapio cardapio) {
        this.console = console;
        this.cardapio = cardapio;
    }

    public String titulo() { return "Simulador do intervalo"; }

    public void executar() {
        List<Fila> unica = List.of(new FilaUnica(2));
        List<Fila> separadas = List.of(new FilaRapida(), new FilaPreparo());
        GeradorDeAlunos gerador = new GeradorDeAlunos(cardapio, DURACAO);
        Simulador simulador = new Simulador(DURACAO);

        console.linha("\n" + ALUNOS + " alunos chegam ao longo de " + DURACAO + " min. Cada um pede 1 item"
                + " e precisa de " + PoliticaDeTempo.MINUTOS_PARA_COMER + " min para comer."
                + "\nOs dois cenários usam os mesmos 2 atendentes.");

        List<Aluno> turma = gerador.gerar(ALUNOS, SEMENTE);
        console.linha("\nUma turma (semente " + SEMENTE + "):");
        mostrar("Fila única", simulador.simular(turma, unica));
        mostrar("Filas separadas", simulador.simular(turma, separadas));

        double comeramUnica = 0, comeramSeparadas = 0;
        for (long semente = 1; semente <= RODADAS; semente++) {
            List<Aluno> sorteio = gerador.gerar(ALUNOS, semente);
            comeramUnica += simulador.simular(sorteio, unica).comeram();
            comeramSeparadas += simulador.simular(sorteio, separadas).comeram();
        }
        console.linha("\nMédia de " + RODADAS + " turmas sorteadas (quantos conseguem comer):");
        console.linha(String.format(Locale.forLanguageTag("pt-BR"),
                "  Fila única:      %.1f de %d%n  Filas separadas: %.1f de %d",
                comeramUnica / RODADAS, ALUNOS, comeramSeparadas / RODADAS, ALUNOS));
    }

    private void mostrar(String cenario, ResultadoSimulacao r) {
        console.linha(String.format(Locale.forLanguageTag("pt-BR"),
                "  %-16s comeram %2d | desistiram %2d | espera média %.1f min",
                cenario, r.comeram(), r.desistiram(), r.esperaMedia()));
    }
}
