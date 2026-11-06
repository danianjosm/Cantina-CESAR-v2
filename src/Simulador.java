import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Simula o intervalo para um conjunto de filas. O aluno só entra na fila se der tempo de
 * receber o item e comer antes do fim, senão, desiste
 */
public class Simulador {
    private final int duracao;

    public Simulador(int duracao) { this.duracao = duracao; }

    public ResultadoSimulacao simular(List<Aluno> alunos, List<Fila> filas) {
        Map<Fila, PriorityQueue<Double>> atendentesLivresEm = new HashMap<>();
        for (Fila fila : filas) atendentesLivresEm.put(fila, atendentesDisponiveis(fila));

        int comeram = 0;
        int desistiram = 0;
        double somaEspera = 0;
        for (Aluno aluno : alunos) {
            PriorityQueue<Double> atendentes = atendentesLivresEm.get(filaDe(aluno.item(), filas));
            double inicio = Math.max(aluno.chegada(), atendentes.peek());
            double pronto = inicio + aluno.item().tempo();
            if (pronto + PoliticaDeTempo.MINUTOS_PARA_COMER <= duracao) {
                atendentes.poll();
                atendentes.add(pronto);
                somaEspera += inicio - aluno.chegada();
                comeram++;
            } else {
                desistiram++;
            }
        }
        return new ResultadoSimulacao(comeram, desistiram, comeram == 0 ? 0 : somaEspera / comeram);
    }

    private PriorityQueue<Double> atendentesDisponiveis(Fila fila) {
        PriorityQueue<Double> livres = new PriorityQueue<>();
        for (int i = 0; i < fila.atendentes(); i++) livres.add(0.0);
        return livres;
    }

    private Fila filaDe(Item item, List<Fila> filas) {
        for (Fila fila : filas) {
            if (fila.aceita(item)) return fila;
        }
        throw new IllegalStateException("Nenhuma fila aceita " + item.nome());
    }
}
