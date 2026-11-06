
public class PoliticaDeTempo {
    public static final int MINUTOS_PARA_COMER = 3;

    private final EstimadorDeTempo estimador;

    public PoliticaDeTempo(EstimadorDeTempo estimador) { this.estimador = estimador; }

    public int minutosAtePronto(Pedido pedido, Item novo) {
        return estimador.minutosAtePronto(pedido.itensCom(novo));
    }

    public boolean daTempo(int minutosAtePronto, int minutosRestantes) {
        return minutosAtePronto <= minutosRestantes;
    }

    public boolean sobraTempoParaComer(int minutosAtePronto, int minutosRestantes) {
        return minutosRestantes - minutosAtePronto >= MINUTOS_PARA_COMER;
    }
}
