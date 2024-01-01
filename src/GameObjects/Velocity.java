package GameObjects;

/**
 * The Shapes.GameObjects.Velocity class represents a vector that specifies the change in position on the x and y axes.
 */
public class Velocity {
    private double dx; // the change in position on the x-axis
    private double dy; // the change in position on the y-axis

    /**
     * Creates a new instance of the Shapes.GameObjects.Velocity class with the specified changes in position on the x and y axes.
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the change in position on the x-axis.
     * @return the change in position on the x-axis
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in position on the y-axis.
     * @return the change in position on the y-axis
     */
    public double getDy() {
        return dy;
    }

    /**
     * Returns the speed of this velocity vector.
     * @return the speed of this velocity vector
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns the angle of this velocity vector in radians.
     * @return the angle of this velocity vector in radians
     */
    public double getAngle() {
        return Math.atan2(dy, dx) * 180 / Math.PI;
    }

    /**
     * Returns a new point that results from applying this velocity to the specified point.
     * @param p the point to which this velocity should be applied
     * @return a new point that results from applying this velocity to the specified point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Returns a new Shapes.GameObjects.Velocity instance with the specified angle and speed.
     * @param angle the angle in radians
     * @param speed the speed in units per second
     * @return a new Shapes.GameObjects.Velocity instance with the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle * Math.PI / 180;
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }

    public Velocity reverseVelocity() {
        double newDx = -dx;
        double newDy = -dy;
        return new Velocity(newDx, newDy);

    }
}