package de.kaitokuntatsu.random;

import de.kaitokuntatsu.datastructures.Graph.Edge;
import de.kaitokuntatsu.datastructures.Graph.Graph;
import de.kaitokuntatsu.datastructures.Graph.Vertex;
import de.kaitokuntatsu.datastructures.List;

public class GraphenUebung {

    public static boolean tiefensuche(Graph lGraph, Vertex pStart, Vertex pZiel, List<Vertex> pPath) {
        pStart.setMark(true);
        if (pStart.equals(pZiel)) {
            pPath.append(pStart);
            return true;
        }
        List<Vertex> lNeighbours = lGraph.getNeighbours(pStart);
        lNeighbours.toFirst();
        while (lNeighbours.hasAccess()) {
            if (!lNeighbours.getContent().isMarked() && tiefensuche(lGraph, lNeighbours.getContent(), pZiel, pPath)) {
                pPath.append(pStart);
                return true;
            }
            lNeighbours.next();
        }
        return false;
    }

    // ABI.UEB.12
    public static Graph create() {
        Graph lGraph = new Graph();

        Vertex[] lVertices = {
                new Vertex("A"), // 0
                new Vertex("B"), // 1
                new Vertex("C"), // 2
                new Vertex("D"), // 3
                new Vertex("E"), // 4
                new Vertex("F"), // 5
                new Vertex("G"), // 6
                new Vertex("H"), // 7
                new Vertex("I"), // 8
        };
        for (Vertex lVertex : lVertices) {
            lGraph.addVertex(lVertex);
        }

        lGraph.addEdge(new Edge(lVertices[0], lVertices[1], 1));
        lGraph.addEdge(new Edge(lVertices[0], lVertices[2], 1));
        lGraph.addEdge(new Edge(lVertices[1], lVertices[4], 1));
        lGraph.addEdge(new Edge(lVertices[4], lVertices[3], 1));
        lGraph.addEdge(new Edge(lVertices[4], lVertices[7], 1));
        lGraph.addEdge(new Edge(lVertices[7], lVertices[6], 1));
        lGraph.addEdge(new Edge(lVertices[2], lVertices[5], 1));
        lGraph.addEdge(new Edge(lVertices[5], lVertices[7], 1));

        return lGraph;
    }

    public static void main(String[] args) {
        Graph lGraph = create();
        List<Vertex> lPath = new List<>();
        System.out.println(tiefensuche(lGraph, lGraph.getVertex("A"), lGraph.getVertex("I"), lPath));
        lPath.toFirst();
        while (lPath.hasAccess()) {
            System.out.println(lPath.getContent().getID());
            lPath.next();
        }
    }
}
