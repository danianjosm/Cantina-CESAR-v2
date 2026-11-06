import java.util.List;

public class EstimadorFilaUnica implements EstimadorDeTempo {
    public int minutosAtePronto(List<Item> itens) {
        int soma = 0;
        for (Item item : itens) soma += item.tempo();
        return soma;
    }
}
