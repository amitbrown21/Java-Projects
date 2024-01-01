package GameObjects;

import GameSettings.*;
import biuoop.DrawSurface;

/**
 * The Ball class represents a 2D ball with a center point, radius, color, and velocity.
 */
public class Ball implements Sprite {
    private Point center;                   // The center point of the ball
    private int radius;                     // The radius of the ball
    private java.awt.Color color;           // The color of the ball
    private Velocity velocity;              // The velocity of the ball
    private GameEnvironment environment;    // The game environment for collision detection

    /**
     * Constructs a new Ball object with the specified center point, radius, color, and game environment.
     * The ball's initial velocity is set to (0,0).
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color the color of the ball
     * @param environment the game environment for collision detection
     */
    public Ball(Point center, int radius, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.environment = environment;
    }

    /**
     * Constructs a new Ball object with the specified center coordinates, radius, color, and game environment.
     * The ball's initial velocity is set to (0,0).
     * @param x the x coordinate of the center point of the ball
     * @param y the y coordinate of the center point of the ball
     * @param radius the radius of the ball
     * @param color the color of the ball
     * @param environment the game environment for collision detection
     */
    public Ball(int x, int y, int radius, java.awt.Color color, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.environment = environment;
    }

    /**
     * Returns the x coordinate of the center point of the ball.
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y coordinate of the center point of the ball.
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Returns the center point of the ball.
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Returns the color of the ball.
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     * @param surface the DrawSurface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Sets the velocity of the ball.
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball given its dx and dy values.
     * @param dx the change in x position of the ball for each unit of time
     * @param dy the change in y position of the ball for each unit of time
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity of the ball.
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball one step within a boundary defined by a start point and dimensions.
     * If the ball reaches the boundary, it will bounce back.
     * @param boundaryWidth the width of the boundary
     * @param boundaryHeight the height of the boundary
     * @param startX the x coordinate of the start point of the boundary
     * @param startY the y coordinate of the start point of the boundary
     */
    public void moveOneStep(int boundaryWidth, int boundaryHeight, int startX, int startY) {
        // Calculate the next center point of the ball based on its current velocity.
        Point nextCenter = this.getVelocity().applyToPoint(this.center);
        int nextX = (int) nextCenter.getX();
        int nextY = (int) nextCenter.getY();

        // Check if the ball goes outside the boundaries
        if (nextX - this.getSize() < startX) {
            // If the ball reaches the left boundary, set it to the boundary and reverse its x velocity
            nextX = this.getSize() + startX;
            this.setVelocity(new Velocity(-this.getVelocity().getDx(), this.getVelocity().getDy()));
        }
        if (nextX + this.getSize() > boundaryWidth + startX) {
            // If the ball reaches the right boundary, set it to the boundary and reverse its x velocity
            nextX = boundaryWidth + startX - this.getSize();
            this.setVelocity(new Velocity(-this.getVelocity().getDx(), this.getVelocity().getDy()));
        }
        if (nextY - this.getSize() < startY) {
            // If the ball reaches the top boundary, set it to the boundary and reverse its y velocity
            nextY = this.getSize() + startY;
            this.setVelocity(new Velocity(this.getVelocity().getDx(), -this.getVelocity().getDy()));
        }
        if (nextY + this.getSize() > boundaryHeight + startY) {
            // If the ball reaches the bottom boundary, set it to the boundary and reverse its y velocity
            nextY = boundaryHeight - this.getSize() + startY;
            this.setVelocity(new Velocity(this.getVelocity().getDx(), -this.getVelocity().getDy()));
        }

        // Update the position of the ball
        this.center = new Point(nextX, nextY);
    }

    /**
     * Moves the ball one step within a boundary defined by a start point and dimensions.
     * If the ball reaches the boundary, it will bounce back.
     * @param boundaryWidth the width of the boundary
     * @param boundaryHeight the height of the boundary
     */
    public void moveOneStep(int boundaryWidth, int boundaryHeight) {
        Point nextCenter = this.getVelocity().applyToPoint(this.center);
        int nextX = (int) nextCenter.getX();
        int nextY = (int) nextCenter.getY();

        // Check if the ball goes outside the boundaries
        if (nextX - this.getSize() < 0) {
            nextX = this.getSize();
            this.setVelocity(new Velocity(-this.getVelocity().getDx(), this.getVelocity().getDy()));
        }
        if (nextX + this.getSize() > boundaryWidth) {
            nextX = boundaryWidth - this.getSize();
            this.setVelocity(new Velocity(-this.getVelocity().getDx(), this.getVelocity().getDy()));
        }
        if (nextY - this.getSize() < 0) {
            nextY = this.getSize();
            this.setVelocity(new Velocity(this.getVelocity().getDx(), -this.getVelocity().getDy()));
        }
        if (nextY + this.getSize() > boundaryHeight) {
            nextY = boundaryHeight - this.getSize();
            this.setVelocity(new Velocity(this.getVelocity().getDx(), -this.getVelocity().getDy()));
        }

        this.center = new Point(nextX, nextY);
    }

    /**
     * Moves the ball one step.
     * If the ball collides with any collidable objects, it will change its velocity accordingly.
     */
    public void moveOneStep() {
        // Calculate the ball's trajectory
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        // Check if the trajectory collides with any collidable objects
        CollisionInfo collisionInfo = environment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            // There was a collision - update the velocity
            Velocity newVelocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity);
            if (newVelocity == null) {
                newVelocity = this.velocity.reverseVelocity();
                this.center = newVelocity.applyToPoint(this.center);
            }
            this.velocity = newVelocity;
        }

        // Update the ball's position
        this.center = this.velocity.applyToPoint(this.center);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds the ball to the game.
     * @param game the game to add the ball to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Removes the ball from the game.
     * @param game the game to remove the ball from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}
