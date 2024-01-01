package GameObjects;

import java.util.List;

public interface HitNotifier {
    /**
     * Add a listener to be notified of hit events.
     * @param hl the listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove a listener from the list of hit event listeners.
     * @param hl the listener to remove
     */
    void removeHitListener(HitListener hl);

    /**
     * Get a copy of the list of hit event listeners.
     * @return a copy of the hit event listeners
     */
    List<HitListener> getHitListeners();
}
