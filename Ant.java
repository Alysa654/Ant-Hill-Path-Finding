import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ant {
    Vertex currVertex;
    Vertex anthill;

    public Ant(Vertex anthill){
        currVertex = anthill;
        this.anthill = anthill;
    }

    public void print(){
        System.out.print("Currently at: ");
        currVertex.print();
    }

    public void findFood(int foodSource, int seed, Graph g){
        Vertex v = g.findVertex(foodSource);
        findFood(v, seed, g);
    }

    public void findFood(Vertex foodSource, int seed, Graph g){
        boolean lookForFood = true;
        boolean goHome = false;
        Random r = new Random(seed);
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
                Edge[] listEdges = new Edge[size];

                for(int j = 0; j<size;j++){
                    listEdges[j] = currVertex.listOfEdges.get(j);
                }
                Integer[] amountAnts = new Integer[size];

                Arrays.sort(listEdges);
                
                int amountOfAnts = 0;

                
                int indexForEdge = -1;
                if(amountOfAnts==0){
                    for(int j = 0; j<size;j++){
                        amountOfAnts += listEdges[j].amountOfAnts+1;
                        amountAnts[j] = listEdges[j].amountOfAnts+1;
                    }
                }
                else{
                    for(int j = 0; j<size;j++){
                        amountOfAnts += listEdges[j].amountOfAnts;
                        amountAnts[j] = listEdges[j].amountOfAnts;
                    }
                }
                double num = r.nextDouble();
                int totalAnts = 0;
                for(int j = 0; j<size;j++){
                    double sum = (double)(listEdges[j].amountOfAnts+totalAnts);
                    sum = sum/amountOfAnts;
                    if(num <= sum){
                        indexForEdge = j;
                    }
                    else{
                        totalAnts+=listEdges[j].amountOfAnts;
                    }
                }

                if(indexForEdge == -1){
                    indexForEdge = r.nextInt(size);
                }
                
                Edge e = listEdges[indexForEdge];
                e.amountOfAnts +=1;
                currVertex = g.findVertex(e.getOtherSide(currVertex.myIndex));
                
            }
           i++;
        }
        // System.out.println("Look For Food Done");
        i = 0;
        currVertex.print();
        System.out.println("Going Home.");
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
                        currVertex = g.findVertex(e.getOtherSide(currVertex.myIndex));
                        e.amountOfAnts+=1;
                        j=minEdges.size();
                    }
                }
                
            }
            i++;
        }
        currVertex.print();
    }
}
