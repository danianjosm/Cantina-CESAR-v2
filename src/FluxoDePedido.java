/** monta o pedido, cobra e acompanha o status. */
public class FluxoDePedido implements Modo {
    private static final int MAX_MINUTOS = 15;

    private final Console console;
    private final Tela tela;
    private final Cardapio cardapio;
    private final MeiosDePagamento meios;
    private final PoliticaDeTempo politica;

    public FluxoDePedido(Console console, Tela tela, Cardapio cardapio,
                         MeiosDePagamento meios, PoliticaDeTempo politica) {
        this.console = console;
        this.tela = tela;
        this.cardapio = cardapio;
        this.meios = meios;
        this.politica = politica;
    }

    public String titulo() { return "Fazer um pedido"; }

    public void executar() {
        int minutos = console.lerInteiro(
                "Quantos minutos faltam no intervalo? (1 a " + MAX_MINUTOS + ") ", 1, MAX_MINUTOS);
        Pedido pedido = montarPedido(minutos);
        if (pedido.vazio()) {
            console.linha("Nenhum item. Até a próxima!");
            return;
        }
        tela.resumo(pedido);
        if (!cobrar(pedido)) {
            console.linha("Pedido cancelado. Até a próxima!");
            return;
        }
        acompanharPreparo(pedido);
    }

    private Pedido montarPedido(int minutos) {
        Pedido pedido = new Pedido();
        while (true) {
            tela.cardapio(cardapio);
            int opcao = console.lerInteiro("Escolha um item (0 para finalizar): ", 0, cardapio.itens().size());
            if (opcao == 0) return pedido;
            tentarAdicionar(pedido, cardapio.item(opcao), minutos);
        }
    }

    private void tentarAdicionar(Pedido pedido, Item item, int minutos) {
        int necessarios = politica.minutosNecessarios(pedido, item);
        if (necessarios <= minutos) {
            pedido.adicionar(item);
            console.linha("Dá tempo! Adicionado: " + item.nome() + " (precisa de ~" + necessarios
                    + " min com " + PoliticaDeTempo.MINUTOS_PARA_COMER + " para comer; faltam " + minutos + ")");
        } else {
            console.linha("Não dá tempo de pegar " + item.nome() + " (precisaria de ~" + necessarios
                    + " min e faltam " + minutos + ").");
        }
    }

    /** Devolve true se o pagamento foi aprovado; 0 cancela. Recusa permite tentar outro meio. */
    private boolean cobrar(Pedido pedido) {
        while (true) {
            tela.meiosDePagamento(meios);
            int opcao = console.lerInteiro("Escolha (0 para cancelar): ", 0, meios.meios().size());
            if (opcao == 0) return false;
            if (pedido.pagarCom(meios.meio(opcao))) {
                console.linha("Pagamento aprovado.");
                tela.status(pedido);
                return true;
            }
            console.linha("Pagamento recusado. Tente outra forma de pagamento.");
        }
    }

    private void acompanharPreparo(Pedido pedido) {
        console.aguardarEnter("(Enter para iniciar o preparo)");
        pedido.avancar();
        tela.status(pedido);
        console.aguardarEnter("(Enter para finalizar)");
        pedido.avancar();
        tela.status(pedido);
    }
}
