import java.util.LinkedList;
import java.util.Random;

public class antHillSim {
    
    // Your program begins with a call to main()
    
    public static void main(String[] args)
    {
        int seed = 5;
        // creates a new graph currently hardcoded to 126 verticies
        Graph g = new Graph(126);

        System.out.println("G has been made");

        int amountOfAnts = 100;
        //For simplicity the AntHill is always vertex 1.
        Vertex antHill = g.findVertex(1);
        Ant explorerAnt = new Ant(antHill);
        int sumOfPathLengths = 0;
        int totalAntsThatMadeIt = 0;
        Count c = new Count();
        // Random r = new Random();
        for (int i = 0; i<amountOfAnts; i++){
            explorerAnt.deletePath();
            // if(r.nextDouble() < .01){
            //     explorerAnt.findFood(r.nextInt(126), seed+i, g);
            // }
            // else {
                explorerAnt.findFood(122, seed+i, g);
            // }
            sumOfPathLengths += explorerAnt.getPathLength();
            System.out.println("Path Length: "+explorerAnt.getPathLength());
            if (explorerAnt.getPathLength() != 0){
                totalAntsThatMadeIt +=1;
                c.add(explorerAnt.getPathLength());
            }
            
            // explorerAnt.print();
        }

        // g.print();
        // explorerAnt.printPath();
        Vertex v = g.findVertex(122);
        System.out.println("122 dist to ant hill: "+ v.getSmallestDistance());
        System.out.println("Average Path Length: "+ sumOfPathLengths/totalAntsThatMadeIt);
        c.printPathLengths();
        /*
            We have a 100 ants.
            We broadcast the graph to all the ants
            All ants search for a random food source
            
        */
    }
}