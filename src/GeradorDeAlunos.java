import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/** Gera alunos chegando ao longo do intervalo */
public class GeradorDeAlunos {
    private final Cardapio cardapio;
    private final int duracao;

    public GeradorDeAlunos(Cardapio cardapio, int duracao) {
        this.cardapio = cardapio;
        this.duracao = duracao;
    }

    public List<Aluno> gerar(int quantidade, long semente) {
        Random sorteio = new Random(semente);
        List<Aluno> alunos = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            Item item = cardapio.item(1 + sorteio.nextInt(cardapio.itens().size()));
            alunos.add(new Aluno(sorteio.nextDouble() * duracao, item));
        }
        alunos.sort(Comparator.comparingDouble(Aluno::chegada));
        return alunos;
    }
}
