import java.util.Random;

public class antHillSim {
    
    // Your program begins with a call to main()
    
    public static void main(String[] args)
    {
        int seed = 5;
        // creates a new graph currently hardcoded to 126 verticies
        Graph g = new Graph(126);

        System.out.println("G has been made");

        int amountOfAnts = 100000;
        //For simplicity the AntHill is always vertex 1.
        Vertex antHill = g.findVertex(1);
        Ant explorerAnt = new Ant(antHill);
        int sum = 0;
        int total = 0;
        Random r = new Random();
        for (int i = 0; i<amountOfAnts; i++){
            explorerAnt.deletePath();
            if(r.nextDouble() < .1){
                explorerAnt.findFood(r.nextInt(126), seed+i, g);
            }
            else {
                explorerAnt.findFood(122, seed+i, g);
            }
            sum += explorerAnt.getPathLength();
            if (explorerAnt.getPathLength() != 0){
                total +=1;
            }
            // explorerAnt.print();
        }

        // g.print();
        // explorerAnt.printPath();
        Vertex v = g.findVertex(122);
        System.out.println("122 dist to ant hill: "+ v.getSmallestDistance());
        System.out.println("Average Path Length: "+ sum/total);
        /*
            We have a 100 ants.
            We broadcast the graph to all the ants
            All ants search for a random food source
            
        */
    }
}