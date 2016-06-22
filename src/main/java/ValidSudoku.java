
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        // input checking: check if board is 9X9
        if (board == null || board.length != 9 || board[0] == null || board[0].length != 9) {
            return false;
        }

        // 1. check row by row
        for (int row = 0; row < 9; row++) {
            boolean[] flag = new boolean[9];
            for(int col = 0; col < 9; col++) {
                if (!valid(board[row][col], flag)) {
                    return false;
                }
            }
        }

        // 2. check col by col
        for (int col = 0; col < 9; col++) {
            boolean[] flag = new boolean[9];
            for(int row = 0; row < 9; row++) {
                if (!valid(board[row][col], flag)) {
                    return false;
                }
            }
        }

        // 3. check each square
        for (int block = 0; block < 9; block++) {
            boolean[] flag = new boolean[9];
            int rowStart = (block / 3) * 3;
            int colStart = (block % 3) * 3;
            for (int row = rowStart; row < rowStart + 3; row++) {
                for (int col = colStart; col < colStart + 3; col++) {
                    if (!valid(board[row][col], flag)) {
                        return false;
                    }
                }
            }
        }

        // Another way of checking the square
        /*
        for(int row = 0; row < 9; row += 3){
            for(int col = 0; col < 9; col += 3){
                boolean[] flag = new boolean[9];
                for(int i = 0; i < 9; i++){
                    if(!valid(board[row + i/3][col + i%3], flag))
                        return false;
                }
            }
        }
        */

        return true;
    }

    private boolean valid(char ch, boolean[] flag) {
        // case 1: '.'
        if (ch == '.') {
            return true;

        // case 2: '1' - '9'
        } else if ('1' <= ch && ch <= '9') {
            if (flag[ch - '1']) {
                return false;
            } else {
                flag[ch - '1'] = true;
                return true;
            }

        // case 3: other character
        } else {
            return false;
        }
    }
}