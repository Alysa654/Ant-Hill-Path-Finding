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
        listOfEdges = new ArrayList<Edge>();
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
        ArrayList<Edge> copyListEdges = new ArrayList<Edge>();

        //goes through the list once, updates the distanceToAntHill or adds to a list to updateLater
        //Searches through each vertex to find shortest distance, then adds one to assign to itself
        for(int i = 0; i<listOfEdges.size();i++){
            if(listOfEdges.get(i).equals(1)){
                listOfEdges.get(i).distanceToAntHill = 1;
            }
            else{
                Edge e = listOfEdges.get(i);
                int minDistance = Integer.MAX_VALUE;

                //edge A
                for(int j = 0; j<verticies[e.edgeA].listOfEdges.size();j++){

                    if(verticies[e.edgeA].listOfEdges.get(j).distanceToAntHill!=-1 && verticies[e.edgeA].listOfEdges.get(j).distanceToAntHill<minDistance){
                        minDistance = verticies[e.edgeA].listOfEdges.get(j).distanceToAntHill;
                    }
                }
                //edge B
                for(int j = 0; j<verticies[e.edgeB].listOfEdges.size();j++){
                    if(verticies[e.edgeB].listOfEdges.get(j).distanceToAntHill!=-1 && verticies[e.edgeB].listOfEdges.get(j).distanceToAntHill<minDistance){
                        minDistance = verticies[e.edgeB].listOfEdges.get(j).distanceToAntHill;
                    }
                }

                if(minDistance != Integer.MAX_VALUE){
                    e.distanceToAntHill = minDistance + 1;
                }
                else{
                    // copyListEdges.add(e);
                    copyListEdges.add(e);
                }
            }
        }

        //Now to Update the edges we couldn't update earlier
        int i = 0;
        while (!copyListEdges.isEmpty() && i<1000) {
            Edge e = copyListEdges.removeFirst();
            int minDistance = Integer.MAX_VALUE;
            i+=1;
            //edge A
            for(int j = 0; j<verticies[e.edgeA].listOfEdges.size();j++){
                if(verticies[e.edgeA].listOfEdges.get(j).distanceToAntHill!=-1 && verticies[e.edgeA].listOfEdges.get(j).distanceToAntHill<minDistance){
                    minDistance = verticies[e.edgeA].listOfEdges.get(j).distanceToAntHill;
                }
            }
            //edge B
            for(int j = 0; j<verticies[e.edgeB].listOfEdges.size();j++){
                if(verticies[e.edgeB].listOfEdges.get(j).distanceToAntHill!=-1 && verticies[e.edgeB].listOfEdges.get(j).distanceToAntHill<minDistance){
                    minDistance = verticies[e.edgeB].listOfEdges.get(j).distanceToAntHill;
                }
            }

            if(minDistance != Integer.MAX_VALUE){
                e.distanceToAntHill = minDistance + 1;
            }
            else{
                copyListEdges.add(10,e);
            }
        }
    }


    public void print(){
        for(Edge e : listOfEdges){
            e.print();
        }
    }


}
