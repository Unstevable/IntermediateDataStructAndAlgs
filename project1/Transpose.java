// Author: Steven Carr
// Last Edited: September 2022
//Returns a new matrix that is a transposition of the given matrix
import stdlib.StdArrayIO;

public class Transpose {
    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        double[][] x = StdArrayIO.readDouble2D();
        StdArrayIO.print(transpose(x));
    }

    // Returns a new matrix that is the transpose of x.
    private static double[][] transpose(double[][] x) {
        // Create a new 2D matrix t (for transpose) with dimensions n x m, where m x n are the
        // dimensions of x.
        // set m equal to the amount of rows of the 2D array
        int m = x.length;
        // set n equal to the amount of columns
        int n = x[0].length;
        // create a new array (now of doubles) with the same dimensions as original, but flipped
        double[][] t = new double[n][m];

        // For each 0 <= i < m and 0 <= j < n, set t[j][i] to x[i][j].
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t[j][i] = x[i][j];
            }
        }

        // Return t.
        return t;
    }
}
