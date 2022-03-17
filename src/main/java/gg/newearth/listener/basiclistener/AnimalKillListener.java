package gg.newearth.listener.basiclistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class AnimalKillListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onAnimalDeath(EntityDeathEvent ev) {
        if (ev.getEntity().getKiller() == null)
            return;

        Player player = ev.getEntity().getKiller();
        switch (ev.getEntityType()) {
            case PIG, COW, SHEEP -> player.giveExp(2);
            case CHICKEN -> player.giveExp(1);
        }
    }
}
