// Author: Steven Carr
// Last Edited: September 2022
import dsa.WeightedQuickUnionUF;
import stdlib.In;
import stdlib.StdOut;

// An implementation of the Percolation API using the UF data structure.
public class UFPercolation implements Percolation {
    // Initialize percolation system for UF representation
    WeightedQuickUnionUF uf;
    // Initialize percolation system for the back wash problem, only
    // connected to the source
    WeightedQuickUnionUF backwashfix;
    // Initialize the size of the system
    private int n;
    // Initialize the system itself with an array
    private boolean[][] open;
    // Initialize the total number of open sites
    private int openSites;
    // Initialize a variable to represent the source, a.k.a the top
    private int source;
    // Initialize a variable to represent the sink, a.k.a the bottom
    private int sink;

    // Constructs an n x n percolation system, with all sites blocked.
    public UFPercolation(int n) {
        // If the size input is less than or is 0, output error message
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal n");
        }
        // Set this object n equal to n
        this.n = n;
        // Set the total open sites to 0
        openSites = 0;
        // Set the array system to size n by n
        open = new boolean[n][n];
        // Set the source to 0, since it is the top
        source = 0;
        // Set the sink to n^2 + 1, because it is the bottom
        sink = (n * n) + 1;
        // Create a weighted union object in UF, with size n^2 + 2 to
        // account for the source and the sink
        uf = new WeightedQuickUnionUF((n * n) + 2);
        // Create a weighted union object for the back wash problem,
        // only connected to the source and not the sink
        backwashfix = new WeightedQuickUnionUF((n * n) + 1);
        // For loop to convert the array system into a naturally numbered system
        for (int i = source; i < n; i++) {
            // Connects the uf system to the source
            uf.union(encode(0, i), source);
            // Connects the back wash fix to the source
            backwashfix.union(encode(0, i), source);
            // Connects the uf to the sink
            uf.union(encode((n-1), i), sink);
            for (int j = 0; j < n; j++) {
                // Setting sites to blocked
                open[i][j] = false;
            }
        }
    }

    // Opens site (i, j) if it is not already open.
    public void open(int i, int j) {
        if (i < 0 || i > (n-1) || j < 0 || j > (n-1)) {
            // if the given index is out of the bounds of the system,
            // output error message
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // if this current site is false (blocked), make it true (open it)
        if (!open[i][j]) {
            open[i][j] = true;
            // Increment the total number of open sites by 1
            openSites++;
            // If statement to check the north to make sure its not out of bounds
            if ((i - 1) >= 0 && open[i - 1][j]) {
                uf.union(encode(i, j), encode(i - 1, j));
                backwashfix.union(encode(i, j), encode(i - 1, j));
            }
            // If statement to check the south to make sure its not out of bounds
            if ((i + 1) < n && open[i + 1][j]) {
                uf.union(encode(i, j), encode(i + 1, j));
                backwashfix.union(encode(i, j), encode(i + 1, j));
            }
            // If statement to check the east to make sure its not out of bounds
            if ((j + 1) < n && open[i][j + 1]) {
                uf.union(encode(i, j), encode(i, j + 1));
                backwashfix.union(encode(i, j), encode(i, j + 1));
            }
            // If statement to check the west to make sure its not out of bounds
            if ((j - 1) >= 0 && open[i][j - 1]) {
                uf.union(encode(i, j), encode(i, j - 1));
                backwashfix.union(encode(i, j), encode(i, j - 1));
            }
        }
    }

    // Returns true if site (i, j) is open, and false otherwise.
    public boolean isOpen(int i, int j) {
        if (i < 0 || i > (n - 1) || j < 0 || j > (n - 1)) {
            // if the given index is out of the bounds of the system,
            // output error message
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // Simply returns the current site, if it is open it will return
        // true, and false if not
        return open[i][j];
    }

    // Returns true if site (i, j) is full, and false otherwise.
    public boolean isFull(int i, int j) {
        if (i < 0 || i > (n - 1) || j < 0 || j > (n - 1)) {
            // if the given index is out of the bounds of the system,
            // output error message
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // Same logic as used in isOpen
        return isOpen(i, j) && uf.connected(encode(i, j), source) &&
                backwashfix.connected(encode(i, j), source);
    }

    // Returns the number of open sites.
    public int numberOfOpenSites() {
        // Simply return the total amount of open sites
        return openSites;
    }

    // Returns true if this system percolates, and false otherwise.
    public boolean percolates() {
        // Size check
        if (n <= 1) {
            return false;
        }
        // Returns the check of the connection between the
        // top and bottom, a.k.a source and sink
        return uf.connected(sink, source);
    }

    // Returns an integer ID (1...n) for site (i, j).
    private int encode(int i, int j) {
        // Translates the array
        return n * i + j + 1;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        UFPercolation perc = new UFPercolation(n);
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