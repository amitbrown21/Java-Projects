package GameSettings;

import GameObjects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameSettings.GameEnvironment class represents the environment in which a game takes place.
 * <p>
 * It manages a collection of GameSettings.Game.GameObjects.Collidable.GameSettings.Game.GameObjects.Collidable objects and provides methods for detecting collisions between them.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Creates a new GameSettings.GameEnvironment object with an empty collection of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the collection of collidables.
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes a collidable object from the collection of collidables.
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Returns information about the closest collision that is going to occur between a line trajectory and a collidable
     * object in this collection.
     * If no collision occurs, returns null.
     * @param trajectory the line trajectory of the moving object
     * @return the GameSettings.Game.GameObjects.Collidable.GameObjects.CollisionInfo object representing the closest collision that is going to occur, or null if no
     *         collision will occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestObject = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            List<Point> intersectionPoints = rect.intersectionPoints(trajectory);
            if (intersectionPoints.size() > 0) {
                Point intersectionPoint = intersectionPoints.get(0);
                double distance = intersectionPoint.distance(trajectory.start());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestPoint = intersectionPoint;
                    closestObject = c;
                }
            }
        }
        if (closestObject == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestObject);
    }

    public List<Collidable> getCollidables() {
        return collidables;
    }
}