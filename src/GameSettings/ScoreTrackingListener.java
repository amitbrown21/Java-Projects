package GameSettings;

import GameObjects.*;


/**
 * The ScoreTrackingListener class is responsible for tracking and updating the score
 * whenever a block is hit by a ball.
 * It implements the HitListener interface.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener with the given score counter.
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Updates the score when a block is hit by a ball.
     * Increases the current score by 5 for each block hit.
     * @param beingHit the block being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5); // Increase the score by 5 when a block is hit
    }
}
