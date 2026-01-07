//Author: Steven Carr
//Last Edited: October 2022
//A class which represents a Location with it's latitude and longitude
import java.util.Arrays;

import stdlib.StdOut;

public class Location implements Comparable<Location> {
    private String name; // location name
    private double lat;  // latitude
    private double lon;  // longitude

    // Constructs a new location given its name, latitude, and longitude.
    public Location(String name, double lat, double lon) {
        // Initialize instance variables
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    // Returns the great-circle distance between this location and other.
    public double distanceTo(Location other) {
        // Convert the current latitude and longitude to radians
        double a1 = Math.toRadians(lat);
        double b1 = Math.toRadians(lon);

        // Convert the latitude and longitude of "other" to radians
        double a2 = Math.toRadians(other.lat);
        double b2 = Math.toRadians(other.lon);

        // Final full equation
        double dist = 6359.83 * Math.acos((Math.sin(a1) * Math.sin(a2)) +
                ((Math.cos(a1) * Math.cos(a2)) * Math.cos(b1 - b2)));

        // Return the result of the equation
        return dist;
    }

    // Returns true if this location is the same as other, and false otherwise.
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        // Set Location first to this, and cast other as Location
        // second, because the previous statements confirmed it must
        // be of the same class
        Location first = this, second = (Location) other;

        // Return a comparison between the latitudes and longitudes of
        // both the first location and the second location
        return first.lat == second.lat && first.lon == second.lon;
    }

    // Returns a string representation of this location.
    public String toString() {
        return name + " (" + lat + ", " + lon + ")";
    }

    // Returns a comparison of this location with other based on their respective distances to
    // the origin, Parthenon (Greece) @ 37.971525, 23.726726.
    public int compareTo(Location that) {
        // Construct a new Location object to be set to the origin
        Location origin = new Location("Parthenon (Greece)", 37.971525, 23.726726);

        // Variables to determine the distance of the first location to the
        // origin, and the second location to the origin
        double firstdist = this.distanceTo(origin);
        double seconddist = that.distanceTo(origin);

        // Compare the two distances
        return Double.compare(firstdist, seconddist);
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int rank = Integer.parseInt(args[0]);
        String name = args[1];
        double lat = Double.parseDouble(args[2]);
        double lon = Double.parseDouble(args[3]);
        Location[] wonders = new Location[7];
        wonders[0] = new Location("The Great Wall of China (China)", 40.6769, 117.2319);
        wonders[1] = new Location("Petra (Jordan)", 30.3286, 35.4419);
        wonders[2] = new Location("The Colosseum (Italy)", 41.8902, 12.4923);
        wonders[3] = new Location("Chichen Itza (Mexico)", 20.6829, -88.5686);
        wonders[4] = new Location("Machu Picchu (Peru)", -13.1633, -72.5456);
        wonders[5] = new Location("Taj Mahal (India)", 27.1750, 78.0419);
        wonders[6] = new Location("Christ the Redeemer (Brazil)", 22.9519, -43.2106);
        Arrays.sort(wonders);
        StdOut.println("Seven wonders, in the order of their distance to Parthenon (Greece):");
        for (Location wonder : wonders) {
            StdOut.println("  " + wonder);
        }
        Location loc = new Location(name, lat, lon);
        StdOut.print("wonders[" + rank + "] == " + loc + "? ");
        StdOut.println(wonders[rank].equals(loc));
    }
}
