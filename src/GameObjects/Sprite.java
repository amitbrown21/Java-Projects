package GameObjects;

import biuoop.DrawSurface;

/**
 * The GameObjects.Sprite interface represents an object that can be drawn on a DrawSurface and notified that time has passed.
 * <p>
 * It defines methods for drawing the sprite on a DrawSurface and updating the sprite with the passage of time.
 */
public interface Sprite {

    /**
     * Draws the sprite on the specified DrawSurface.
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();
}