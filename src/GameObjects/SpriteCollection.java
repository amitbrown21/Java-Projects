package GameObjects;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * The SpriteCollection class represents a collection of sprites.
 * It manages adding, notifying time passed, and drawing all sprites on a DrawSurface.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * Constructs a new SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a new sprite to the collection.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notifies all the sprites in the collection that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(sprites);

        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all the sprites in the collection on the specified DrawSurface.
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Removes a sprite from the collection.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}