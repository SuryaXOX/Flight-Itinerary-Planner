import java.util.LinkedList;

public class Airport<T> {

public T data; // vertex label or application specific data
    public LinkedList<Edge> edgesLeaving;

    public Airport(T data) {
      this.data = data;
      this.edgesLeaving = new LinkedList<>();
    }
    protected class Edge {
      public Airport target;
      public int weight;

      public Edge(Airport target, int weight) {
        this.target = target;
        this.weight = weight;
      }
    }
}
