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

    @Override 
    public boolean equals(Object o){
        if (o instanceof Vertex){
            return ((Vertex)o).myIndex == this.myIndex;
        }
        if (o instanceof Integer){
            return (Integer)o == this.myIndex;
        }
        return false;
    }

    public int getSmallestDistance(){
        int smallestDistance = Integer.MAX_VALUE;
        for (Edge e: listOfEdges){
            if (e.distanceToAntHill < smallestDistance){
                smallestDistance = e.distanceToAntHill;
            }
        }
        return smallestDistance;
    }
}
