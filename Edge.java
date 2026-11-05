
public class Edge {
    Integer edgeA;
    Integer edgeB;

    int amountOfAnts;
    int distanceToAntHill;
    
    public Edge(int a, int b){
        edgeA = a;
        edgeB = b;
        amountOfAnts = 0;
        distanceToAntHill = -1;
    }

    @Override 
    public boolean equals(Object o){
        if (o instanceof Integer){
            return edgeA.equals(o) || edgeB.equals(o);
        }
        if (o instanceof Edge){
            boolean result1 = false;
            result1 = ((Edge)o).edgeA == this.edgeA;
            result1 = result1 && ((Edge)o).edgeB == this.edgeB;

            boolean result2 = ((Edge)o).edgeA == this.edgeB;
            result2 = result2 && ((Edge)o).edgeB == this.edgeA;

            return result1 || result2;
        }
        return false;
    }

    // public boolean equals(Vertex vertex){
    //     return edgeA.equals(vertex) || edgeB.equals(vertex);
    // }
    // public boolean equals(int index){
        // Vertex vertex = new Vertex(index);
        // return edgeA.equals(vertex) || edgeB.equals(vertex);
    // }

    public void print(){
        System.out.println(edgeA + " " + edgeB + " Distance To Ant Hill " + distanceToAntHill);
    }

}
