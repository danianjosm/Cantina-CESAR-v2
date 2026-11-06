import java.util.List;

/** o pedido fica pronto quando a fila mais lenta termina */
public class EstimadorPorFilas implements EstimadorDeTempo {
    private final List<Fila> filas;

    public EstimadorPorFilas(List<Fila> filas) { this.filas = List.copyOf(filas); }

    public int minutosAtePronto(List<Item> itens) {
        int maisLenta = 0;
        for (Fila fila : filas) {
            maisLenta = Math.max(maisLenta, tempoNa(fila, itens));
        }
        return maisLenta;
    }

    private int tempoNa(Fila fila, List<Item> itens) {
        int soma = 0;
        for (Item item : itens) {
            if (fila.aceita(item)) soma += item.tempo();
        }
        return soma;
    }
}
