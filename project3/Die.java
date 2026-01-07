//Author: Steven Carr
//Last Edited: October 2022
// A class representing a Die.
import stdlib.StdOut;
import stdlib.StdRandom;

public class Die implements Comparable<Die> {
    private int value; // the face value

    // Constructs a die.
    public Die() {
        // Initialize value to 0
        value = 0;
    }
    
    // Rolls this die.
    public void roll() {
        // Roll the die, set it to a random number between
        // 1 and 7, 7 not being included (so 1 to 6)
        this.value = StdRandom.uniform(1, 7);
    }

    // Returns the face value of this die.
    public int value() {
        // Simply return the current value
        return value;
    }

    // Returns true if this die is the same as other, and false otherwise.
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        // Set this to the first die, and cast other as a die
        // as second, and then compare the value of the first
        // and second die (this and other).  We cast "other" as
        // a Die because we know it must be of the same class due to
        // previous "if" statements.
        Die first = this, second = (Die) other;
        // Compare the value of the first and second die, and return
        return first.value == second.value;
    }

    // Returns a comparison of this die with other, by their face values.
    public int compareTo(Die that) {
        // Return a comparison of the current value and the other Die value,
        // if the difference is less than 0, other is greater, if it is more
        // than 0, the current value is greater, if it is 0, they are equal
        return value - that.value;
    }

    // Returns a string representation of this die.
    public String toString() {
        // Represent the current side of the Die using asterisks.
        // Each if statement represents a side of the Die
        if (value == 1) {
            return "     \n  *  \n     ";
        } else if (value == 2) {
            return "*    \n     \n    *";
        } else if (value == 3) {
            return "*    \n  *  \n    *";
        } else if (value == 4) {
            return "*   *\n     \n*   *";
        } else if (value == 5) {
            return "*   *\n  *  \n*   *";
        } else if (value == 6) {
            return "* * *\n     \n* * *";
        }
        // If the value is none of these, than the Die hasn't been rolled
        return "Not rolled yet";
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);
        Die a = new Die();
        a.roll();
        while (a.value() != x) {
            a.roll();
        }
        Die b = new Die();        
        b.roll();
        while (b.value() != y) {
            b.roll();
        }
        Die c = new Die();        
        c.roll();
        while (c.value() != z) {
            c.roll();
        }
        StdOut.println("Dice a, b, and c:");
        StdOut.println(a);
        StdOut.println(b);
        StdOut.println(c);
        StdOut.println("a.equals(b)    = " + a.equals(b));
        StdOut.println("b.equals(c)    = " + b.equals(c));
        StdOut.println("a.compareTo(b) = " + a.compareTo(b));
        StdOut.println("b.compareTo(c) = " + b.compareTo(c));
    }
}
