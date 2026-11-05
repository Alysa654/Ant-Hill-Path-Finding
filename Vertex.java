import java.util.ArrayList;

public class Vertex {
    ArrayList<Edge> listOfEdges;
    int myIndex;

    public Vertex(int index){
        myIndex = index;
        listOfEdges = new ArrayList<Edge>();
    }

    public void addEdge(Edge newEdge){
        listOfEdges.add(newEdge);
    }
}
