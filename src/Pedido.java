import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Pedido com estado privado: só muda por métodos que protegem as regras. */
public class Pedido {
    private final List<Item> itens = new ArrayList<>();
    private StatusPedido status = StatusPedido.CRIADO;

    public void adicionar(Item item) {
        Objects.requireNonNull(item, "item");
        if (status != StatusPedido.CRIADO) {
            throw new IllegalStateException("Pedido já fechado: não aceita novos itens.");
        }
        itens.add(item);
    }

    
    public List<Item> itens() { return List.copyOf(itens); }

    
    public List<Item> itensCom(Item extra) {
        List<Item> simulado = new ArrayList<>(itens);
        simulado.add(extra);
        return simulado;
    }

    public boolean vazio() { return itens.isEmpty(); }

    public double total() {
        double soma = 0;
        for (Item item : itens) soma += item.preco();
        return soma;
    }

    public StatusPedido status() { return status; }

   
    public boolean pagarCom(Pagamento pagamento) {
        if (status != StatusPedido.CRIADO || itens.isEmpty()) {
            throw new IllegalStateException("Só é possível pagar um pedido novo e com itens.");
        }
        if (!pagamento.pagar(total())) return false;
        status = status.proximo();
        return true;
    }

   
    public void avancar() {
        if (status == StatusPedido.CRIADO) {
            throw new IllegalStateException("Pague o pedido antes de prepará-lo.");
        }
        status = status.proximo();
    }
}
