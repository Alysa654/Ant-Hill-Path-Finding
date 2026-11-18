import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Ant {
    Vertex currVertex;
    Vertex anthill;
    LinkedList<Vertex> path;


    public Ant(Vertex anthill){
        currVertex = anthill;
        this.anthill = anthill;
        path = new LinkedList<Vertex>();
    }

    public void print(){
        System.out.print("Currently at: ");
        currVertex.print();
    }

    public void findFood(int foodSource, int seed, Graph g){
        Vertex v = g.findVertex(foodSource);
        findFood(v, seed, g);
    }

    public void deletePath(){
        if (!path.isEmpty()){
            path.clear();
        }
    }

    public void printPath(){
        for(int i = 0; i<path.size(); i++){
            Vertex v = path.get(i);
            System.out.println("Step "+i+": Vertex "+v.getIndex());
        }
    }
    public int getPathLength(){
        // System.out.println("Path Length: " + path.size());
        return  path.size();
    }

    public void findFood(Vertex foodSource, int seed, Graph g){
        boolean lookForFood = true;
        boolean goHome = true;
        Random r = new Random(seed);
        LinkedList<Edge> linkedListOfEdges = new LinkedList<Edge>();
        // System.out.println("Finding Food.");
         /*
                check if this vertex == foodsource
                if yes: 
                    lookforfood = false
                    goHome = true
                if no: 
                    pick random edge in list of edges and go to the other vertex
                    add one to amount of ants on edge
             */
            int i = 0;
        while (lookForFood && i < 1000) {
            if(currVertex == foodSource){
                lookForFood = false;
                goHome = true;
            }
            else{
                // currVertex = g.findVertex(e.getOtherSide(currVertex.myIndex));
                int size = currVertex.listOfEdges.size();
                int indexForEdge = -1;
                

                if(indexForEdge == -1){
                    indexForEdge = r.nextInt(size);
                }
                
                Edge e = currVertex.listOfEdges.get(indexForEdge);
                e.amountOfAnts +=1;
                linkedListOfEdges.add(e);
                // path.add(currVertex);
                currVertex = g.findVertex(e.getOtherSide(currVertex.myIndex));
                
            }
           i++;
        }
        // System.out.println("Look For Food Done");
        if(i==1000){
            for(Edge e : linkedListOfEdges){
                e.amountOfAnts -=1;
            }
            goHome = false;
        }
        i = 0;
        // currVertex.print();
        // System.out.println("Going Home.");
        while (goHome && i<1000) {
            if (currVertex == anthill){
                goHome = false;
            }
            /*
                find all edges with equal distanceToHome
                rank those by numOfAnts
                pick one and go to next vertex
            */
            else{
                ArrayList<Edge> minEdges = new ArrayList<Edge>();
                minEdges.ensureCapacity(currVertex.listOfEdges.size());
                int totalAnts = 0;
                for(Edge e: currVertex.listOfEdges){
                    if(e.distanceToAntHill == currVertex.getSmallestDistance()){
                        minEdges.add(e);
                        totalAnts = totalAnts + e.amountOfAnts;
                    }
                }
                if (totalAnts == 0){totalAnts+=1;}
                int index = r.nextInt(totalAnts);
                for(int j = 0; j<minEdges.size();j++){
                    Edge e = minEdges.get(j);
                    index = index - e.amountOfAnts;
                    if(index <= 0 ){
                        path.add(currVertex);
                        currVertex = g.findVertex(e.getOtherSide(currVertex.myIndex));
                        e.amountOfAnts+=1;
                        j=minEdges.size();
                    }
                }
                
            }
            i++;
        }
        if(i==1000){
            for(Edge e : linkedListOfEdges){
                e.amountOfAnts -=1;
            }
        }
        // currVertex.print();
    }
}
