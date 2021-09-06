package grafo;

public class Vertice {

    private String label;

    public Vertice(String descricao) {
        this.label = descricao;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}
