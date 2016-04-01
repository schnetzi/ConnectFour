package paul.connectfour;



public class Game {

    public static final int RED = 1;
    public static final int YELLOW = -1;
    public static final int EMPTY = 0;

    private int[][] gameState = new int[7][6];
    private int gameCounter = 0;

    public void increaseGameCounter() {
        this.gameCounter++;
    }

    public Game() {

    }


    public int getCurrentPlayer() {
        return this.gameCounter % 2 == 0 ? RED : YELLOW;
    }


    public boolean move(int column) {
        int length = gameState[column].length;
        if (column < 0 || column > 6) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (gameState[column][i] == EMPTY) {
                gameState[column][i] = getCurrentPlayer();
                return true;
            }
        }

        return false;
    }


    public int computerMove() {
        while (true) {
            int randomCol = 0 + (int) (Math.random() * (gameState.length));
            if (this.move(randomCol)) {
                return randomCol;
            }
        }
    }


    public int checkWinner(int columnIndex) {
        int player = this.getCurrentPlayer();

        //vertical
        int inARow = 0;
        int[] column = this.gameState[columnIndex];
        for (int i = 0; i < column.length; i++) {
            if (column[i] == player) {
                inARow++;
            } else {
                inARow = 0;
            }
            if (inARow == 4) {
                return player;
            }
        }

        //horizontal
        int minColumnIndex = columnIndex - 3 < 0 ? 0 : columnIndex - 3;
        int maxColumnIndex = columnIndex + 3 > 6 ? 6 : columnIndex + 3;

        int sumNextToEachOther = 0;
        for (int n = 0; n < this.gameState[columnIndex].length; n++) {
            for (int m = minColumnIndex; m <= maxColumnIndex; m++) {
                int field = this.gameState[m][n];
                if (field == player) {
                    sumNextToEachOther++;
                } else {
                    sumNextToEachOther = 0;
                }
                if (sumNextToEachOther == 4) {
                    return player;
                }
            }
        }

        //diagonal
        int rowPosition = 1;
        while (rowPosition < this.gameState[columnIndex].length) {
            if (this.gameState[columnIndex][rowPosition] == 0) {
                break;
            }
            rowPosition++;
        }
        rowPosition--;

        //check to the top right
        int minCol = columnIndex;
        int minRow = rowPosition;
        while (minCol - 1 >= 0 && minRow - 1 >= 0) {
            minCol--;
            minRow--;
        }

        int maxCol = columnIndex;
        int maxRow = rowPosition;
        while (maxCol + 1 <= 6 && maxRow + 1 <= 5) {
            maxCol++;
            maxRow++;

        }

        int count = 0;
        int row = minRow;
        for (int col = minCol; col <= maxCol; col++, row++) {
            if (this.gameState[col][row] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return player;
            }
        }

        //check to the top left
        minCol = columnIndex;
        maxRow = rowPosition;
        while (minCol - 1 >= 0 && maxRow + 1 <= 5) {
            minCol--;
            maxRow++;
        }

        maxCol = columnIndex;
        minRow = rowPosition;
        while (maxCol + 1 <= 6 && minRow - 1 >= 0) {
            maxCol++;
            minRow--;
        }

        count = 0;
        row = maxRow;
        for (int col = minCol; col <= maxCol; col++, row--) {
            if (this.gameState[col][row] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return player;
            }
        }

        return 0;
    }
}
