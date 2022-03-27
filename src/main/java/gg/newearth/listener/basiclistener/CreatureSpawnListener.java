package gg.newearth.listener.basiclistener;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e){
        Entity entity = e.getEntity();
        boolean remove = checkEntity(entity);
        if(remove) e.setCancelled(true);
    }

    public boolean checkEntity(Entity e){
        return switch (e.getType()) {
            case BAT, COD, LLAMA, TRADER_LLAMA, VEX, ENDERMITE, MULE, DONKEY, POLAR_BEAR -> true;
            default -> false;
        };
    }
}
