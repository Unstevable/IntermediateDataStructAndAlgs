//Author: Steven Carr
//Last Edited: October 2022
// Calculates the min and max value in a given linked list.
import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.StdStats;

public class MinMax {
    // Returns the minimum value in the given linked list.
    public static int min(Node first) {
        // Set min to the largest integer.
        int min = Integer.MAX_VALUE;
        // Node to keep track of place
        Node current = first;

        // Compare each element in linked list with min and if it is smaller, update min.
        // While there are still items in the linked list
        while (current != null) {
            // if the current item is less than the current minimum
            if (current.item < min) {
                // then the minimum is the current
                min = current.item;
            }
            // if not, check the next node
            current = current.next;
        }

        // Return min.
        return min;
    }

    // Returns the maximum value in the given linked list.
    public static int max(Node first) {
        // Set max to the smallest integer.
        int max = Integer.MIN_VALUE;
        // Node to keep track of place
        Node current = first;

        // Compare each element in linked list with max and if it is larger, update max.
        // While there is still items in the Linked List
        while (current != null) {
            // if the current item is greater than the current max
            if (current.item > max) {
                // the maximum is now the current
                max = current.item;
            }
            // if not, check next node
            current = current.next;
        }

        // Return max.
        return max;
    }

    // A data type to represent a linked list. Each node in the list stores an integer item and a
    // reference to the next node in the list.
    protected static class Node {
        protected int item;  // the item
        protected Node next; // the next node
    }

    // Unit tests the library. [DO NOT EDIT]
    public static void main(String[] args) {
        int[] items = new int[1000];
        for (int i = 0; i < 1000; i++) {
            items[i] = StdRandom.uniform(-10000, 10000);
        }
        Node first = null;
        for (int item : items) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }
        StdOut.println("min(first) == StdStats.min(items)? " + (min(first) == StdStats.min(items)));
        StdOut.println("max(first) == StdStats.max(items)? " + (max(first) == StdStats.max(items)));
    }
}
