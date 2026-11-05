
public class Edge {
    Integer edgeA;
    Integer edgeB;

    int amountOfAnts;
    int distanceToAntHill;

    public Edge(int a, int b){
        edgeA = a;
        edgeB = b;
        amountOfAnts = 0;
        distanceToAntHill = 1;
    }
}
