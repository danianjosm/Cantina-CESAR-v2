/** preparo do pedido + tempo para comer cabe nos minutos que restam. */
public class PoliticaDeTempo {
    public static final int MINUTOS_PARA_COMER = 3;

    private final EstimadorDeTempo estimador;

    public PoliticaDeTempo(EstimadorDeTempo estimador) { this.estimador = estimador; }

    public int minutosNecessarios(Pedido pedido, Item novo) {
        return estimador.minutosAtePronto(pedido.itensCom(novo)) + MINUTOS_PARA_COMER;
    }

    public boolean daTempo(Pedido pedido, Item novo, int minutosRestantes) {
        return minutosNecessarios(pedido, novo) <= minutosRestantes;
    }
}
