import java.util.List;


public class Cardapio {
    private final List<Item> itens;

    public Cardapio(List<Item> itens) { this.itens = List.copyOf(itens); }

    public static Cardapio padrao() {
        return new Cardapio(List.of(new Coxinha(), new Refrigerante(), new Tapioca(), new Cuscuz()));
    }

    public List<Item> itens() { return itens; }

    /** Posição de 1 a N, como o aluno vê na tela. */
    public Item item(int posicao) { return itens.get(posicao - 1); }
}
