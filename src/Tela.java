
public class Tela {
    private final Console console;

    public Tela(Console console) { this.console = console; }

    public void cardapio(Cardapio cardapio) {
        console.linha("\nCardápio (valores estimados):");
        int posicao = 1;
        for (Item item : cardapio.itens()) {
            console.linha(posicao++ + ") " + item.nome() + "  " + Dinheiro.formatar(item.preco())
                    + "  ~" + item.tempo() + " min  [" + item.tipo().rotulo() + "]");
        }
    }

    public void meiosDePagamento(MeiosDePagamento meios) {
        console.linha("\nForma de pagamento:");
        int posicao = 1;
        for (Pagamento meio : meios.meios()) {
            console.linha(posicao++ + ") " + meio.nome());
        }
    }

    public void resumo(Pedido pedido) {
        console.linha("\nResumo do pedido:");
        for (Item item : pedido.itens()) {
            console.linha(" - " + item.nome() + "  " + Dinheiro.formatar(item.preco()));
        }
        console.linha("Total: " + Dinheiro.formatar(pedido.total()));
    }

    public void status(Pedido pedido) {
        console.linha("Status: " + pedido.status().rotulo());
    }
}
