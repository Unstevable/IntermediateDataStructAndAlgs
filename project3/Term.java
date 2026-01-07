//Author: Steven Carr
//Last Edited: October 2022
// A class representing a Term for Autocompletion
import java.util.Arrays;
import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class Term implements Comparable<Term> {
    private String query; // Instance variable for query
    private long weight; // Instance variable for weight

    // Constructs a term given the associated query string, having weight 0.
    public Term(String query) {
        if (query == null) {
            // If the query is null, throw error message
            throw new NullPointerException("query is null");
        }
        // Initialize query, initialize weight to 0
        this.query = query;
        weight = 0;
    }

    // Constructs a term given the associated query string and weight.
    public Term(String query, long weight) {
        if (query == null) {
            // If the query is null, throw error message
            throw new NullPointerException("query is null");
        } else if (weight < 0) {
            // If the weight is less than 0, throw error message
            throw new IllegalArgumentException("Illegal weight");
        }
        // Initialize query and weight accordingly
        this.query = query;
        this.weight = weight;
    }

    // Returns a string representation of this term.
    public String toString() {
        // return weight and query separated by a tab space
        return weight + "\t" + query;
    }

    // Returns a comparison of this term and other by query.
    public int compareTo(Term other) {
        // Return a comparison between this and other query
        return this.query.compareTo(other.query);
    }

    // Returns a comparator for comparing two terms in reverse order of their weights.
    public static Comparator<Term> byReverseWeightOrder() {
        // Simply return a new ReverseWeightOrder comparator
        return new ReverseWeightOrder();
    }

    // Returns a comparator for comparing two terms by their prefixes of length r.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            // If r is less than 0, throw error message
            throw new IllegalArgumentException("Illegal r");
        }
        // Simply return a new Prefix order comparator, initialized to length r
        return new PrefixOrder(r);
    }

    // Reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        // Returns a comparison of terms v and w by their weights in reverse order.
        public int compare(Term v, Term w) {
            // Since this is reverse, the comparisons are of the opposite order.
            // So, if the weight of v is less than the weight of w, return 1 instead
            // of -1.
            if (v.weight < w.weight) {
                return 1;
                // If the weight of v is greater than the weight of w, return -1
            } else if (v.weight > w.weight) {
                return -1;
                // If the weight of v equals the weight of w, return 0
            } else {
                return 0;
            }
        }
    }

    // Prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        private int r; // Instance variable for r

        // Constructs a new prefix order given the prefix length.
        PrefixOrder(int r) {
            // Initialize r accordingly
            this.r = r;
        }

        // Returns a comparison of terms v and w by their prefixes of length r.
        public int compare(Term v, Term w) {
            // Initialize a String variable "a" to be a substring from 0 to
            // Math.min(r, length of v's query)
            String a = v.query.substring(0, Math.min(r, v.query.length()));
            // Initialize a String variable "b" to be a substring from 0 to
            // Math.min(r, length of w's query)
            String b = w.query.substring(0, Math.min(r, w.query.length()));

            // If a is greater than b, return 1
            if (a.compareTo(b) > 0) {
                return 1;
                // If a is less than b, return -1
            } else if (a.compareTo(b) < 0) {
                return -1;
                // If a == b, return 0
            } else {
                return 0;
            }
        }
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Arrays.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}
