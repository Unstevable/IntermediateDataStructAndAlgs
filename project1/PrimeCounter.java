// Author: Steven Carr
// Last Edited: September 2022
// Program that returns the number of primes that are less than or equal to the given number
import stdlib.StdOut;

public class PrimeCounter {
    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdOut.println(primes(n));
    }

    // Returns true if x is prime; and false otherwise.
    private static boolean isPrime(int x) {
        // For each 2 <= i <= x / i, if x is divisible by i, then x is not a prime. If no such i
        // exists, then x is a prime.
        int i = 2;
        // while i is less than the number x / i (which is the same as saying while i is less
        // than the square root of x)
        while (i <= x / i) {
            // if the number x is divisible by i, then the number is not prime
            if (x % i == 0) {
                return false;
            }
            // if the number x is not divisible by i, then keep incrementing i until it is x - 1
            i++;
        }
        // if the while loop has completed and it did not return false, then the number x is prime
        return true;
    }

    // Returns the number of primes <= n.
    private static int primes(int n) {
        // For each 2 <= i <= n, use isPrime() to test if i is prime, and if so increment a count.
        // At the end return the count.
        // start the total number of primes at 0
        int totalprimes = 0;
        // 1 and 2 are already prime, so starting at 2, if i is prime, increment the total number
        // of primes by 1, continue this loop until i is equal to the number n
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                totalprimes++;
            }
        }
        // return the total number of primes
        return totalprimes;
    }
}
