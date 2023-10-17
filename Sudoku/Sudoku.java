/* 
 * filename : Sudoku.java
 * author: Vivian Nguyen
 * date: Oct 17
 * Project 4
 */

public class Sudoku {

    private Board board;
    private LandscapeDisplay ld;


    //constructs the sudoku, makes new board with pre-determined number of locked values
    public Sudoku(int lockNum) {
        board = new Board(lockNum);
        ld = new LandscapeDisplay(board);

    }



    //finds the next cell to find a solution for
    public Cell findNextCell() {
        // creates cell
        Cell next = null;
        // total of 9 possible solutions for a cell
        int solutions = 99;

        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                // create a cell at r,c
                Cell temp = board.get(r, c);

                // moves on if the cell is already locked
                // System.out.println(r + c);
                if (temp.isLocked() || temp.getValue() != 0) {
                    // System.out.println("Continuing");
                    continue;

                }
                // System.out.println(" After Continuing");

                // counts how many solutions each cell has
                int solutionNum = 0;
                for (int i = 1; i < 10; i++) {
                    if (board.validValue(r, c, i)) {
                        solutionNum++;
                    }
                }
                // System.out.println(r +" " + c + " "+ solutionNum );

                // we want the cell with fewer solutions
                if (solutionNum < solutions) {
                    next = temp;
                    solutions = solutionNum;
                }

            }

        }
        //sets the cell to a value
        for (int i = 1; i < 10; i++) {
            if (board.validValue(next.getRow(), next.getCol(), i)) {
                next.setValue(i);
                break;
            }
        }

        return next;

    }


    // to check if a cell has a valid value to try
    public int possibleValue(Cell c) {

        int value = 0;

        for (value = c.getValue() + 1; value < 10; value++) {

            if (this.board.validValue(c.getRow(), c.getCol(), value)) {
                break;
            }

        }

        return value;
    }


    //algorithm to solve the sudoku
    public boolean solve(int delay) {
        // Allocate a stack, initially empty
        CellStack stack = new CellStack();

        // while the stack size is less than the number of unspecified cells

        while (stack.size() < 81 - board.numLocked()) {

            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    System.out.println("Interrupted");
                }
                ld.repaint();
            }

            // select the next cell to check (you'll be calling findNextCell, described
            // below)
            Cell next = this.findNextCell();
            // next.setValue(this.possibleValue(next));
            // System.out.println("HERE: " + next);

            // if this cell has a valid value to try
            if (next != null) {

                stack.push(next);

                // updates the board
                this.board.set(next.getRow(), next.getCol(), next.getValue());

                // System.out.println(board);
            } else {
                // if the stack is not empty
                while (!stack.empty()) {
                    // pop a cell off the stack
                    Cell popped = stack.pop();

                    int v = this.possibleValue(popped);

                    if (v < 10) {
                        popped.setValue(v);
                        stack.push(popped);

                        break;
                    }

                    else {
                        popped.setValue(0);
                    }
                }
                if (stack.size() == 0) {
                    return false;

                }
            }
        }
        board.finished = true;
        return true;
    }

    public static void main(String args[]) {



        //testing constructor
        Sudoku sudoku = new Sudoku(0);
        // System.out.println("Initial Sudoku Grid:");
        // System.out.print(sudoku.board + "\n");

        // Sudoku sud = new Sudoku(50);
        // System.out.println("New Sudoku Grid:");
        // System.out.print(sud.board + "\n");

        //testing solve method

        sudoku.solve(10);
        System.out.print(sudoku.board);
        System.out.print(sudoku.board.validSolution() + "\n");

        // for exploration
        // long startTime = System.currentTimeMillis();
        // sudoku.solve(10);
        // long elapsed = System.currentTimeMillis() - startTime;
        // System.out.println(elapsed);

    }

}
