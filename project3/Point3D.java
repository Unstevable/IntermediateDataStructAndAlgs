//Author: Steven Carr
//Last Edited: October 2022
//A class representing a 3D point (x, y, z coordinates)
import java.util.Arrays;
import java.util.Comparator;

import stdlib.StdIn;
import stdlib.StdOut;

public class Point3D implements Comparable<Point3D> {
    private double x; // x coordinate
    private double y; // y coordinate
    private double z; // z coordinate

    // Constructs a point in 3D given its x, y, and z coordinates.
    public Point3D(double x, double y, double z) {
        // Initialize the x, y, and z values accordingly
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Returns the Euclidean distance between this point and other.
    public double distance(Point3D other) {
        // Splitting up the equation
        // Variable for (x1-x2)^2
        double pointx = Math.pow(this.x - other.x, 2);
        // Variable for (y1-y2)^2
        double pointy = Math.pow(this.y - other.y, 2);
        // Variable for (z1-z2)^2
        double pointz = Math.pow(this.z - other.z, 2);

        // Final step in the equation is taking the square root of the sum
        double dist = Math.sqrt(pointx + pointy + pointz);

        // Return the value of the equation
        return dist;
    }

    // Returns a string representation of this point.
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    // Returns a comparison of this point with other based on their respective distances to the
    // origin (0, 0, 0).
    public int compareTo(Point3D other) {
        // Constructs a new 3D Point, as the origin
        Point3D origin = new Point3D(0, 0, 0);

        // Initializes variables to calculate the distance from the
        // origin for both "this" 3D Point and "other" 3D Point
        double firstdist = this.distance(origin);
        double seconddist = other.distance(origin);

        // Return the comparison between the values
        return Double.compare(firstdist, seconddist);
    }

    // Returns a comparator to compare two points by their x-coordinate.
    public static Comparator<Point3D> xOrder() {
        // Simply return new x comparator
        return new XOrder();
    }

    // Returns a comparator to compare two points by their y-coordinate.
    public static Comparator<Point3D> yOrder() {
        // Simply return new y comparator
        return new YOrder();
    }

    // Returns a comparator to compare two points by their z-coordinate.
    public static Comparator<Point3D> zOrder() {
        // Simply return new z comparator
        return new ZOrder();
    }

    // A comparator for comparing two points by their x-coordinate.
    private static class XOrder implements Comparator<Point3D> {
        // Returns a comparison of p1 and p2 by their x-coordinate.
        public int compare(Point3D p1, Point3D p2) {
            // If x1 is greater than x2, return 1
            if (p1.x > p2.x) {
                return 1;
                // If x1 is less than x2, return -1
            } else if (p1.x < p2.x) {
                return -1;
                // If x1 == x2, return 0
            } else {
                return 0;
            }
        }
    }

    // A comparator for comparing two points by their y-coordinate.
    private static class YOrder implements Comparator<Point3D> {
        // Returns a comparison of p1 and p2 by their y-coordinate.
        public int compare(Point3D p1, Point3D p2) {
            // If y1 is greater than y2, return 1
            if (p1.y > p2.y) {
                return 1;
                // If y1 is less than y2, return -1
            } else if (p1.y < p2.y) {
                return -1;
                // If y1 == y2, return 0
            } else {
                return 0;
            }
        }
    }

    // A comparator for comparing two points by their z-coordinate.
    private static class ZOrder implements Comparator<Point3D> {
        // Returns a comparison of p1 and p2 by their z-coordinate.
        public int compare(Point3D p1, Point3D p2) {
            // If z1 is greater than z2, return 1
            if (p1.z > p2.z) {
                return 1;
                // If z1 is less than z2, return -1
            } else if (p1.z < p2.z) {
                return -1;
                // If z1 == z2, return 0
            } else {
                return 0;
            }
        }
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        StdOut.print("How many points? ");
        int n = StdIn.readInt();
        Point3D[] points = new Point3D[n];
        StdOut.printf("Enter %d doubles, separated by whitespace: ", n * 3);
        for (int i = 0; i < n; i++) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            double z = StdIn.readDouble();
            points[i] = new Point3D(x, y, z);
        }
        StdOut.println("Here are the points in the order entered:");
        for (Point3D point : points) {
            StdOut.println("  " + point);
        }
        Arrays.sort(points);
        StdOut.println("Sorted by their natural ordering (compareTo)");
        for (Point3D point : points) {
            StdOut.println("  " + point);
        }
        Arrays.sort(points, Point3D.xOrder());
        StdOut.println("Sorted by their x coordinate (xOrder)");
        for (Point3D point : points) {
            StdOut.println("  " + point);
        }
        Arrays.sort(points, Point3D.yOrder());
        StdOut.println("Sorted by their y coordinate (yOrder)");
        for (Point3D point : points) {
            StdOut.println("  " + point);
        }
        Arrays.sort(points, Point3D.zOrder());
        StdOut.println("Sorted by their z coordinate (zOrder)");
        for (Point3D point : points) {
            StdOut.println("  " + point);
        }
    }
}
