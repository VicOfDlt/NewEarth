package gg.newearth.listener.basiclistener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.Random;

public class ArmorDamageListener implements Listener {

    private static final Random random = new Random();

    @EventHandler
    public void onArmorDamage(PlayerItemDamageEvent event) {
        String name = event.getItem().getType().toString();
        if(!name.endsWith("_HELMET") && !name.endsWith("_CHESTPLATE") && !name.endsWith("_LEGGINGS") && !name.endsWith("_BOOTS"))
            return;

        if (5 < random.nextInt(10)) {
            event.setCancelled(true);
        }
    }
}
