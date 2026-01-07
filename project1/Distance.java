// Author: Steven Carr
// Last Edited: September 2022
//Returns the Euclidean distance between the given vectors
import stdlib.StdArrayIO;
import stdlib.StdOut;

public class Distance {
    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        double[] x = StdArrayIO.readDouble1D();
        double[] y = StdArrayIO.readDouble1D();
        StdOut.println(distance(x, y));
    }

    // Returns the Euclidean distance between the position vectors x and y.
    private static double distance(double[] x, double[] y) {
        // Sum up the squares of (x[i] - y[i]), where 0 <= i < x.length, and return the square
        // root of the sum.
        // Initialize an integer i to keep track of array place
        int i = 0;
        // Initialize a double placeholder to store difference in temporarily
        double placeholder;
        // Initialize a double sum to store the sum of all the squares
        double sum = 0;
        // While i is less than the length of array x and array y (they should be the same)
        while (i < x.length && i < y.length) {
            // the placeholder is equal to the differences of each array slot
            placeholder = x[i] - y[i];
            // the sum is equal to the resulted difference, squared
            sum += placeholder * placeholder;
            // reset the placeholder for next value
            placeholder = 0;
            // increment the array places
            i++;
        }
        // return the square root of the sum of squares
        return Math.sqrt(sum);
    }
}
