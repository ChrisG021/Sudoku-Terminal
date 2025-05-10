package model;

public class SudokuBoard {
    private Cell[][] board;

    public SudokuBoard() {
        board = new Cell[9][9];

        int[][] preset = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = preset[i][j];
                board[i][j] = new Cell(val, val != 0);
            }
        }
    }

    public boolean setCell(int row, int col, int value) {
        if (isValidMove(row, col, value)) {
            board[row][col].setValue(value);
            return true;
        }
        return false;
    }

    public boolean isValidMove(int row, int col, int value) {
        if (value < 1 || value > 9 || board[row][col].isFixed()) return false;

        for (int j = 0; j < 9; j++) {
            if (board[row][j].getValue() == value) return false;
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col].getValue() == value) return false;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j].getValue() == value) return false;
            }
        }

        return true;
    }

    public void printBoard() {
        System.out.println("    0 1 2   3 4 5   6 7 8");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("  +-------+-------+-------+");
            }
            System.out.print(i + " | ");
            for (int j = 0; j < 9; j++) {
                int value = board[i][j].getValue();
                System.out.print((value == 0 ? "." : value) + " ");
                if ((j + 1) % 3 == 0) System.out.print("| ");
            }
            System.out.println();
        }
        System.out.println("  +-------+-------+-------+");
    }

    public boolean isComplete() {
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[9];
            boolean[] colCheck = new boolean[9];
            for (int j = 0; j < 9; j++) {
                int rowVal = board[i][j].getValue();
                int colVal = board[j][i].getValue();

                if (rowVal == 0 || colVal == 0) return false;

                if (rowCheck[rowVal - 1] || colCheck[colVal - 1]) return false;
                rowCheck[rowVal - 1] = true;
                colCheck[colVal - 1] = true;
            }
        }

        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                boolean[] blockCheck = new boolean[9];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int val = board[blockRow * 3 + i][blockCol * 3 + j].getValue();
                        if (blockCheck[val - 1]) return false;
                        blockCheck[val - 1] = true;
                    }
                }
            }
        }

        return true;
    }
}