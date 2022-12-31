package de.kaitokuntatsu.random;


import de.kaitokuntatsu.datastructures.Graph.*;
import de.kaitokuntatsu.datastructures.List;

/**
 * GraphHandler ist eine Klasse, welche das erstellen und durchsuchen der Abiturklasse {@link Graph} erleichtert.
 *
 * @author Joshua H.
 * @version 1.0 | 29.03.2022
 * */
public class GraphHandler
{

    /**
     * @return Gibt einen Graph einer modifizierte Version des 2. Labyrinths mit mehreren Wegen zurück
     * */
    public Graph createLabyrinth()
    {

        Graph graph = new Graph();

        Vertex[] vertices = {
                new Vertex("A"),
                new Vertex("B"),
                new Vertex("C"),
                new Vertex("D"),
                new Vertex("E"),
                new Vertex("F"),
                new Vertex("G"),
                new Vertex("H"),
                new Vertex("I"),
                new Vertex("Z"),
        };

        for (Vertex v : vertices)
        {
            graph.addVertex(v);
        }

        graph.addEdge(new Edge(vertices[4], vertices[8], 1));
        graph.addEdge(new Edge(vertices[7], vertices[9], 1));
        graph.addEdge(new Edge(vertices[0], vertices[1],1));
        graph.addEdge(new Edge(vertices[1],vertices[2],1));
        graph.addEdge(new Edge(vertices[1],vertices[5],1));
        graph.addEdge(new Edge(vertices[2],vertices[3],1));
        graph.addEdge(new Edge(vertices[2],vertices[4],1));
        graph.addEdge(new Edge(vertices[5],vertices[7],1));
        graph.addEdge(new Edge(vertices[5],vertices[6],1));
        graph.addEdge(new Edge(vertices[6],vertices[8],1));
        graph.addEdge(new Edge(vertices[6],vertices[9],1));


        return graph;
    }


    /**
     * @return Gibt das 2. Labyrinth als Graph zurück
     * */
    public Graph createLab2()
    {
        Graph graph = new Graph();

        Vertex[] vertices = {
            new Vertex("A"),
            new Vertex("B"),
            new Vertex("C"),
            new Vertex("D"),
            new Vertex("E"),
            new Vertex("F"),
            new Vertex("G"),
            new Vertex("H"),
            new Vertex("I"),
            new Vertex("Z"),
        };

        for (Vertex v : vertices)
        {
            graph.addVertex(v);
        }

        graph.addEdge(new Edge(vertices[0], vertices[1],1));
        graph.addEdge(new Edge(vertices[1],vertices[2],1));
        graph.addEdge(new Edge(vertices[1],vertices[5],1));
        graph.addEdge(new Edge(vertices[2],vertices[3],1));
        graph.addEdge(new Edge(vertices[2],vertices[4],1));
        graph.addEdge(new Edge(vertices[5],vertices[7],1));
        graph.addEdge(new Edge(vertices[5],vertices[6],1));
        graph.addEdge(new Edge(vertices[6],vertices[8],1));
        graph.addEdge(new Edge(vertices[6],vertices[9],1));


        return graph;
    }

    /**
     * @param graph -
     * @param id -
     * @return -
     * */
    public boolean tiefensuche(Graph graph, String id)
    {
        graph.setAllEdgeMarks(false);
        graph.setAllVertexMarks(false);

        return tiefensuche(graph, graph.getVertex("A"), id);
    }

    private boolean tiefensuche(Graph graph, Vertex v, String id)
    {
        if (v.getID().compareTo(id) == 0) return true;

        List<Vertex> vertices = graph.getNeighbours(v);
        vertices.toFirst();
        while (vertices.hasAccess())
        {
            if (graph.allEdgesMarked()) return false;

            if (!graph.getEdge(v, vertices.getContent()).isMarked())
            {
                graph.getEdge(v, vertices.getContent()).setMark(true);
                if (tiefensuche(graph, vertices.getContent(), id))
                {
                    return true;
                }
            }

            vertices.next();
        }

        return false;
    }

    public List<Vertex> searchPath(Graph graph, String startVretexName, String destinationID)
    {
        graph.setAllEdgeMarks(false);
        graph.setAllVertexMarks(false);

        return searchPath(graph, graph.getVertex(startVretexName), destinationID);
    }

    private List<Vertex> searchPath(Graph graph, Vertex currentVertex, String destinationID)
    {
        if (currentVertex.getID().compareTo(destinationID) == 0)
        {
            List<Vertex> ret = new List<>();
            ret.append(currentVertex);
            return ret;
        }

        List<Vertex> vertices = graph.getNeighbours(currentVertex);
        vertices.toFirst();
        while (vertices.hasAccess())
        {
            if (graph.allEdgesMarked()) return null;

            if (!graph.getEdge(currentVertex, vertices.getContent()).isMarked())
            {
                graph.getEdge(currentVertex, vertices.getContent()).setMark(true);
                List<Vertex> ret = searchPath(graph, vertices.getContent(), destinationID);
                if (ret != null)
                {
                    ret.append(currentVertex);
                    return ret;
                }
            }

            vertices.next();
        }

        return null;
    }


    /**
     * Liefert eine List aus Listen, welche Vertices enthalten. Die einzelnen Listen repräsentieren
     * mögliche Wege durch den Graphen.
     *
     * @param graph - Graph, welcher die Vertices beinhaltet
     * @param startVertex - Name des Vertex, an dem die Suche nach Wegen beginnt
     * @param desinationID - Name des Vertex, zu dem gesucht werden soll
     * */
    public List<List<Vertex>> searchAllPaths(Graph graph, String startVertex, String desinationID)
    {
        graph.setAllVertexMarks(false);
        return searchAllPaths(graph, graph.getVertex(startVertex), desinationID);
    }

