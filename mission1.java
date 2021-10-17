import org.w3c.dom.Node;
import java.util.*;


class mission1
{
    private int numVertices;
    private LinkedList<Integer> adjLists[];
    private boolean visited[];

    mission1(int vertices)
    {
        numVertices = vertices;
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<Integer>();
    }

    void addEdge(int src, int dest)
    {
        adjLists[src].add(dest);
    }


    void DFS(int vertex)
    {
        visited[vertex] = true;
        System.out.print(vertex + 1 + " ");

        ListIterator<Integer> ite = adjLists[vertex].listIterator();

        while (ite.hasPrevious()) {
            int adj = ite.previous();
            if (!visited[adj])
                DFS(adj);
        }

        while (ite.hasNext()) {
            int adj = ite.next();
            if (!visited[adj])
                DFS(adj);
        }
    }

    public static void DFS1(int[][] matrix, boolean[] visited,int n, int i, int p){
        p++;
        System.out.print((i + 1) + " ");
        visited[i]= true;
        for (int j = 0; j<n;j++){
            if(!(visited[j]) && matrix[i][j]==1){
                if (p == 5)
                    DFS2(matrix, visited, n, j);
                DFS1(matrix, visited, n, j, p);
            }
        }
    }

    public static void DFS2(int[][] matrix, boolean[] visited,int n, int i){
        int s = 0;
        for (int j = 0; j < n; j++){
            for (int p = 0; p < n; p++){
                if(!(visited[j]) && matrix[i][j]==1) {
                    s++;
                    if (s > n*n) {
                        System.out.print((i + 1) + " ");
                        visited[i] = true;
                    }
                }
            }
        }
    }

    static void rand(int n, int[][] matrix){
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if(i == j)
                    matrix[i][j] = 0;
                else{
                    matrix[i][j] = (int)(( Math.random() * 2) + 0);
                    matrix[j][i] = matrix[i][j];
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String args[])
    {
        System.out.println("Введите размер массива:");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        int p = 0;
        boolean[] visited = new boolean[n];
        rand(n, matrix);

        for (int i = 0; i < n; i++){
        visited[i] = false;
        }

        mission1 g = new mission1(n);

        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if(matrix[i][j] == 1) {
                    g.addEdge(i, j);
                }
            }
        }

        System.out.println("\nFollowing is Depth First Traversal");
        DFS1(matrix, visited, n, 0, p);

        System.out.println("\n\nFollowing is Depth Second Traversal");
        g.DFS(0);
    }
}