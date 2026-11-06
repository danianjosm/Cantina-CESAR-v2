import java.util.List;


public class Main {
    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);
        Cardapio cardapio = Cardapio.padrao();
        List<Fila> filas = List.of(new FilaRapida(), new FilaPreparo());
        PoliticaDeTempo politica = new PoliticaDeTempo(new EstimadorPorFilas(filas));

        MenuPrincipal menu = new MenuPrincipal(console, List.of(
                new FluxoDePedido(console, new Tela(console), cardapio, MeiosDePagamento.padrao(), politica),
                new ModoSimulador(console, cardapio)));
        try {
            menu.executar();
        } catch (EntradaEncerradaException e) {
            console.linha("\nEntrada encerrada. Até a próxima!");
        }
    }
}
