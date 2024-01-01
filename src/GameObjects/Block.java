package GameObjects;

import GameSettings.Game;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a rectangular block that can be collided with and drawn on a DrawSurface.
 * It implements both the Collidable and Sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private List<HitListener> hitListeners;

    /**
     * Constructs a Block from a given rectangle.
     * @param rect the rectangle that defines the block.
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Returns the collision rectangle of the block.
     * @return the rectangle that represents the collision boundaries of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * Determines the new velocity of a colliding object after colliding with the block.
     * @param collisionPoint the point where the collision occurs.
     * @param currentVelocity the current velocity of the colliding object.
     * @return the new velocity of the colliding object after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double hitX = collisionPoint.getX();
        double hitY = collisionPoint.getY();
        double rectWidth = rect.getWidth();
        double rectHeight = rect.getHeight();

        // Determine whether the collision point is inside the rectangle
        boolean insideRect = hitX >= rect.getUpperLeft().getX() &&
                hitX <= rect.getUpperLeft().getX() + rectWidth &&
                hitY >= rect.getUpperLeft().getY() &&
                hitY <= rect.getUpperLeft().getY() + rectHeight;

        if (!insideRect) {
            return null;
        }

        // Determine which side of the rectangle was hit
        boolean hitLeft = Double.compare(hitX, rect.getUpperLeft().getX()) == 0;
        boolean hitRight = Double.compare(hitX, rect.getUpperLeft().getX() + rectWidth) == 0;
        boolean hitTop = Double.compare(hitY, rect.getUpperLeft().getY()) == 0;
        boolean hitBottom = Double.compare(hitY, rect.getUpperLeft().getY() + rectHeight) == 0;

        // Calculate the new velocity based on which side was hit
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (hitLeft || hitRight) {
            dx = -dx;
        }
        if (hitTop || hitBottom) {
            dy = -dy;
        }

        // Notify the hit event to all registered listeners
        notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on a given DrawSurface.
     * @param d the DrawSurface to draw the block on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) rect.getUpperLeft().getX();
        int y = (int) rect.getUpperLeft().getY();
        int height = (int) rect.getHeight();
        int width = (int) rect.getWidth();
        Color color = rect.getColor();
        d.setColor(color);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * Does nothing on time passed (implemented from the Sprite interface).
     */
    @Override
    public void timePassed() {
        // Do nothing
    }

    /**
     * Adds the block to a game as both a collidable and a sprite.
     * @param game the game to add the block to.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Removes the block from a game as both a collidable and a sprite.
     * @param game the game to remove the block from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Adds a hit listener to the block.
     * @param hl the hit listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the block.
     * @param hl the hit listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Returns the list of hit listeners.
     * @return the list of hit listeners.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }
}
