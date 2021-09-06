package grafo;

import java.util.ArrayList;
import java.util.List;

public class Percurso {

    private final Grafo grafo;
    private boolean caminhoHam = false;
    private boolean circuitoHam = false;

    private List<Vertice> verticesVisitados = new ArrayList();
    private List<Aresta> arestasVisitadas = new ArrayList();

    private StringBuilder pathStr;

    public Percurso(Grafo grafo, Vertice vertice) {
        this.grafo = grafo;
        verticesVisitados.add(vertice);
    }

    public Percurso(Percurso percurso) {
        this.grafo = percurso.grafo;

        this.caminhoHam = percurso.caminhoHam;
        this.circuitoHam = percurso.circuitoHam;

        verticesVisitados = percurso.getVerticesVisitados();
        arestasVisitadas = percurso.getArestasVisitadas();
    }

    public void add(Aresta aresta) {
        arestasVisitadas.add(aresta);
        verticesVisitados.add(aresta.getProx(this.getAnt()));

        caminhoHam = verificaCaminhoHam();
        circuitoHam = verificaCircuitoHam();

    }

    private boolean verificaCaminhoHam() {
        List<Vertice> grafoVertices = grafo.getListVertices();
        if (this.verticesVisitados.containsAll(grafoVertices)) {
            if (this.verticesVisitados.size() == grafoVertices.size()) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaCircuitoHam() {
        Vertice inicio = this.verticesVisitados.get(0);
        Vertice fim = this.verticesVisitados.get(this.verticesVisitados.size() - 1);
        if (inicio == fim) {
            List<Vertice> grafoVertices = grafo.getListVertices();
            if (this.verticesVisitados.containsAll(grafoVertices)) {
                if (this.verticesVisitados.size() == grafoVertices.size() + 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public String exibePercurso() {

        pathStr = new StringBuilder();

        for (Vertice ver : verticesVisitados) {
            int pesoV = arestasVisitadas.get(verticesVisitados.indexOf(ver)).getPeso();
            pathStr.append(ver.getLabel()).append(" ").append(pesoV).append(" ");
        }

        pathStr.delete(pathStr.length() - 3, pathStr.length());
        pathStr.append(" = ").append(this.getPeso());

        return pathStr.toString();
    }

    public boolean isCaminhoHam() {
        return caminhoHam;
    }

    public boolean isCircuitoHam() {
        return circuitoHam;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public int getPeso() {
        int total = 0;
        for (Aresta aresta : arestasVisitadas) {
            total += aresta.getPeso();
        }
        return total;
    }

    public Vertice getAnt() {
        return verticesVisitados.get(verticesVisitados.size() - 1);
    }

    public List<Vertice> getVerticesVisitados() {
        return new ArrayList<>(verticesVisitados);
    }

    public List<Aresta> getArestasVisitadas() {
        return new ArrayList<>(arestasVisitadas);
    }

}
