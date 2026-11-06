
public interface Item {
    String nome();
    double preco();
    int tempo();       // minutos (estimado)
    TipoItem tipo();   // RAPIDO ou PREPARADO
}
