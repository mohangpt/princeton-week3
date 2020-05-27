import static org.junit.jupiter.api.Assertions.assertEquals;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
class BoardTest {

    @org.junit.jupiter.api.Test
    void dimension() {
    }

    @org.junit.jupiter.api.Test
    void hamming() {
    }

    @org.junit.jupiter.api.Test
    void manhattanWith0Distance() {
        int[][] solvedArray = {{1,2,3}, {4,5,6}, {7,8,0}};
        Board b = new Board(solvedArray);
        assertEquals(0, b.manhattan());
    }

    @org.junit.jupiter.api.Test
    void manhattanWithNonZeroDistance() {
        int[][] solvedArray = {{1,2,0}, {4,5,3}, {7,8,6}};
        Board b = new Board(solvedArray);
        assertEquals(2, b.manhattan());
    }

    @org.junit.jupiter.api.Test
    void hamming0() {
        int[][] solvedArray = {{1,2,3}, {4,5,6}, {7,8,0}};
        Board b = new Board(solvedArray);
        assertEquals(0, b.hamming());
    }

    @org.junit.jupiter.api.Test
    void hammingNon0() {
        int[][] solvedArray = {{1,3,2}, {4,5,0}, {7,8,6}};
        Board b = new Board(solvedArray);
        assertEquals(3, b.hamming());
    }

    @org.junit.jupiter.api.Test
    void isGoaltrue() {
        int[][] target = {{1,2,3}, {4,5,6}, {7,8,0}};
        Board b = new Board(target);
        assertEquals(true, b.isGoal());
    }

    @org.junit.jupiter.api.Test
    void isGoalfalse() {
        int[][] target = {{1,2,3}, {5,4,6}, {7,8,0}};
        Board b = new Board(target);
        assertEquals(false, b.isGoal());
    }

    @org.junit.jupiter.api.Test
    void testEqual() {

    }

    @org.junit.jupiter.api.Test
    void neighborsWith3() {
        int[][] solvedArray = {{1,2,3}, {4,5,0}, {7,8,6}};
        Board b = new Board(solvedArray);
        int counter = 0;
        // String
        for(Board t : b.neighbors()) {
            counter++;
            System.out.println(t);
        }
        assertEquals(3,  counter);
    }

    @org.junit.jupiter.api.Test
    void neighborsWith4() {
        int[][] solvedArray = {{1,2,3}, {4,0,6}, {7,5,8}};
        Board b = new Board(solvedArray);
        int counter = 0;
        // String
        for(Board t : b.neighbors()) { counter++;
            System.out.println(t);
        }
        assertEquals(4,  counter);
    }

    @org.junit.jupiter.api.Test
    void neighborsWith2() {
        int[][] solvedArray = {{0,2,3}, {4,1,6}, {7,5,8}};
        Board b = new Board(solvedArray);
        int counter = 0;
        // String
        for(Board t : b.neighbors()) { counter++;
            System.out.println(t);
        }
        assertEquals(2,  counter);
    }

    @org.junit.jupiter.api.Test
    void twin() {
    }
}