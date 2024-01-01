package GameObjects;


/**
 * The GameSettings.Game.GameObjects.Collidable.GameObjects.CollisionInfo class represents information about a collision between a ball and a collidable object.
 * <p>
 * It contains the collision point and the collidable object that was collided with.
 */
public class CollisionInfo {

    /**
     * The point at which the collision occurred.
     */
    private Point collisionPoint;

    /**
     * The collidable object that was collided with.
     */
    private Collidable collisionObject;

    /**
     * Constructs a new GameSettings.Game.GameObjects.Collidable.GameObjects.CollisionInfo object with the specified collision point and collidable object.
     * @param collisionPoint the point at which the collision occurred
     * @param collisionObject the collidable object that was collided with
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurred.
     * @return the collision point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Returns the collidable object that was collided with.
     * @return the collision object
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}