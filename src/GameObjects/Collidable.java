package GameObjects;

/**
 * The GameSettings.Game.GameObjects.Collidable.GameSettings.Game.GameObjects.Collidable interface represents an object that can be collided with.
 * <p>
 * It defines methods for getting the collision shape of the object and handling collisions.
 */
public interface Collidable {

    /**
     * Returns the collision shape of the object as a rectangle.
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Handles a collision with the object at the specified collision point and with the specified current velocity.
     * Returns the new velocity expected after the hit based on the force the object inflicted on us.
     * @param collisionPoint the point at which the collision occurred
     * @param currentVelocity the velocity of the object before the collision
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint,
                 Velocity currentVelocity);
}