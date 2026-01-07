//Author: Steven Carr
//Last Edited: November 2022
//Calculates the Ramanujan number using for-loops
import stdlib.StdOut;

public class Ramanujan1 {
    // Entry point.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        // Initialize variables a, b, c and d
        int a, b, c, d;
        // For a being greater than 0, while a is less than the cubed root of n
        for (a = 1; (a * a * a) <= n; a++) {
            // Create variable for a cubed for easier access
            int acubed = a * a * a;
            // For b being equal to a, and being less than the cubed root of
            // n - a cubed
            for (b = a; (b * b * b) <= (n - (a * a * a)); b++) {
                // Same deal as a
                int bcubed = b * b * b;
                // For c being greater than a, and being less than cubed root of n
                for (c = a + 1; (c * c * c) <= n; c++) {
                    // Same as a and b
                    int ccubed = c * c * c;
                    // For d being equal to c, while being less than the cubed root
                    // of n - c cubed
                    for (d = c; (d * d * d) <= (n - (c * c * c)); d++) {
                        // Same as a, b and c
                        int dcubed = d * d * d;
                        // If the sum of a cubed and b cubed is equal to the sum of
                        // c cubed and d cubed, print the output
                        if (acubed + bcubed == ccubed + dcubed) {
                            StdOut.print((acubed + bcubed) + " = ");
                            StdOut.print(a + "^3 + " + b + "^3 = ");
                            StdOut.print(c + "^3 + " + d + "^3\n");
                        }
                    }
                }
            }
        }
    }
}
