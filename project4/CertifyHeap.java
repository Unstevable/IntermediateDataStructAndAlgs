//Author: Steven Carr
//Last Edited: November 2022
// Checks to see if the given input represents a max-heap or not
import stdlib.StdIn;
import stdlib.StdOut;

public class CertifyHeap {
    // Returns true if a[] represents a max-heap, and false otherwise.
    public static boolean isMaxHeap(Comparable[] a) {
        // Set n to the number of elements in a.
        int n = a.length;

        // For each node 1 <= i <= n / 2, if a[i] is less than either of its children, return
        // false, meaning a[] does not represent a max-heap. If no such i exists, return true.
        for (int i = 1; i <= n / 2; i++) {
            // need to make sure that i * 2 is less than the length of the array (left child)
            if (i * 2 < n && less(a[i], a[i * 2])) {
                return false;
                // also need to check the same for the right child
            } else if (i * 2 + 1 < n && less(a[i], a[i * 2 + 1])) {
                return false;
            }
        }
        return true;
    }

    // Returns true of v is less than w, and false otherwise.
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Unit tests the library. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println(isMaxHeap(a));
    }
}
