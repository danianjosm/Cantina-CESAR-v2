public class Cuscuz implements Item {
    public String nome() { return "Cuscuz com queijo"; }
    public double preco() { return 14.0; }
    public int tempo() { return 10; }
    public TipoItem tipo() { return TipoItem.PREPARADO; }
}
