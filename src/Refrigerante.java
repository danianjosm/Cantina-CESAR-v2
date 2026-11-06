public class Refrigerante implements Item {
    public String nome() { return "Refrigerante lata"; }
    public double preco() { return 8.0; }
    public int tempo() { return 1; }
    public TipoItem tipo() { return TipoItem.RAPIDO; }
}
