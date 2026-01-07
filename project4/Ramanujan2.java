//Author: Steven Carr
//Last Edited: November 2022
//Calculates the Ramanujan number using a MinPQ
import dsa.MinPQ;
import stdlib.StdOut;

public class Ramanujan2 {
    // Entry point.
    public static void main(String[] args) {
        // Read an integer from command line argument
        int n = Integer.parseInt(args[0]);
        // Initialize a min priority queue of object type Pair
        MinPQ<Pair> pq = new MinPQ<Pair>();
        // Fill the min pq with i, i + 1 until i is less than the cubed root
        // of n, which is displayed as i * i * i <= n instead of i <= cubed root n
        for (int i = 1; i * i * i < n; i++) {
            // add i, i + 1
            pq.insert(new Pair(i, i + 1));
        }
        // Initialize the previous pair in the pq
        Pair previous = new Pair(0, 0);
        // For as long as the pq is not empty
        while (!pq.isEmpty()) {
            // Make the current pair in the pq the smallest pair
            Pair current = pq.delMin();
            // if the previous pair isn't null, the previous and current sum of cubes are equal, and
            // the previous sum of cubes (which also means the current sum of cubes) is less than or
            // equal to n
            if (previous != null && previous.sumOfCubes == current.sumOfCubes &&
                    previous.sumOfCubes <= n) {
                // Print the sum of cubes in the propper format
                String ls = "%d = %d^3 + %d^3 = %d^3 + %d^3\n";
                StdOut.printf(ls, previous.sumOfCubes, previous.i,
                        previous.j, current.i, current.j);
            }
            // If the current j is less than the cubed root of n (typo in the original instructions,
            // supposed to be j instead of i)
            if (current.j * current.j * current.j < n) {
                // Insert the new pair of i and j + 1 into the pq
                pq.insert(new Pair(current.i, current.j + 1));
                // Make the previous pair equal the current pair, and the current pair will reset
                // when it now loops back around
                previous = current;
            }
        }
    }

    // A data type that encapsulates a pair of numbers (i, j) and the sum of their cubes.
    private static class Pair implements Comparable<Pair> {
        private int i;          // first number in the pair
        private int j;          // second number in the pair
        private int sumOfCubes; // i^3 + j^3

        // Constructs a pair (i, j).
        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
            sumOfCubes = i * i * i + j * j * j;
        }

        // Returns a comparison of pairs this and other based on their sum-of-cubes values.
        public int compareTo(Pair other) {
            return sumOfCubes - other.sumOfCubes;
        }
    }
}
