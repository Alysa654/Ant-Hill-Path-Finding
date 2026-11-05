import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        listOfEdges.ensureCapacity(189);
        addEdges();
        calculateDistanceToAntHill();
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

                //adds the edges to the graph
                adjacencyMatrix[edgeA-1][edgeB-1]=1;
                if(listOfEdges.isEmpty() || !listOfEdges.contains(new Edge(edgeB, edgeA))){
                    listOfEdges.add(new Edge(edgeA, edgeB));
                    verticies[edgeA].addEdge(listOfEdges.getLast());
                    verticies[edgeB].addEdge(listOfEdges.getLast());
                }
            }
            //Error Handling for file not existing
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private void calculateDistanceToAntHill(){

    }
}
