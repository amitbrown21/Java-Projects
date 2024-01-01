package GameObjects;

import GameSettings.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The ScoreIndicator class is responsible for displaying the current score on the screen.
 * It implements the Sprite interface.
 */
public class ScoreIndicator implements Sprite {
    private static final int SCORE_TEXT_SIZE = 20;
    private static final int SCORE_TEXT_X = 10;
    private static final int SCORE_TEXT_Y = 20;

    private Counter score;

    /**
     * Constructs a ScoreIndicator with the given score counter.
     * @param score the score counter
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the score on the given DrawSurface.
     * @param d the DrawSurface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        String scoreText = "Score: " + this.score.getValue();
        d.drawText(SCORE_TEXT_X, SCORE_TEXT_Y, scoreText, SCORE_TEXT_SIZE);
    }

    /**
     * Updates the score indicator. Since it is not animated, no changes are made.
     */
    public void timePassed() {
        // No need to update anything for the score indicator
    }
}
