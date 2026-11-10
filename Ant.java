import java.util.ArrayList;
import java.util.Random;

public class Ant {
    Vertex currVertex;
    Vertex anthill;

    public Ant(Vertex anthill){
        currVertex = anthill;
        this.anthill = anthill;
    }

    public void findFood(Vertex foodSource, int seed, Graph g){
        boolean lookForFood = true;
        boolean goHome = false;
        Random r = new Random(seed);
         /*
                check if this vertex == foodsource
                if yes: 
                    lookforfood = false
                    goHome = true
                if no: 
                    pick random edge in list of edges and go to the other vertex
                    add one to amount of ants on edge
             */
        while (lookForFood) {
            if(currVertex == foodSource){
                lookForFood = false;
                goHome = true;
            }
            else{
                int range = currVertex.listOfEdges.size();
                int index = r.nextInt(range);
                Edge e = currVertex.listOfEdges.get(index);

                e.amountOfAnts +=1;
                currVertex = g.findVertex(e.getOtherSide(currVertex.myIndex));
            }
           
        }
        while (goHome) {
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
                int index = r.nextInt(totalAnts);
                for(int i = 0; i<minEdges.size();i++){
                    Edge e = minEdges.get(i);
                    index = index - e.amountOfAnts;
                    if(index <= 0 ){
                        currVertex = g.findVertex(e.getOtherSide(currVertex.myIndex));
                        i=minEdges.size();
                    }
                }
                
            }
        }
    }
}
