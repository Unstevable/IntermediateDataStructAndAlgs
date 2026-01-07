//Author: Steven Carr
//Last Edited: October 2022
// An Autocompletion program given an array of Terms
import java.util.Arrays;
import java.util.Comparator;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class Autocomplete {
    private Term[] terms; // Instance variable for terms

    // Constructs an autocomplete data structure from an array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            // If terms is null, throw error message
            throw new NullPointerException("terms is null");
        }
        // Initialize this.terms to a copy of terms
        this.terms = terms.clone();
        // Sort the array of terms in lexicographic order
        Arrays.sort(this.terms);
    }

    // Returns all terms that start with prefix, in descending order of their weights.
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            // If the prefix is null, throw error message
            throw new NullPointerException("prefix is null");
        }
        // Construct a new Term with the size of prefix
        Term term = new Term(prefix);
        // Construct a Comparator to search by the prefix
        Comparator<Term> prefixcompare = Term.byPrefixOrder(prefix.length());
        // Initialize variable "i" to search for the first index of the first term
        // that starts with the prefix
        int i = BinarySearchDeluxe.firstIndexOf(this.terms, term, prefixcompare);
        // Initialize the variable "n" to be the total number of terms starting with
        // the given prefix
        int n = numberOfMatches(prefix);

        // Construct a Term array called "matches" with n elements
        Term[] matches = new Term[n];

        // Copy the terms from terms into matches
        for (int a = 0; a < n; a++) {
            matches[a] = this.terms[i++];
        }
        // Sort the array of matches in Reverse Weight Order
        Arrays.sort(matches, Term.byReverseWeightOrder());
        // Return matches
        return matches;
    }

    // Returns the number of terms that start with prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
            // If the prefix is null, throw error message
            throw new NullPointerException("prefix is null");
        }
        // Construct a Term by the prefix
        Term term = new Term(prefix);
        // Construct a new Comparator by the prefix
        Comparator<Term> prefixcompare = Term.byPrefixOrder(prefix.length());
        // Initialize variable "i" to be the first index containing prefix
        int i = BinarySearchDeluxe.firstIndexOf(this.terms, term, prefixcompare);
        // Initialize variable "j" to be the last index containing prefix
        int j = BinarySearchDeluxe.lastIndexOf(this.terms, term, prefixcompare);

        // Compute the number of terms starting with the prefix and return
        // Using "?" here as a conditional replacement
        return i == -1 && j == -1 ? 0 : j - i + 1;
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
        Autocomplete autocomplete = new Autocomplete(terms);
        StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            String msg = " matches for \"" + prefix + "\", in descending order by weight:";
            if (results.length == 0) {
                msg = "No matches";
            } else if (results.length > k) {
                msg = "First " + k + msg;
            } else {
                msg = "All" + msg;
            }
            StdOut.printf("%s\n", msg);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println("  " + results[i]);
            }
            StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        }
    }
}
