/** Tipo do item: decide em qual fila ele é atendido. */
public enum TipoItem {
    RAPIDO("Rápido"),
    PREPARADO("Preparado na hora");

    private final String rotulo;

    TipoItem(String rotulo) { this.rotulo = rotulo; }

    public String rotulo() { return rotulo; }
}
