// Time Complexity : n! => When trying to place the 1st queen we have n options for 2nd row we have n -2 options to place the queen, for 3rd queen we have n-4 options
// Space Complexity : O(n^2) + O(n) => O(n^2), O(n^2) as we are using grid[][] and O(n) for recursive stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/*
Approach : Created a boolean[][] to mark as the element as visited. Start recurse call with the 1st row, then iterate over n columns and check if the queen can be placed in that cell by checking its upper left diagoanl, upper right diagonal, up and if it is safe use backtracking to mark that cell as true. In the base condition finally return result by replacing Q if that cell is marked as true and . if marked as false.
 */
class Solution {
    boolean[][] grid;
    List<List<String>> result;
    public List<List<String>> solveNQueens(int n) {

        if (n == 0) {
            return new ArrayList<>();
        }

        grid = new boolean[n][n];
        result = new ArrayList<>();

        recurse(0);

        return result;
    }

    private void recurse(int row) {

        // base
        if(row == grid.length){
            List<String> answer = new ArrayList<>();
            for(int i =0; i<grid.length;i++){
                StringBuilder sb = new StringBuilder();
                for(int j =0; j<grid.length; j++){
                    if(grid[i][j]){
                        sb.append("Q");
                    }
                    else{
                        sb.append(".");
                    }
                }

                answer.add(sb.toString());
            }

            result.add(answer);
            return;
        }

        // logic

        for (int j = 0; j < grid.length; j++) {

            if (isSafe(row, j)) {
                // action
                grid[row][j] = true;

                // recurse

                recurse(row + 1);

                // backtrack
                grid[row][j] = false;
            }

        }
    }

    private boolean isSafe(int row, int column) {

        // upper elements
        for (int i = row; i >= 0; i--) {
            if (grid[i][column]) {
                return false;
            }
        }

        // upper left diagonal
        int i = row-1;
        int j = column-1;

        while (i >= 0 && j >= 0) {

            if (grid[i][j]) {
                return false;
            }

            i--;
            j--;
        }

        // upper right diagonal
        i = row-1;
        j = column+1;

        while (i >= 0 && j < grid.length) {

            if (grid[i][j]) {
                return false;
            }

            i--;
            j++;
        }

        return true;
    }
}