import java.util.List;

/** Novo meio = classe nova + uma linha em {@link #padrao()}. */
public class MeiosDePagamento {
    private final List<Pagamento> meios;

    public MeiosDePagamento(List<Pagamento> meios) { this.meios = List.copyOf(meios); }

    public static MeiosDePagamento padrao() {
        return new MeiosDePagamento(List.of(new Pix(), new CartaoCredito(), new ValeRefeicao()));
    }

    public List<Pagamento> meios() { return meios; }

    public Pagamento meio(int posicao) { return meios.get(posicao - 1); }
}
