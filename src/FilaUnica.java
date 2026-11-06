/** todo mundo na mesma fila */
public class FilaUnica implements Fila {
    private final int atendentes;

    public FilaUnica(int atendentes) { this.atendentes = atendentes; }

    public String nome() { return "Fila única"; }
    public boolean aceita(Item item) { return true; }
    public int atendentes() { return atendentes; }
}
