//Author: Steven Carr
//Last Edited: October 2022
import java.util.Arrays;
import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class BinarySearchDeluxe {
    // Returns the index of the first key in a that equals the search key, or -1, according to
    // the order induced by the comparator c.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> c) {
        if (a == null || key == null || c == null) {
            // If arguments are null, throw error message
            throw new NullPointerException("a, key, or c is null");
        }
        // Initialize index to -1
        int index = -1;
        // Initialize the low end of array as the beginning
        int lo = 0;
        // Initialize the high end of the array to the end
        int hi = a.length - 1;

        // While low is still less than or equal to high
        while (lo <= hi) {
            // Initialize the middle point of the array
            // This mid operation is used instead of (hi + lo) / 2 to avoid overflow
            int mid = lo + (hi - lo) / 2;
            // Compare the current key and the middle of the array
            int comparekey = c.compare(key, a[mid]);

            // If the key is smaller than the middle point of array
            if (comparekey < 0) {
                // The new high will be before the middle
                hi = mid - 1;

                // If the key is greater than the middle point of array
            } else if (comparekey > 0) {
                // The new low point is after the middle
                lo = mid + 1;

                // If the key is the middle point
            } else {
                // Make it the index
                index = mid;
                // Change high to the last index in the case of other matches
                hi = mid - 1;
            }
        }
        // Return the index
        return index;
    }

    // Returns the index of the first key in a that equals the search key, or -1, according to
    // the order induced by the comparator c.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> c) {
        if (a == null || key == null || c == null) {
            // If arguments are null, throw error message
            throw new NullPointerException("a, key, or c is null");
        }
        // Initialize index to -1
        int index = -1;
        // Initialize the low end of array as the beginning
        int lo = 0;
        // Initialize the high end of the array to the end
        int hi = a.length - 1;

        // While low is still less than or equal to high
        while (lo <= hi) {
            // Initialize the middle point of the array
            // This mid operation is used instead of (hi + lo) / 2 to avoid overflow
            int mid = lo + (hi - lo) / 2;
            // Compare the current key and the middle of the array
            int comparekey = c.compare(key, a[mid]);

            // If the key is smaller than the middle point of array
            if (comparekey < 0) {
                // The new high will be before the middle
                hi = mid - 1;

                // If the key is greater than the middle point of array
            } else if (comparekey > 0) {
                // The new low point is after the middle
                lo = mid + 1;

                // If the key is the middle point
            } else {
                // Make it the index
                index = mid;
                // Change low to the first index, in case there are other matches
                lo = mid + 1;
            }
        }
        // Return the index
        return index;
    }

    // Unit tests the library. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        String prefix = args[1];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Arrays.sort(terms);
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.println("firstIndexOf(" + prefix + ") = " + i);
        StdOut.println("lastIndexOf(" + prefix + ")  = " + j);
        StdOut.println("frequency(" + prefix + ")    = " + count);
    }
}
