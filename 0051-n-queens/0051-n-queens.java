class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        List<List<String>> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        nQueens(result, board, n, 0);

        return result;
    }

    private void nQueens(List<List<String>> result, char[][] board, int n, int row) {
        if (row == n) {
            List<String> sol = new ArrayList<>();
            for (char[] charArr : board) {
                sol.add(new String(charArr));
            }
            result.add(sol);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isSafe(row, i, board, n)) {
                board[row][i] = 'Q';
                nQueens(result, board, n, row + 1);
                board[row][i] = '.';
            }
        }
    }

    private boolean isSafe(int row, int col, char[][] board, int n) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        int i = col;
        int j = row;

        while (i >= 0 && j >= 0) {
            if (board[j][i] == 'Q') return false;
            i--;
            j--;
        }

        i = col;
        j = row;

        while (i < n && j >= 0) {
            if (board[j][i] == 'Q') return false;
            i++;
            j--;
        }

        return true;
    }
}