package grafo.algo;

import grafo.Aresta;
import grafo.Grafo;
import grafo.Percurso;
import grafo.Vertice;
import java.util.ArrayList;
import java.util.List;

public class VizinhoMaisProximo {

    public Percurso percorre(Grafo grafo, Vertice origem) {
        Percurso percurso = new Percurso(grafo, origem);
        Percurso percursoNovo;

        List<Aresta> arestasValidas = grafo.getArestas(origem);

        while (!arestasValidas.isEmpty()) {
            Aresta melhorAresta = getMelhorAresta(arestasValidas);
            Vertice prox = melhorAresta.getProx(origem);
            percursoNovo = percorre(grafo, prox, melhorAresta, new Percurso(percurso), origem);

            if (percursoNovo.isCircuitoHam()) {
                return percursoNovo;
            }

            arestasValidas.remove(melhorAresta);
        }

        return null;
    }

    private Percurso percorre(Grafo grafo, Vertice origem, Aresta aresta, Percurso percurso, Vertice destino) {
        percurso.add(aresta);
        
        if (percurso.isCaminhoHam()) {

            Aresta arestaToDestino = getMelhorAresta(grafo.getArestas(origem, destino));
            if (arestaToDestino != null) {
                percurso.add(arestaToDestino);
            }

            return percurso;

        } else {

            Percurso percursoNovo = new Percurso(percurso);

            List<Aresta> arestasValidas = getArestasValidas(grafo, origem, percurso);

            while (!arestasValidas.isEmpty()) {
                Aresta melhorAresta = getMelhorAresta(arestasValidas);
                Vertice prox = melhorAresta.getProx(origem);

                percursoNovo = percorre(grafo, prox, melhorAresta, new Percurso(percurso), destino);

                if (percursoNovo.isCircuitoHam()) {
                    return percursoNovo;
                }

                arestasValidas.remove(melhorAresta);
            }

            return percursoNovo;
        }

    }

    private List<Aresta> getArestasValidas(Grafo grafo, Vertice vertice, Percurso p) {

        List<Aresta> arestasValidas = new ArrayList<>();

        for (Aresta a : grafo.getArestas(vertice)) {
            Vertice v = a.getProx(vertice);
            if (!p.getVerticesVisitados().contains(v)) {
                arestasValidas.add(a);
            }
        }

        return arestasValidas;
    }

    private Aresta getMelhorAresta(List<Aresta> arestas) {
        if (arestas.isEmpty()) {
            return null;
        } else {
            Aresta melhor = arestas.get(0);
            for (Aresta aresta : arestas) {
                if (aresta.getPeso() < melhor.getPeso()) {
                    melhor = aresta;
                }
            }
            return melhor;
        }
    }

}
