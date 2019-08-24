package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private int INFINITY = 999; // POSITIVE_INFINITY

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);

        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source.
     *
     * {@code edgeTo[]} is a map that helps us track how we got to node n
     * {@code distTo[]} is a map that helps us track how far n is from the starting vertex
     *
     * */
    private void bfs() {

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < maze.V(); i++) {    // Initialize the fringe (a queue with the starting vertex)
            distTo[i] = INFINITY;
        }

        distTo[s] = 0;
        marked[s] = true;                       // Mark that vertex.
        announce();

        q.add(s);
        while (!q.isEmpty()) {                  // Repeat until fringe is empty:
            int v = q.remove();                 // Remove vertex v from the fringe.

            if (v == t) {
                return;
            }

            for (int n : maze.adj(v)) {
                if (!marked[n]) {               // For each unmarked neighbor n of v:
                    edgeTo[n] = v;              // Set edgeTo[n] = v.
                    announce();
                    distTo[n] = distTo[v] + 1;  // Set distTo[n] = distTo[v] + 1.
                    marked[n] = true;           // Mark n.
                    q.add(n);                   // Add n to fringe.
                }
            }
        }


    }


    @Override
    public void solve() {
        bfs();
    }
}

