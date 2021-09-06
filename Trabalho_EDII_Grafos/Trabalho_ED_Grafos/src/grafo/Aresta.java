package grafo;

public class Aresta {

    private final Vertice origem;
    private final Vertice destino;
    private final int peso;

    public Aresta(Vertice origem, Vertice destino, int peso) {
        this.peso = peso;
        this.origem = origem;
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public Vertice[] getVertices() {
        Vertice[] vertices = {origem, destino};
        return vertices;
    }

    public Vertice getProx(Vertice v) {
        Vertice retorno = null;

        if (v == origem) {
            retorno = destino;
        } else if (v == destino) {
            retorno = origem;
        }
        return retorno;
    }

    public boolean hasVertice(Vertice v) {
        return v == origem || v == destino;
    }

    public boolean contemVertices(Vertice[] vertices) {
        if (vertices.length == 2) {
            return hasVertice(vertices[0]) && hasVertice(vertices[1]);
        }
        return false;
    }

    @Override
    public String toString() {
        return origem + " <- " + peso + " -> " + destino;
    }

}
