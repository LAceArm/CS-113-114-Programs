import java.util.LinkedList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class Reach {

    static int dd;
    static int distance;
    static ArrayList <Integer> sequence;
    static Dictionary <Integer, Integer> parent;
    public static void main(String[] args) {
        int n;
        distance=0;

        /*
         * We don't know in advance the size of the graph, so let's keep
         * track of the edges as we read them in; we will add them to the
         * graph later.
         */
        sequence=new ArrayList <Integer>();
        ArrayList<Integer> From = new ArrayList<Integer>();
        ArrayList<Integer> To = new ArrayList<Integer>();

        int max = 0; // Maximum vertex number so we know
                     // how large a graph to build.
        File file = new File("web.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextInt()) {
                int src = scanner.nextInt();
                int dst = scanner.nextInt();
                if(src>max) max=src;
                if(dst>max) max=dst;
                From.add(src);
                To.add(dst);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("max= "+max+" From size= "+From.size());
        Graphl graph = new Graphl(max+1);

        n = max;
        for(int i=0; i<From.size(); ++i) {
            graph.setEdge(From.get(i), To.get(i),1);
        }
        System.out.println("Built graph");

        Scanner input = new Scanner(System.in);
            System.out.println("Enter source and destination:");
            int s = input.nextInt();
            int d = input.nextInt();
            while((s<0) || (d<0)){
               System.out.print("Source or Destination below zero");
               System.out.println("Please enter a valid source and destination: ");
                  s = input.nextInt();
                  d = input.nextInt();
            } 
                 
            DFS(graph,s); 
           if(sequence.contains(d)){
                pathReconstruction(graph,s,d);
            }
            else{
               System.out.println(d+" is unreachable from "+s);
            }
        
        input.close();
    }
      /** Depth first search */
  static void DFS(Graph G, int v) {
    PreVisit(G, v); // Take appropriate action
    distance++;
    G.setMark(v, 1);
    for (int w = G.first(v); w < G.n(); w = G.next(v, w))
      if (G.getMark(w) == 0)
        DFS(G, w);
    PostVisit(G, v); // Take appropriate action
  }


  /** Breadth first (queue-based) search */
  static void BFS(Graph G, int start) {
    LinkedList<Integer> Q = new LinkedList<Integer>();
    Q.addLast(start);
    G.setMark(start, 1);
    int d = 0;
    while (Q.size() > 0) { // Process each vertex on Q
      ++d;
  //    System.out.println("Distance: "+d);
      int v = Q.removeFirst();
      PreVisit(G, v); // Take appropriate action
      for (int w = G.first(v); w < G.n(); w = G.next(v, w))
        if (G.getMark(w) == 0) { // Put neighbors on Q
          G.setMark(w, G.getMark(v) + 1);
          Q.addLast(w);
        }

      PostVisit(G, v); // Take appropriate action
    }
  }
    static void ABFS(Graph G,int start){
       LinkedList<Integer> q = new LinkedList<Integer>();
       G.setMark(start,0);
       q.addLast(start);
       while(q.size()>0){
         int v=q.removeFirst();
         for(int w=G.first(v);w<G.n();w=G.next(v,w)){
            if(G.getMark(w)==1){
               parent.insert(w,v);
               G.setMark(w,G.getMark(v)+1);
               q.addLast(w);
            }
         }
       }
   }
   static void pathReconstruction(Graph G,int start,int d){
      parent=new UALDictionary<Integer,Integer>();
      ABFS(G,start);
      int distance=G.getMark(d)-1;
      System.out.println("Distance: "+distance); 
      ArrayList <Integer> shortestPath=new ArrayList<Integer>();
      int curr=d;
      while(curr!=start){
            shortestPath.add(curr);
            curr=parent.find(curr);
      }
      shortestPath.add(start);
      System.out.print("Path: ");
      for(int i=shortestPath.size()-1;i>=0;i--)
         System.out.print(shortestPath.get(i)+" ");
    parent.clear();
     System.out.println();
   
  } 

  static void PreVisit(Graph G, int v) {
    //System.out.println("");
  }

  static void PostVisit(Graph G, int v) {
   // System.out.println("Visited "+ v);
    sequence.add(v);
  }

}
