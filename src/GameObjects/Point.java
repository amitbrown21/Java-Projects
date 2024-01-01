package GameObjects;

import java.util.Random;

/**
 * The Shapes.GameObjects.Point class represents a point in a 2D coordinate system.
 * It has methods to calculate the distance between two points and to
 * generate a random point.
 */
public class Point {

    /**
     * The x coordinate of the point.
     */
    private final double x;

    /**
     * The y coordinate of the point.
     */
    private final double y;

    /**
     * Constructs a point with the specified x and y coordinates.
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     * @param other the other point.
     * @return the distance between this point and the other point.
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Checks if this point is equal to another point.
     * @param other the other point.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Gets the x coordinate of the point.
     * @return the x coordinate of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y coordinate of the point.
     * @return the y coordinate of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Generates a random point with x and y coordinates between
     * 1 and 400 and 1 and 300, respectively.
     * @param maxX the maximum x-axis point possible for the point
     * @param maxY the maximum Y-axis point possible for the point
     * @return a random point.
     */
    public static Point generateRandomPoint(int maxX, int maxY) {
        Random r = new Random();
        return new Point(r.nextInt(maxX) + 1, r.nextInt(maxY) + 1);
    }
}



