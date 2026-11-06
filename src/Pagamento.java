/** Contrato de pagamento: {@code pagar} devolve true se aprovado, false se recusado. */
public interface Pagamento {
    String nome();
    boolean pagar(double valor);
}
