package gg.newearth.listener.basiclistener;

import gg.newearth.Main;
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
            case PIG, COW, SHEEP -> player.giveExp(Main.getInstance().getConfig().getInt("PIG_COW_SHEEP_XP"));
            case CHICKEN -> player.giveExp(Main.getInstance().getConfig().getInt("CHICKENXP"));
        }
    }
}
