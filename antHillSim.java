

public class antHillSim {
    
    // Your program begins with a call to main()
    
    public static void main(String[] args)
    {
        // creates a new graph currently hardcoded to 126 verticies
        Graph g = new Graph(126);

        System.out.println("G has been made");

        //finds all possible paths between a and AntHill. 
        //For simplicity the AntHill is always vertex 1.

        g.print();

    }
}