package fight;

import entity.Entity;
import entity.Player;

public class EntityFight extends Fight {
    /**
     * Entity Fight constructor
     *
     * @param p Human player
     * @param e Opponent entity
     */
    public EntityFight(Player p, Entity e, JFrame window) {
        super(p, e, window);
    }
}
