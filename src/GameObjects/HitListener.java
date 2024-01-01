package GameObjects;

public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block being hit
     * @param hitter the ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
