// Time Complexity : exponential (4^n where n is length of word) => As were are performing 4 recusrive call for 4 direction
// Space Complexity : O(n) => As in the recusrive stack we are storing elements equal to the length of word (n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes. In the 1st for loop calculated c by word.charAt(i) instead of word.charAt(i)

/*
Approach: Using recusrion + backtracking. First discover the first valid character by iterating over the board matrix then start the recusrive call by travelling in 4 directions. To mark the element as visited mark with the special character and as a backtracking step undo that action
 */
class Solution {
    int[][] dirs;
    int m, n;

    public boolean exist(char[][] board, String word) {

        if (board == null || board.length == 0 || word.length() == 0) {
            return true;
        }

        m = board.length;
        n = board[0].length;

        dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = word.charAt(0);

                if (c == board[i][j]) {
                    if (backtrack(board, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean backtrack(char[][] board, String word, int index, int row, int column) {

        // base
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row == m || column < 0 || column == n || board[row][column] == '#') {
            return false;
        }

        // logic
        if (board[row][column] == word.charAt(index)) {
            // action
            char temp = board[row][column];
            board[row][column] = '#';
            for (int[] dir : dirs) {
                int nr = row + dir[0];
                int nc = column + dir[1];

                // recurse
                if (backtrack(board, word, index + 1, nr, nc)) {
                    return true;
                }
            }
            // backtrack
            board[row][column] = temp;
        }

        return false;
    }
}