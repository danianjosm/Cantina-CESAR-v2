
public interface Item {
    String nome();
    double preco();
    int tempo();       // minutos (dados da cantina; ver README)
    TipoItem tipo();   // RAPIDO ou PREPARADO
}
