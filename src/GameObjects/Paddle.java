package GameObjects;

import GameSettings.*;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The GameSettings.Game.GameObjects.Collidable.GameObjects.Paddle class represents a game paddle that can be moved horizontally using the keyboard.
 * <p>
 * The paddle implements the GameObjects.Sprite and GameSettings.Game.GameObjects.Collidable.GameSettings.Game.GameObjects.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {

    // Private member variables
    private KeyboardSensor keyboard; // The keyboard sensor used to control the paddle's movement
    private Rectangle paddleRectangle; // The rectangle representing the paddle's position and size
    private int speed; // The speed at which the paddle moves
    private static final int NUM_REGIONS = 5; // The number of regions that the paddle is divided into for ball collision detection


    /**
     * Constructor for a GameSettings.Game.GameObjects.Collidable.GameObjects.Paddle object.
     * @param upperLeft The upper left corner of the paddle's rectangle.
     * @param width The width of the paddle's rectangle.
     * @param height The height of the paddle's rectangle.
     * @param color The color of the paddle's rectangle.
     * @param keyboard The keyboard sensor used to control the paddle's movement.
     */
    public Paddle(Point upperLeft, double width, double height, Color color, KeyboardSensor keyboard) {
        this.paddleRectangle = new Rectangle(upperLeft, width, height, color);
        this.keyboard = keyboard;
        this.speed = 5; // set the default speed to 5
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        // Calculate the new x-coordinate of the upper left corner of the paddle's rectangle
        double newX = this.paddleRectangle.getUpperLeft().getX() - this.speed;

        // Check if the new x-coordinate is within the game boundaries (at least 30 pixels away from the left edge)
        if (newX >= 35) {
            // Create a new rectangle with the updated x-coordinate
            this.paddleRectangle = new Rectangle(new Point(newX, this.paddleRectangle.getUpperLeft().getY()),
                    this.paddleRectangle.getWidth(), this.paddleRectangle.getHeight(),
                    this.getCollisionRectangle().getColor());
        }
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        // Calculate the new x-coordinate of the upper left corner of the paddle's rectangle
        double newX = this.paddleRectangle.getUpperLeft().getX() + this.speed;

        // Check if the new x-coordinate is within the game boundaries (at most 770 pixels away from the left edge)
        if (newX + this.paddleRectangle.getWidth() <= 765) {
            // Create a new rectangle with the updated x-coordinate
            this.paddleRectangle = new Rectangle(new Point(newX, this.paddleRectangle.getUpperLeft().getY()),
                    this.paddleRectangle.getWidth(), this.paddleRectangle.getHeight(),
                    this.getCollisionRectangle().getColor());
        }
    }

    /**
     * Called every game tick. Moves the paddle left or right according to the keyboard input.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     * @param d The DrawSurface to draw the paddle on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.getCollisionRectangle().getColor());
        d.fillRectangle((int) this.paddleRectangle.getUpperLeft().getX(),
                (int) this.paddleRectangle.getUpperLeft().getY(),
                (int) this.paddleRectangle.getWidth(),
                (int) this.paddleRectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.paddleRectangle.getUpperLeft().getX(),
                (int) this.paddleRectangle.getUpperLeft().getY(),
                (int) this.paddleRectangle.getWidth(),
                (int) this.paddleRectangle.getHeight());
    }

    // GameSettings.Game.GameObjects.Collidable.GameSettings.Game.GameObjects.Collidable
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double regionWidth = this.paddleRectangle.getWidth() / NUM_REGIONS;
        double regionLeftX = this.paddleRectangle.getUpperLeft().getX();
        double ballSpeed = currentVelocity.getSpeed();
        double ballAngle = currentVelocity.getAngle();

        // Calculate which region the collision point is in
        int region = (int) ((collisionPoint.getX() - regionLeftX) / regionWidth) + 1;

        // Calculate the new velocity based on the region
        if (region == 1) {
            ballAngle = 210;
        } else if (region == 2) {
            ballAngle = 240;
        } else if (region == 3) {
            return new Velocity(currentVelocity.getDx(),
                    -(currentVelocity.getDy()));
        } else if (region == 4) {
            ballAngle = 300;
        } else if (region == 5) {
            ballAngle = 330;
        }

        // Create a new velocity with the same speed as the original, but with the updated angle
        return Velocity.fromAngleAndSpeed(ballAngle, ballSpeed);
    }

    // Add this paddle to the game.
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}
