
import grafo.Aresta;
import grafo.Grafo;
import grafo.Percurso;
import grafo.algo.ForcaBruta;
import grafo.algo.VizinhoMaisProximo;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // Grafo 01
        Grafo g1 = new Grafo(5);
        System.out.println("Grafo 01");

        g1.ligarVertices("A", "B", 7);
        g1.ligarVertices("A", "C", 6);
        g1.ligarVertices("A", "D", 10);
        g1.ligarVertices("A", "E", 7);

        g1.ligarVertices("B", "C", 7);
        g1.ligarVertices("B", "D", 10);
        g1.ligarVertices("B", "E", 10);

        g1.ligarVertices("C", "D", 5);
        g1.ligarVertices("C", "E", 7);

        g1.ligarVertices("D", "E", 6);

        List<Percurso> percurso = new ForcaBruta().percorreTodos(g1, g1.buscarVertice("A"));
        percurso.forEach((p) -> {
            System.out.println(p.exibePercurso());
        });
        System.out.println("");

        Percurso forcaBruta = new ForcaBruta().percorre(g1, g1.buscarVertice("A"));
        System.out.println("Força Bruta: " + forcaBruta.exibePercurso());
        System.out.println("");

        Percurso vizinho = new VizinhoMaisProximo().percorre(g1, g1.buscarVertice("A"));
        System.out.println("Vizinho mais proximo: " + vizinho.exibePercurso());
        System.out.println("");

        // Grafo 02
        System.out.println("Grafo 02");
        Grafo g2 = new Grafo(6);

        g2.ligarVertices("A", "B", 2);
        g2.ligarVertices("A", "C", 6);
        g2.ligarVertices("A", "E", 1);
        g2.ligarVertices("B", "C", 5);
        g2.ligarVertices("B", "D", 7);
        g2.ligarVertices("C", "D", 2);
        g2.ligarVertices("C", "E", 8);
        g2.ligarVertices("D", "F", 4);
        g2.ligarVertices("E", "F", 3);

        List<Percurso> percurso2 = new ForcaBruta().percorreTodos(g2, g2.buscarVertice("A"));
        percurso2.forEach((p) -> {
            System.out.println(p.exibePercurso());
        });
        System.out.println("");

        Percurso forcaBruta2 = new ForcaBruta().percorre(g2, g2.buscarVertice("A"));
        System.out.println("Força Bruta: " + forcaBruta2.exibePercurso());
        System.out.println("");

        Percurso vizinho2 = new VizinhoMaisProximo().percorre(g2, g2.buscarVertice("A"));
        System.out.println("Vizinho mais proximo: " + vizinho2.exibePercurso());
        System.out.println("");

        // Descomentar para gravar em um arquivo txt
        //Salvar.writerInTxt(g1, "Grafo01");
        //Salvar.writerInTxt(g2, "Grafo02");
    }

    public static void exibirArestas(Grafo g) {

        for (Aresta aresta : g.getListArestas()) {
            System.out.println(aresta);
        }

    }

}
