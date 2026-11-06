
public enum StatusPedido {
    CRIADO("CRIADO"),
    PAGO("PAGO"),
    EM_PREPARO("EM PREPARO"),
    PRONTO("PRONTO");

    private final String rotulo;

    StatusPedido(String rotulo) { this.rotulo = rotulo; }

    public String rotulo() { return rotulo; }

    public boolean finalizado() { return this == PRONTO; }

    public StatusPedido proximo() {
        if (finalizado()) {
            throw new IllegalStateException("O pedido já está pronto.");
        }
        return values()[ordinal() + 1];
    }
}
