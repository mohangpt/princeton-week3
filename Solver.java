/* *****************************************************************************
 *  Name: mohan gupta
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private Node sol;
    private boolean solvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<Node> solutionPriority = new MinPQ<>();
        MinPQ<Node> twinQueue = new MinPQ<>();

        Node n = new Node(initial, null);
        solutionPriority.insert(n);

        Node n1 = new Node(initial.twin(), null);
        twinQueue.insert(n1);

        while (!solutionPriority.isEmpty()) {
            Node temp = solutionPriority.delMin();
            Node twintemp = twinQueue.delMin();

            Board b = temp.board;
            Board b1 = twintemp.board;

            if (b.isGoal()) {
                sol = temp;
                solvable = true;
                return;
            }
            if (b1.isGoal()) {
                sol = null;
                solvable = false;
                return;
            }

            Iterable<Board> list = temp.board.neighbors();
            for (Board toAdd : list) {
                Node x = new Node(toAdd, temp);
                solutionPriority.insert(x);
            }

            Iterable<Board> twinlist = twintemp.board.neighbors();
            for (Board toAdd : twinlist) {
                Node x = new Node(toAdd, twintemp);
                twinQueue.insert(x);
            }
        }
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        return sol.moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        Stack<Board> list = new Stack<>();
        while (sol.previous != null) {
            list.push(sol.board);
            sol = sol.previous;
        }
        list.push(sol.board);
        return list;
    }

    private class Node implements Comparable<Node> {
        private final int priority;
        private final Board board;
        private final Node previous;
        private int moves;


        public Node(Board board, Node previous) {
            this.previous = previous;
            this.board = board;
            if (previous == null) {
                moves = 0;
            } else {
                this.moves = previous.moves + 1;
            }

            priority = board.manhattan() + moves;
        }

        public int compareTo(Node node) {
            return Integer.compare(this.priority, node.priority);
        }
    }
}