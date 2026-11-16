

public class antHillSim {
    
    // Your program begins with a call to main()
    
    public static void main(String[] args)
    {
        int seed = 5;
        // creates a new graph currently hardcoded to 126 verticies
        Graph g = new Graph(126);

        System.out.println("G has been made");

        int amountOfAnts = 10;
        //For simplicity the AntHill is always vertex 1.
        Vertex antHill = g.findVertex(1);
        Ant explorerAnt = new Ant(antHill);
        for (int i = 0; i<amountOfAnts; i++){
            explorerAnt.findFood(122, seed+i, g);
            explorerAnt.print();
        }

        // g.print();
        /*
            We have a 100 ants.
            We broadcast the graph to all the ants
            All ants search for a random food source
            
        */

    }
}