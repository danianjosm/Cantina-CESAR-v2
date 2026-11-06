
public class FilaRapida implements Fila {
    public String nome() { return "Fila rápida"; }
    public boolean aceita(Item item) { return item.tipo() == TipoItem.RAPIDO; }
    public int atendentes() { return 1; }
}
