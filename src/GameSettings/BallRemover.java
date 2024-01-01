package GameSettings;

import GameObjects.*;

/**
 * The BallRemover class is responsible for removing balls from the game when they hit the death region.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter ballsCounter;

    /**
     * Constructs a BallRemover object with the specified game and balls counter.
     * @param game the game instance.
     * @param ballsCounter the counter for tracking the number of balls.
     */
    public BallRemover(Game game, Counter ballsCounter) {
        this.game = game;
        this.ballsCounter = ballsCounter;
    }

    /**
     * Handles the hit event when a ball hits the death region.
     * @param deathRegion the block representing the death region.
     * @param ball the ball that hit the death region.
     */
    @Override
    public void hitEvent(Block deathRegion, Ball ball) {
        ball.removeFromGame(game);
        ballsCounter.decrease(1);
    }
}
