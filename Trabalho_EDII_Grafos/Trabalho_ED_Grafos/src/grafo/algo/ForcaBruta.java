package grafo.algo;

import grafo.Aresta;
import grafo.Grafo;
import grafo.Percurso;
import grafo.Vertice;
import java.util.ArrayList;
import java.util.List;

public class ForcaBruta {

    public List<Percurso> percorreTodos(Grafo grafo, Vertice origem) {
        List<Percurso> ps = new ArrayList<>();
        Percurso percurso = new Percurso(grafo, origem);

        List<Aresta> arestasValidas = grafo.getArestas(origem);

        for (Aresta arestaValida : arestasValidas) {
            Vertice prox = arestaValida.getProx(origem);

            List<Percurso> lista = ForcaBruta.this.percorreTodos(grafo, prox, arestaValida, new Percurso(percurso), origem);

            ps.addAll(lista);
        }

        return ps;
    }

    private List<Percurso> percorreTodos(Grafo grafo, Vertice origem, Aresta aresta, Percurso perc, Vertice destino) {
        List<Percurso> percursoList = new ArrayList<>();

        perc.add(aresta);

        if (perc.isCircuitoHam()) {
            percursoList.add(perc);
            return percursoList;
        }

        if (perc.isCaminhoHam()) {
            List<Aresta> arestasToDestino = grafo.getArestas(origem, destino);
            for (Aresta arestaToDestino : arestasToDestino) {
                Vertice prox = arestaToDestino.getProx(origem);
                List<Percurso> lista = ForcaBruta.this.percorreTodos(grafo, prox, arestaToDestino, new Percurso(perc), destino);
                percursoList.addAll(lista);
            }
        } else {
            Percurso percursoNovo = new Percurso(perc);

            List<Aresta> arestasValidas = getArestasValidas(grafo, origem, perc);

            for (Aresta arestaValida : arestasValidas) {
                Vertice prox = arestaValida.getProx(origem);

                List<Percurso> lista = ForcaBruta.this.percorreTodos(grafo, prox, arestaValida, new Percurso(perc), destino);

                percursoList.addAll(lista);
            }
        }

        return percursoList;
    }

    public Percurso percorre(Grafo grafo, Vertice origem) {
        Percurso melhor = null;
        List<Percurso> all = this.percorreTodos(grafo, origem);
        if (all.size() > 0) {
            melhor = all.get(0);
            for (Percurso p : all) {
                if (p.getPeso() < melhor.getPeso()) {
                    melhor = p;
                }
            }
        }
        return melhor;
    }

    private List<Aresta> getArestasValidas(Grafo grafo, Vertice vertice, Percurso perc) {
        List<Aresta> arestasValidas = new ArrayList<>();

        for (Aresta a : grafo.getArestas(vertice)) {
            Vertice v = a.getProx(vertice);
            if (!perc.getVerticesVisitados().contains(v)) {
                arestasValidas.add(a);
            }
        }

        return arestasValidas;
    }
}
