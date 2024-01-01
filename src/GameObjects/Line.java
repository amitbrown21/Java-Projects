package GameObjects;

import java.util.List;

/**
 * The Shapes.GameObjects.Line class represents a line segment in a two-dimensional space.
 */
public class Line {
    /**
     * The starting point of the line segment.
     */
    private final Point start;
    /**
     * The ending point of the line segment.
     */
    private final Point end; // The ending point of the line segment.

    /**
     * Constructs a new Shapes.GameObjects.Line object with the specified  points.
     * @param start the starting point of the line segment.
     * @param end the ending point of the line segment.
     */
    public Line(final Point start, final Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Shapes.GameObjects.Line object with the specified coordinates
     * of the starting and ending points.
     * @param x1 the x-coordinate of the starting point.
     * @param y1 the y-coordinate of the starting point.
     * @param x2 the x-coordinate of the ending point.
     * @param y2 the y-coordinate of the ending point.
     */
    public Line(final double x1, final double y1, final double x2,
                final double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Returns the length of the line segment.
     * @return the length of the line segment.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line segment.
     * @return the middle point of the line segment.
     */
    public Point middle() {
        double x = (start.getX() + end.getX()) / 2.0;
        double y = (start.getY() + end.getY()) / 2.0;
        return new Point(x, y);
    }

    /**
     * Returns the starting point of the line segment.
     * @return the starting point of the line segment.
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the ending point of the line segment.
     * @return the ending point of the line segment.
     */
    public Point end() {
        return end;
    }

    /**
     * Determines whether the line segment intersects with another line segment.
     * @param other the other line segment to test for intersection.
     * @return true if the line segments intersect, false otherwise.
     */
    public boolean isIntersecting(final Line other) {
        Point p = intersectionWith(other);
        return p != null;
    }

    /**
     * Returns a randomly generated Shapes.GameObjects.Line object.
     * @param maxX the maximum x-axis position of the point
     * @param maxY the maximum y-axis position of the point
     * @return a randomly generated Shapes.GameObjects.Line object.
     */
    public static Line generateRandomLine(final int maxX, final int maxY) {
        return new Line(Point.generateRandomPoint(maxX, maxY),
                Point.generateRandomPoint(maxX, maxY));
    }

    /**
     * Returns the intersection point of the line segment with
     * another line segment.
     * @param other the other line segment to test for intersection.
     * @return the intersection point of the two line
     *         segments, or null if they do not intersect.
     */
    public Point intersectionWith(Line other) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = other.start().getX();
        double y3 = other.start().getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();
        if ((x1 == x3 || x1 == x4) && (y1 == y3 || y1 == y4))
            return new Point(x1, y1);
        if ((x2 == x3 || x2 == x4) && (y2 == y3 || y2 == y4))
            return new Point(x2, y2);

        double dx1 = x2 - x1;
        double dy1 = y2 - y1;
        double dx2 = x4 - x3;
        double dy2 = y4 - y3;

        double det = dx1 * dy2 - dy1 * dx2;

        // Check if lines are parallel
        if (Math.abs(det) < 1e-8) {
            return null;
        }

        double t1 = ((x3 - x1) * dy2 - (y3 - y1) * dx2) / det;
        double t2 = ((x3 - x1) * dy1 - (y3 - y1) * dx1) / det;

        // Check if intersection point lies within the line segments
        if (t1 >= 0 && t1 <= 1 && t2 >= 0 && t2 <= 1) {
            double x = x1 + t1 * dx1;
            double y = y1 + t1 * dy1;
            return new Point(x, y);
        }

        return null;
    }

    /**
     * Equals boolean.
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(final Line other) {
        return start.equals(other.start()) && end.equals(other.end());
    }

    /**
     * Calculates the closest intersection point between this line and a given rectangle.
     * If there is no intersection, return null.
     * @param rect the rectangle to check for intersection with
     * @return the closest intersection point to the start of the line, or null if there is no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Get a list of all the intersection points between the line and the rectangle
        List<Point> intersectionPoints = rect.intersectionPoints(this);

        // If there are no intersection points, return null
        if (intersectionPoints.isEmpty()) {
            return null;
        }

        // Otherwise, find the closest intersection point to the start of the line
        double minDistance = Double.MAX_VALUE;
        Point closestPoint = null;

        for (Point intersectionPoint : intersectionPoints) {
            double distance = intersectionPoint.distance(this.start);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = intersectionPoint;
            }
        }

        return closestPoint;
    }

    public static void main(String[] args) {
        Line line1 = new Line(0, 0, 1, 0);
        Line line2 = new Line(0, 0, 0, 1);
        Point intersection = line1.intersectionWith(line2);
        if (intersection == null) {
            System.out.println("I want to Die.");
        } else {
            System.out.println("The lines intersect at " + intersection.getX() + intersection.getY());
        }

    }
}