    private List<List<Vertex>> searchAllPaths(Graph graph, Vertex currentVertex, String desinationID)
    {
        if (currentVertex.getID().compareTo(desinationID) == 0)
        {
            List<List<Vertex>> ret = new List<>();
            ret.append(new List<>());
            ret.toFirst();
            ret.getContent().append(currentVertex);
            return ret;
        }

        List<List<Vertex>> ret = new List<>();
        List<Vertex> neighbours = graph.getNeighbours(currentVertex);

        neighbours.toFirst();
        while (neighbours.hasAccess())
        {
            if (graph.allVerticesMarked()) return ret;

            if (!neighbours.getContent().isMarked())
            {
                neighbours.getContent().setMark(true);
                Graph clonedGraph = cloneGraph(graph);
                List<List<Vertex>> currentPaths = searchAllPaths(clonedGraph,
                        clonedGraph.getVertex(neighbours.getContent().getID()), desinationID);

                currentPaths.toFirst();
                while(currentPaths.hasAccess())
                {
                    currentPaths.getContent().append(currentVertex);
                    ret.append(currentPaths.getContent());
                    currentPaths.next();
                }
            }
            neighbours.next();
        }

        return ret;
    }

    /**
     * Erstellt einen neuen Graphen, der jede Kante, jeden Knoten und jede Markierung aus dem übergebenen Graphen enthält.
     *
     * @param pGraph - Graph, welcher kopiert werden soll
     * @return Gibt ein neues Graph Objekt mit einer Kopie des Inhalts zurück
     * */
    public Graph cloneGraph(Graph pGraph)
    {
        Graph lGraph = new Graph();

        List<Vertex> lVertices = pGraph.getVertices();
        lVertices.toFirst();
        while(lVertices.hasAccess())
        {
            Vertex lVertex = new Vertex(lVertices.getContent().getID());
            lVertex.setMark(lVertices.getContent().isMarked());
            lGraph.addVertex(lVertex);
            lVertices.next();
        }
        List<Edge> lEdges = pGraph.getEdges();
        lEdges.toFirst();
        while(lEdges.hasAccess())
        {
            Edge lEdge = new Edge(lGraph.getVertex(lEdges.getContent().getVertices()[0].getID()),
                    lGraph.getVertex(lEdges.getContent().getVertices()[1].getID()),1);
            lEdge.setMark(lEdges.getContent().isMarked());
            lGraph.addEdge(lEdge);
            lEdges.next();
        }

        return lGraph;
    }


    /**
     * Sucht den kürzesten Weg in einem Graphen und gibt diesen zurück
     *
     * @param pGraph - Graph, in welchem ein Weg gefunden werden soll
     * @param startVertex - Name des Startknotens
     * @param destinationID - Name des Zielknotens
     *
     * @return Liste von Knoten, welche den Weg mit den wenigesten Knoten repräsentiert
     * */
    public List<Vertex> shortestPath(Graph pGraph, String startVertex, String destinationID)
    {
        List<List<Vertex>> possiblePaths = searchAllPaths(pGraph, startVertex, destinationID);

        List<Vertex> shortestPath = null;
        int minLen = 0;

        possiblePaths.toFirst();
        while(possiblePaths.hasAccess())
        {
            int currentLen = 0;
            possiblePaths.getContent().toFirst();
            while (possiblePaths.getContent().hasAccess())
            {
                currentLen++;
                possiblePaths.getContent().next();
            }

            if (shortestPath == null || currentLen < minLen)
            {
                shortestPath = possiblePaths.getContent();
                minLen = currentLen;
            }

            possiblePaths.next();
        }

        return shortestPath;
    }

    public void printPath(List<Vertex> path)
    {
        System.out.println("Path:");
        path.toFirst();
        while (path.hasAccess())
        {
            System.out.print(path.getContent().getID()+ " ");
            path.next();
        }
    }

    public void printPaths(List<List<Vertex>> paths)
    {
        paths.toFirst();
        while (paths.hasAccess())
        {
            printPath(paths.getContent());
            System.out.println(" ");
            paths.next();
        }
    }

    public List<Vertex> breitensuche()
    {

        return null;
    }
    /**
    public double[][] graphToAdjazenzmatrix(Graph pGraph) {
        List<Vertex> lVertexListX= pGraph.getVertices();
        List<Vertex> lVertexListY= pGraph.getVertices();
        double[][] lMatrix = new double[getLengthOfList(lVertexListX)][getLengthOfList(lVertexListY)];
        lVertexListX.toFirst();
        int x = 0;
        while (lVertexListX.hasAccess()) {

            List<Vertex> lNeighbourList = pGraph.getNeighbours(lVertexListX.getContent());
            lVertexListY.toFirst();
            int y = 0;
            while (lVertexListY.hasAccess()) {

                lNeighbourList.toFirst();
                while (lNeighbourList.hasAccess()) {
                    if (lVertexListY.getContent() == lNeighbourList.getContent()) {
                        lMatrix[x][y] = mGraph.getEdge(lVertexListX.getContent(),lVertexListY.getContent()).getWeight();
                    }
                    lNeighbourList.next();
                }
                lVertexListY.next();
                y++;
            }
            lVertexListX.next();
            x++;
        }
        return lMatrix;
    }
    **/

    public static void main(String[] args) {
        GraphHandler handler = new GraphHandler();
        Graph graph = handler.createLabyrinth() ;

        List<List<Vertex>> paths = handler.searchAllPaths(graph, "A", "Z");
        handler.printPaths(paths);

        System.out.println("\nShortest:");
        handler.printPath(handler.shortestPath(graph,"A","Z"));
    }
}
