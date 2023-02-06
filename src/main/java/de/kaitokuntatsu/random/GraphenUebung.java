package de.kaitokuntatsu.random;

import de.kaitokuntatsu.datastructures.Graph.Edge;
import de.kaitokuntatsu.datastructures.Graph.Graph;
import de.kaitokuntatsu.datastructures.Graph.Vertex;

public class GraphenUebung {

    // ABI.UEB.12
    public Graph create() {
        Graph lGraph = new Graph();

        Vertex[] lVertices = {
                new Vertex("A"),
                new Vertex("B"),
                new Vertex("C"),
                new Vertex("D"),
                new Vertex("E"),
                new Vertex("F"),
                new Vertex("G"),
                new Vertex("H"),
                new Vertex("I"),
        };
        for (Vertex lVertex : lVertices) {
            lGraph.addVertex(lVertex);
        }

        lGraph.addEdge(new Edge(lVertices[0], lVertices[1], 1));
        lGraph.addEdge(new Edge(lVertices[0], lVertices[2], 1));
        lGraph.addEdge(new Edge(lVertices[1], lVertices[4], 1));
        lGraph.addEdge(new Edge(lVertices[4], lVertices[3], 1));
        lGraph.addEdge(new Edge(lVertices[4], lVertices[6], 1));
        lGraph.addEdge(new Edge(lVertices[6], lVertices[5], 1));
        lGraph.addEdge(new Edge(lVertices[2], lVertices[5], 1));
        lGraph.addEdge(new Edge(lVertices[5], lVertices[7], 1));

        return lGraph;
    }
}
