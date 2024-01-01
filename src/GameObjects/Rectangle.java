package GameObjects;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Return a (possibly empty) list of intersection points with the specified line.
     * @param line the line to check for intersection with the rectangle
     * @return a list of intersection points with the specified line
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();

        // Get the lines that make up the rectangle's edges
        Line top = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Line left = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Line right = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Line bottom = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);

        // Check for intersection with each of the rectangle's edges
        if (line.isIntersecting(top)) {
            intersectionPoints.add(line.intersectionWith(top));
        }
        if (line.isIntersecting(left)) {
            intersectionPoints.add(line.intersectionWith(left));
        }
        if (line.isIntersecting(right)) {
            intersectionPoints.add(line.intersectionWith(right));
        }
        if (line.isIntersecting(bottom)) {
            intersectionPoints.add(line.intersectionWith(bottom));
        }

        return intersectionPoints;
    }

    /**
     * Return the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * draws the sprites on the game's surface
     * @param surface the surface on which the sprite is drawn
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);

    }

    /**
     * Returns the color of the rectangle
     * @return the color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }
}
