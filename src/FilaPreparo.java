
public class FilaPreparo implements Fila {
    public String nome() { return "Fila de preparo"; }
    public boolean aceita(Item item) { return item.tipo() == TipoItem.PREPARADO; }
    public int atendentes() { return 1; }
}
