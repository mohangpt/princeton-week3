/* *****************************************************************************
 *  Name: mohan
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.LinkedList;

public class Board {
    private final int[][] state;
    private int rowz;
    private int colz;
    private final int manhatton;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null || tiles.length < 2) {
            throw new IllegalArgumentException();
        }
        int n = tiles.length;
        state = new int[n][n];
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                state[i][j] = tiles[i][j];
                if (state[i][j] == 0) {
                    rowz = i;
                    colz = j;
                }
            }
        }
        manhatton = calmanhattan();
    }

    // unit testing (not graded)
    public static void main(String[] args) {
    // this is for unit testing only
    }

    // board dimension n
    public int dimension() {
        return state.length;
    }

    // number of tiles out of place
    public int hamming() {
        int ref = 1;
        int counter = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i][j] != ref && state[i][j] != 0) {
                    counter++;
                }
                ref++;
            }
        }
        return counter;
    }

    public int manhattan() {
        return manhatton;
    }

    // sum of Manhattan distances between tiles and goal
    private int calmanhattan() {
        int ret = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
               if (state[i][j] != 0) {
                   ret += Math.abs(((state[i][j] - 1) / state.length) - i) + Math.abs(((state[i][j] - 1) % state.length) - j);
               }
            }
        }
        return ret;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }
        if (((Board) y).state.length != this.state.length) {
            return false;
        }
        for (int i = 0; i < this.state.length; i++) {
            for (int j = 0; j < this.state.length; j++) {
                if (this.state[i][j] != ((Board) y).state[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // string representation of this board
    public String toString() {
        StringBuilder ret = new StringBuilder(state.length + "\n");
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                ret.append(state[i][j]).append(" ");
            }
            ret.append("\n");
        }
        return ret.toString();
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        LinkedList<Board> ret = new LinkedList<>();
        // find location of 0
        int row = rowz;
        int col = colz;
        // four derections 0 can travel

        // up (can be travelled when row != 0)
        if (row != 0) {
            ret.add(new Board(swap(row, col, row - 1, col, state)));
        }
        // down  (can be teavelled when row != n)
        if (row != state.length-1) {
            ret.add(new Board(swap(row, col, row + 1, col, state)));
        }
        // right (can be travelled when col != n)
        if (col != state.length-1) {
            ret.add(new Board(swap(row, col, row, col + 1, state)));
        }
        // left (can be travelled when col != 0)
        if (col != 0) {
            ret.add(new Board(swap(row, col, row, col-1, state)));
        }
        return ret;
    }

    private int[][] swap(int row, int col, int row1, int col1, int [][] temp) {
        int[][] ret = new int[temp.length][temp.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                ret[i][j] = temp[i][j];
            }
        }
        int t = ret[row][col];
        ret[row][col] = ret[row1][col1];
        ret[row1][col1] = t;
        return ret;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        // just exchange 1 and 2 and return
        int r = 0;
        int c = 0;
        int r1 = 0;
        int c1 = 1;
        if (this.state[r][c] == 0) {
            r++;
        }
        if (this.state[r1][c1] == 0) {
            r1++;
        }
        return new Board(swap(r, c, r1, c1, state));
    }
}
