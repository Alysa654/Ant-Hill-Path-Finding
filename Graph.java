import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
    Vertex[] verticies;
    Integer[][] adjacencyMatrix;
    ArrayList<Edge> listOfEdges;

    //creates a graph
    public Graph(int numOfVerts){
        verticies = new Vertex[numOfVerts+1];
        adjacencyMatrix = new Integer[numOfVerts][numOfVerts];
        //hardcoded for the permament file, should change later
        listOfEdges = new ArrayList<Edge>();
        listOfEdges.ensureCapacity(189);

        addEdges();
        try {
            calculateDistanceToAntHill();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //adds edges to a random graph. Currently Hardcoded to one graph with 126 verticies. 
    private void addEdges(){
        String filepath = "tsphcp/GP3_126.hcp";
        File file = new File(filepath);
        try (Scanner myReader = new Scanner(file)) {
            for (int i = 0; i<6; i++){
                myReader.nextLine();
            }
            String tokenString = "";
            // test = myReader.next();
            while (myReader.hasNext() && !myReader.hasNext("-1")) {
                //gets the edges from the file
                tokenString = myReader.next();
                int edgeA = Integer.parseInt(tokenString);
                tokenString = myReader.next();
                int edgeB = Integer.parseInt(tokenString);

                Edge e = new Edge(edgeA, edgeB);
                //adds the edges to the graph
                adjacencyMatrix[edgeA-1][edgeB-1]=1;
                if(listOfEdges.isEmpty() || !(listOfEdges.contains(e))){
                    listOfEdges.add(e);
                     //edgeA
                    if(verticies[edgeA] != null){
                        verticies[edgeA].addEdge(e);
                    }
                    else{
                        verticies[edgeA] = new Vertex(edgeA);
                        verticies[edgeA].addEdge(e);
                    }
                    //edgeB
                    if(verticies[edgeB] != null){
                        verticies[edgeB].addEdge(e);
                    }
                    else{
                        verticies[edgeB] = new Vertex(edgeB);
                        verticies[edgeB].addEdge(e);
                    }
                }
            }
            //Error Handling for file not existing
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private void calculateDistanceToAntHill(){
        LinkedList<Edge> edgeList = new LinkedList<Edge>();
        LinkedList<Integer> prevIndexes = new LinkedList<Integer>();
        ArrayList<Edge> foundEdges = new ArrayList<Edge>();
        Vertex currVertex = verticies[1];
        Vertex prevVertex = verticies[1];

        for(Edge e: currVertex.listOfEdges){
            edgeList.add(e);
            prevIndexes.add(currVertex.myIndex);
        }
        while (!edgeList.isEmpty()) {
            Edge e = edgeList.removeFirst();
            int prevIndex = prevIndexes.removeFirst();
            foundEdges.add(e);

            if(currVertex.myIndex == 1){
                e.distanceToAntHill = 1;
            }
            else{
                prevVertex = findVertex(prevIndex);
                e.distanceToAntHill = prevVertex.getSmallestDistance()+1;
            }
            prevVertex = currVertex;
            currVertex = findVertex(e.edgeB);
            for(Edge e1: currVertex.listOfEdges){
                if(!foundEdges.contains(e1)){
                    edgeList.add(e1);
                    prevIndexes.add(prevVertex.myIndex);
                }
            }
        }
    }

    public Vertex findVertex(int index){
        return verticies[index];
    }

    public void print(){
        for(Edge e : listOfEdges){
            e.print();
        }
    }


}
