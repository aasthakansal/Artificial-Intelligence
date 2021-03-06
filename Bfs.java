/*
Chaitrali Londhe
421075
*/
package bfs;
import java.util.*;
public class Bfs {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("How many nodes are there in the graph? :");
        int nodes_count;
        nodes_count = scan.nextInt();
        Algorithm a = new Algorithm(nodes_count); 
        a.getter(); //input
        a.method(); //bfs algorithm
    }    
}

class Node { //node in graph
    int no;
    int heuristic;
    int parent;

    public Node(int no, int heuristic) {
        this.no = no;
        this.heuristic = heuristic;
        parent = 0;
    }   
}

class HeuristicComparator implements Comparator<Node>{ //to sort nodes in increasing order of heuristic in open priority queue

            @Override
            public int compare(Node s1, Node s2) {
                if (s1.heuristic > s2.heuristic)
                    return 1;
                else if (s1.heuristic < s2.heuristic)
                    return -1;
                                return 0;
                }
        }

class Algorithm {
    int GOAL;
    int START;
    int graph[][];
    ArrayList<Node> closed = new ArrayList<>();
    PriorityQueue<Node> open = new PriorityQueue<Node>(10,new HeuristicComparator());
    HashMap<Integer,Node> nodetrack = new HashMap<>();
    public Scanner scan = new Scanner(System.in);
    
    Algorithm(int nodes_count) {
        graph = new int[nodes_count][nodes_count];
    }
    
    void movegen(int n)  //generates children of node n
    {
    	Node node;
        for(int i = 0; i<graph.length; i++)
        {
            if(graph[n-1][i] == 1)
            {
                node = nodetrack.get(i+1); //code to assign parent to generated nodes                
                if(!closed.contains(node) && !open.contains(node)) 
                {
                	node.parent = n;
                	nodetrack.put(i+1, node);
                        
                        open.add(node);  //not seen node added to open list
                }
            }
        }
    }
    
/*  public void removeseen() //removes already seen children and adds left ones to open list
    {
        for(int i: children)
        {
            Node n = nodetrack.get(i);
            if(!closed.contains(n) && !open.contains(n))
            {
                open.add(n);
            }
        }
        children.clear();
    }
*/
    
    public void printpath() //function to print path
    {
        System.out.println("path from goal to start node is: ");
        int next = GOAL;
        while(next>0)
        {
            System.out.print(next+" -> ");
            Node n = nodetrack.get(next);
            next = n.parent;
        }
    }
    
    public void printclosed() //function to print explored nodes
    {
        System.out.println("nodes being explored: ");
        for(Node n:closed)
        {
            System.out.print(n.no+" ");
        }
        System.out.println();
    }
    
    public void method() //fuction to search goal state
    {
        open.add(nodetrack.get(START));
        while(!open.isEmpty())
        {
            Node node = open.remove();
            if(node.no == GOAL)
            {
                System.out.println("found");
                printclosed();
                printpath();
                return;
            }
            else
            {
                closed.add(node);
                movegen(node.no);
            }
        }
    }

    public void getter() { //function to get input
        int hval;
        System.out.print("\nEnter GOAL: ");
        GOAL = scan.nextInt();
        System.out.print("\nEnter START: ");
        START = scan.nextInt();
        System.out.println("Enter your graph:");
        for (int i=0;i<graph.length;i++) {
            for(int j=0;j<graph.length;j++){
                if(i>=j) continue;
                System.out.printf("\nIs there an edge between nodes %d and %d :",i+1,j+1);
                graph[i][j] = scan.nextInt();
                graph[j][i] = graph[i][j];
            }
        }
        System.out.print("Enter heuristic values for each node: ");
        
       for(int i = 1; i <= graph.length; i++)
       {
           System.out.printf("\n Enter H value for node %d :",i);
           hval = scan.nextInt();
           Node n = new Node(i,hval);
           //nodes.add(n);
           nodetrack.put(i,n);
       }
                
        System.out.println("The entered graph is:");
        int k = 1;
        for(int i[] : graph) {
            System.out.print(k+"\t");
            k++;
            for (int j : i) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}

/*
OUTPUT

How many nodes are there in the graph? :10

Enter GOAL: 10

Enter START: 1
Enter your graph:

Is there an edge between nodes 1 and 2 :1

Is there an edge between nodes 1 and 3 :1

Is there an edge between nodes 1 and 4 :1

Is there an edge between nodes 1 and 5 :0

Is there an edge between nodes 1 and 6 :0

Is there an edge between nodes 1 and 7 :0

Is there an edge between nodes 1 and 8 :0

Is there an edge between nodes 1 and 9 :0

Is there an edge between nodes 1 and 10 :0

Is there an edge between nodes 2 and 3 :1

Is there an edge between nodes 2 and 4 :0

Is there an edge between nodes 2 and 5 :1

Is there an edge between nodes 2 and 6 :1

Is there an edge between nodes 2 and 7 :0

Is there an edge between nodes 2 and 8 :0

Is there an edge between nodes 2 and 9 :0

Is there an edge between nodes 2 and 10 :0

Is there an edge between nodes 3 and 4 :1

Is there an edge between nodes 3 and 5 :0

Is there an edge between nodes 3 and 6 :0

Is there an edge between nodes 3 and 7 :1

Is there an edge between nodes 3 and 8 :1

Is there an edge between nodes 3 and 9 :0

Is there an edge between nodes 3 and 10 :0

Is there an edge between nodes 4 and 5 :0

Is there an edge between nodes 4 and 6 :0

Is there an edge between nodes 4 and 7 :0

Is there an edge between nodes 4 and 8 :0

Is there an edge between nodes 4 and 9 :1

Is there an edge between nodes 4 and 10 :0

Is there an edge between nodes 5 and 6 :0

Is there an edge between nodes 5 and 7 :0

Is there an edge between nodes 5 and 8 :0

Is there an edge between nodes 5 and 9 :0

Is there an edge between nodes 5 and 10 :0

Is there an edge between nodes 6 and 7 :1

Is there an edge between nodes 6 and 8 :0

Is there an edge between nodes 6 and 9 :0

Is there an edge between nodes 6 and 10 :0

Is there an edge between nodes 7 and 8 :0

Is there an edge between nodes 7 and 9 :0

Is there an edge between nodes 7 and 10 :0

Is there an edge between nodes 8 and 9 :0

Is there an edge between nodes 8 and 10 :1

Is there an edge between nodes 9 and 10 :0
Enter heuristic values for each node: 
 Enter H value for node 1 :28

 Enter H value for node 2 :25

 Enter H value for node 3 :18

 Enter H value for node 4 :16

 Enter H value for node 5 :40

 Enter H value for node 6 :30

 Enter H value for node 7 :4

 Enter H value for node 8 :8

 Enter H value for node 9 :39

 Enter H value for node 10 :0
The entered graph is:
1	0 1 1 1 0 0 0 0 0 0 
2	1 0 1 0 1 1 0 0 0 0 
3	1 1 0 1 0 0 1 1 0 0 
4	1 0 1 0 0 0 0 0 1 0 
5	0 1 0 0 0 0 0 0 0 0 
6	0 1 0 0 0 0 1 0 0 0 
7	0 0 1 0 0 1 0 0 0 0 
8	0 0 1 0 0 0 0 0 0 1 
9	0 0 0 1 0 0 0 0 0 0 
10	0 0 0 0 0 0 0 1 0 0 
found
nodes being explored: 
1 4 3 7 8 
path from goal to start node is: 
10 -> 8 -> 3 -> 1 ->
*/
