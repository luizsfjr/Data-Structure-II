package grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    private char letra = 'A';

    private List<Vertice> listVertices = new ArrayList<>();
    private List<Aresta> listArestas = new ArrayList<>();

    public Grafo(int maxVertices) {
        listVertices = new ArrayList(maxVertices);
        inicializaGrafo(maxVertices);
    }

    private void inicializaGrafo(int maxVertices) {
        for (int i = 0; i < maxVertices; i++) {
            listVertices.add(new Vertice(String.valueOf(letra)));
            letra++;
        }
    }

    public Vertice buscarVertice(String label) {
        for (Vertice v : listVertices) {
            if (v.getLabel().equals(label)) {
                return v;
            }
        }
        return null;
    }

    public void ligarVertices(String origem, String destino, int peso) {
        Vertice orig = buscarVertice(origem);
        Vertice dest = buscarVertice(destino);

        addAresta(new Aresta(orig, dest, peso));
        // addAresta(new Aresta(dest, orig, peso));

    }

    public List<Vertice> getListVertices() {
        return new ArrayList<>(listVertices);
    }

    public List<Aresta> getListArestas() {
        return new ArrayList<>(listArestas);
    }

    public List<Aresta> getArestas(Vertice v1, Vertice v2) {
        List<Aresta> lista = new ArrayList<>();
        Vertice[] v = {v1, v2};

        for (Aresta aresta : listArestas) {
            if (aresta.contemVertices(v)) {
                lista.add(aresta);
            }
        }

        return lista;
    }

    public List<Aresta> getArestas(Vertice v) {
        List<Aresta> lista = new ArrayList<>();

        for (Aresta aresta : listArestas) {
            if (aresta.hasVertice(v)) {
                lista.add(aresta);
            }
        }

        return lista;
    }

    public Grafo addAresta(Aresta a) {
        boolean contem = false;

        for (Aresta aresta : listArestas) {
            if (aresta.equals(a)) {
                contem = true;
            }
        }

        if (!contem) {
            this.listArestas.add(a);
        }

        return this;
    }

}
