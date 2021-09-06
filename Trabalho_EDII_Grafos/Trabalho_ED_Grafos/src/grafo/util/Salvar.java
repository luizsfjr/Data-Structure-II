package grafo.util;

import grafo.Grafo;
import grafo.Percurso;
import grafo.Vertice;
import grafo.algo.ForcaBruta;
import grafo.algo.VizinhoMaisProximo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe que salva todos os percursos possiveis no em arquivo de texto .txt
 *
 * @author Ian Azevedo  <ianbazevedo@gmail.com>
 */
public class Salvar {

    private static BufferedWriter bufferWriter;

    public static void writerInTxt(Grafo g, String id) throws IOException {
        // Caminho de saida
        String pathFile = "outTxt/";

        // Nome do arquivo de saida
        String fileName = id + ".txt";

        bufferWriter = new BufferedWriter(new FileWriter(pathFile + fileName));

        for (Vertice listVertice : g.getListVertices()) {
            bufferWriter.append("Partindo de " + listVertice.getLabel() + "\n");
            bufferWriter.append("Força Bruta\n");

            List<Percurso> percurso = new ForcaBruta().percorreTodos(g, g.buscarVertice(listVertice.getLabel()));
            for (Percurso p : percurso) {
                bufferWriter.append(p.exibePercurso() + "\n");
            }

            Percurso forcaBruta = new ForcaBruta().percorre(g, g.buscarVertice(listVertice.getLabel()));

            bufferWriter.append("Melhor percurso: " + forcaBruta.exibePercurso() + "\n");
            bufferWriter.append("Vizinho mais proximo\n");
            Percurso vizinho = new VizinhoMaisProximo().percorre(g, g.buscarVertice(listVertice.getLabel()));
            bufferWriter.append("Melhor percurso: " + vizinho.exibePercurso() + "\n\n");

        }
        bufferWriter.close();
    }

}
