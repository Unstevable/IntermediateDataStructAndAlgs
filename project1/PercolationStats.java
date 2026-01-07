// Author: Steven Carr
// Last Edited: September 2022
// Outputs the Percolation statistics
import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.StdStats;

public class PercolationStats {
    // Initialize the number of experiments m
    private int m;
    // Initialize the percolation thresholds for m experiments
    private double[] x;

    // Performs m independent experiments on an n x n percolation system.
    public PercolationStats(int n, int m) {
        if (n <= 0 || m <= 0) {
            // if the size of the system is less than or is 0,
            // or if the number of experiments is less than or 0,
            // output error message
            throw new IllegalArgumentException("Illegal n or m");
        }
        // set the PercolationStats m equal to m
        this.m = m;
        // Create an array to store the percolation thresholds
        x = new double[m];
        // While i is less than the total experiments
        for (int i = 0; i < m; i++) {
            int count = 0;
            UFPercolation perc = new UFPercolation(n);
            // Until the system percolates...
            while (!perc.percolates()) {
                int a;
                int b;
                do {
                    // Open a random site if it is not already open
                    a = StdRandom.uniform(n);
                    b = StdRandom.uniform(n);
                } while (perc.isOpen(a, b));
                perc.open(a, b);
                // Increment count total
                count++;
            }
            // Calculate the percolation threshold and store in array
            x[i] = (double) count / n / n;
        }
    }

    // Returns sample mean of percolation threshold.
    public double mean() {
        // Use StdStats to return the mean of the percolation
        // threshold array x
        return StdStats.mean(x);
    }

    // Returns sample standard deviation of percolation threshold.
    public double stddev() {
        // Use StdStats to return the standard deviation of the
        // percolation threshold array x
        return StdStats.stddev(x);
    }

    // Returns low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        // Calculate the low confidence, which is the mean subtracted by
        // 1.96 multiplied by the deviation divided by the square root of
        // total experiments
        return StdStats.mean(x) - 1.96 * StdStats.stddev(x) / Math.sqrt(m);
    }

    // Returns high endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        // Calculate the high confidence, which is the same as the low confidence
        // except we add the latter half to the mean
        return StdStats.mean(x) + 1.96 * StdStats.stddev(x) / Math.sqrt(m);
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, m);
        StdOut.printf("Percolation threshold for a %d x %d system:\n", n, n);
        StdOut.printf("  Mean                = %.3f\n", stats.mean());
        StdOut.printf("  Standard deviation  = %.3f\n", stats.stddev());
        StdOut.printf("  Confidence interval = [%.3f, %.3f]\n", stats.confidenceLow(),
                stats.confidenceHigh());
    }
}