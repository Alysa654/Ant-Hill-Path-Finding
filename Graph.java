import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
    Integer[] verticies;
    Integer[][] adjacencyMatrix;

    //creates a graph
    public Graph(int numOfVerts){
        verticies = new Integer[numOfVerts];
        adjacencyMatrix = new Integer[numOfVerts][numOfVerts];
        addEdges();
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
                tokenString = myReader.next();
                int edgeA = Integer.parseInt(tokenString);
                tokenString = myReader.next();
                int edgeB = Integer.parseInt(tokenString);
                // tokenString = edgeA + " " + edgeB;
                adjacencyMatrix[edgeA-1][edgeB-1]=1;
            }
            // Testing for reaching end of file.
            // System.out.println(tokenString);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
