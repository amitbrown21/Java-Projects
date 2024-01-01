package GameSettings;

import GameObjects.*;
import GameObjects.Point;
import GameObjects.Rectangle;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * The GameSettings.Game class represents the game itself, which is responsible for managing and running the game.
 * <p>
 * It contains a collection of Sprites, a game environment, a GUI, a sleeper, and a keyboard sensor.
 */
public class Game {
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;
    private static final int BALL_RADIUS = 5;
    private static final int BALL_SPEED = 3;
    private static final int BLOCK_WIDTH = 45;
    private static final int BLOCK_HEIGHT = 25;
    private static final int NUM_ROWS = 5;
    private static final int BLOCKS_PER_ROW = 12;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private biuoop.Sleeper sleeper;
    private KeyboardSensor keyboard;
    private Counter remainingBlocks;
    private Counter ballsCounter;
    private Counter score;
    private ScoreIndicator scoreIndicator;

    /**
     * Constructs a new GameSettings.Game object with default settings.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", GAME_WIDTH, GAME_HEIGHT);
        this.sleeper = new biuoop.Sleeper();
        this.keyboard = gui.getKeyboardSensor();
        this.remainingBlocks = new Counter();
        this.ballsCounter = new Counter();
        this.score = new Counter();
        this.scoreIndicator = new ScoreIndicator(score);
    }

    /**
     * Adds a GameSettings.Game.GameObjects.Collidable.GameSettings.Game.GameObjects.Collidable object to the game environment.
     * @param c the GameSettings.Game.GameObjects.Collidable.GameSettings.Game.GameObjects.Collidable object to be added
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a GameObjects.Sprite object to the game.
     * @param s the GameObjects.Sprite object to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes the game by creating the game environment, adding the borders, paddle, ball, and blocks.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);

        // create the game environment and add the borders
        Block upperBorder = new Block(new Rectangle(new Point(0, 0), 800, 30, Color.gray));
        Block leftBorder = new Block(new Rectangle(new Point(0, 30), 30, 570, Color.gray));
        Block rightBorder = new Block(new Rectangle(new Point(770, 30), 30, 570, Color.gray));
        Block death = new Block(new Rectangle(new Point(0, 570), 800, 30, Color.gray));
        this.environment.addCollidable(upperBorder);
        this.environment.addCollidable(leftBorder);
        this.environment.addCollidable(rightBorder);
        this.environment.addCollidable(death);
        leftBorder.addToGame(this);
        upperBorder.addToGame(this);
        death.addToGame(this);
        rightBorder.addToGame(this);
        death.addHitListener(new BallRemover(this, ballsCounter));

        // create the paddle
        Paddle paddle = new Paddle(new Point(375, 550),
                PADDLE_WIDTH, PADDLE_HEIGHT,
                Color.cyan, this.keyboard);
        paddle.addToGame(this);

        // create the balls
        Ball ball = new Ball(new Point(400, 400), BALL_RADIUS, Color.pink,
                this.environment);
        Velocity initialVelocity = Velocity.fromAngleAndSpeed(90, BALL_SPEED);
        ball.setVelocity(initialVelocity);
        ball.addToGame(this);
        ballsCounter.increase(1);

        Ball ball2 = new Ball(new Point(300, 300), BALL_RADIUS, Color.pink,
                this.environment);
        initialVelocity = Velocity.fromAngleAndSpeed(70, BALL_SPEED);
        ball2.setVelocity(initialVelocity);
        ball2.addToGame(this);
        ballsCounter.increase(1);

        Ball ball3 = new Ball(new Point(400, 400), BALL_RADIUS,
                Color.pink,
                this.environment);
         initialVelocity = Velocity.fromAngleAndSpeed(80,
                BALL_SPEED);
        ball3.setVelocity(initialVelocity);
        ball3.addToGame(this);
        ballsCounter.increase(1);
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
        int x = 770 - BLOCK_WIDTH; // Start from the right side of the screen
        int y = 100;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < BLOCKS_PER_ROW - i; j++) {
                Block block =
                        new Block(new Rectangle(new Point(x - j * (BLOCK_WIDTH), y + i * (BLOCK_HEIGHT)),
                                BLOCK_WIDTH, BLOCK_HEIGHT, colors[i]));
                block.addHitListener(blockRemover);
                block.addHitListener(scoreListener);
                this.remainingBlocks.increase(1);
                block.addToGame(this);
            }
        }
    }

    /**
     * Runs the game in default setting.
     */
    public void run() {
        int millisecondsPerFrame = 1000 / FRAMES_PER_SECOND;
        while (this.remainingBlocks.getValue() > 0 && this.ballsCounter.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(new Color(10, 60, 100));
            d.fillRectangle(0, 0, GAME_WIDTH, GAME_HEIGHT);
            this.sprites.drawAllOn(d);
            scoreIndicator.drawOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();


            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (remainingBlocks.getValue() == 0) {
            score.increase(100);
        }
        this.gui.close();
    }


    /**
     * Removes a Collidable object from the game environment.
     * @param c the Collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a Sprite object from the game.
     * @param s the Sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}
