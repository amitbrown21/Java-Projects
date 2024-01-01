package GameSettings;

import GameObjects.*;


/**
 * The BlockRemover class is responsible for removing blocks from the game when they are hit.
 * It implements the HitListener interface.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover with the specified game and remaining blocks counter.
     * @param game the game to remove blocks from.
     * @param remainingBlocks the counter representing the remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the hit event by removing the block from the game and decreasing the remaining blocks count.
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    }
}
