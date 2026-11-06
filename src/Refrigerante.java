public class Refrigerante implements Item {
    public String nome() { return "Refrigerante"; }
    public double preco() { return 5.0; }
    public int tempo() { return 1; }
    public TipoItem tipo() { return TipoItem.RAPIDO; }
}
