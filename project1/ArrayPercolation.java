// Author: Steven Carr
// Last Edited: September 2022

import stdlib.In;
import stdlib.StdOut;

// An implementation of the Percolation API using a 2D array.
public class ArrayPercolation implements Percolation {
    // Initialize the percolation grid
    private boolean[][] open;
    // Initialize the size of the grid n
    private int n;
    // Initialize the variable to keep track of open sites
    private int openSites;
    // Initialize a variable to keep track of the status of the percolation
    private boolean status;

    // Constructs an n x n percolation system, with all sites blocked.
    public ArrayPercolation(int n) {
        if (n <= 0) {
            // if the given size is less than or is 0, output error message
            throw new IllegalArgumentException("Illegal n");
        }
        // set the size of ArrayPercolation n to the parameter n
        this.n = n;
        // Create a new percolation grid array, all initially blocked
        open = new boolean[n][n];
    }

    // Opens site (i, j) if it is not already open.
    public void open(int i, int j) {
        if (i < 0 || i > (n-1) || j < 0 || j > (n-1)) {
            // if the given indexes are out of bounds, a.k.a
            // they are bigger or smaller than the bounds of the
            // grid, output this error message
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // if the current location in the grid is false, then
        // open it (make it true).  Then increment the total
        // amount of open sites to keep track
        if (!open[i][j]) {
            open[i][j] = true;
            openSites++;
        }
    }

    // Returns true if site (i, j) is open, and false otherwise.
    public boolean isOpen(int i, int j) {
        if (i < 0 || i > (n-1) || j < 0 || j > (n-1)) {
            // if the given indexes are out of bounds, a.k.a
            // they are bigger or smaller than the bounds of the
            // grid, output this error message
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // simply return the current place in the grid.  If it is
        // open, it will return true.  If not, it will return false.
        return open[i][j];
    }

    // Returns true if site (i, j) is full, and false otherwise.
    public boolean isFull(int i, int j) {
        if (i < 0 || i > (n-1) || j < 0 || j > (n-1)) {
            // if the given indexes are out of bounds, a.k.a
            // they are bigger or smaller than the bounds of the
            // grid, output this error message
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // create another grid for full checking
        boolean[][] full = new boolean[n][n];
        // flood every site in the first row of the system
        for (int rows = 0; rows < open.length; rows++) {
            floodFill(full, 0, rows);
        }
        // check for system percolation
        for (int rows = 0; rows < full.length; rows++) {
            if (full[n-1][rows]) {
                status = true;
                break;
            }
        }
        // return the current place in the grid to see if it is full
        return full[i][j];
    }

    // Returns the number of open sites.
    public int numberOfOpenSites() {
        // simply return the total number of open sites
        // which we have been keeping track of
        return openSites;
    }

    // Returns true if this system percolates, and false otherwise.
    public boolean percolates() {
        // check each space in the bottom row to see if it
        // is full.  if it is, then the system percolated.
        for (int i = 0; i < open.length; i++) {
            if (isFull(n-1, i)) {
                return status;
            }
        }
        return status;
    }

    // Recursively flood fills full[][] using depth-first exploration, starting at (i, j).
    private void floodFill(boolean[][] full, int i, int j) {
        // if the given indexes are out of bounds, the space is closed
        // or if the given space is already full, return
        if (i < 0 || i > (n-1) || j < 0 || j > (n-1) ||
                        !isOpen(i, j) || full[i][j]) {
            return;
        }
        // fills the given space, and then recursively calls the function
        // for each surrounding space
        full[i][j] = true;
        floodFill(full, i - 1, j);
        floodFill(full, i + 1, j);
        floodFill(full, i, j - 1);
        floodFill(full, i, j + 1);
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        ArrayPercolation perc = new ArrayPercolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.printf("%d x %d system:\n", n, n);
        StdOut.printf("  Open sites = %d\n", perc.numberOfOpenSites());
        StdOut.printf("  Percolates = %b\n", perc.percolates());
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.printf("  isFull(%d, %d) = %b\n", i, j, perc.isFull(i, j));
        }
    }
}