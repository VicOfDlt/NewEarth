package gg.newearth.listener.basiclistener;

import gg.newearth.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class TNTMinecartDamageListener implements Listener {
    private static final double DAMAGE_MULTIPLIER = Main.getInstance().getConfig().getDouble("TNTMinecartDamageMultiplier");
    @EventHandler(ignoreCancelled = true)
    public void onArrowHit(EntityDamageByEntityEvent ev) {
        if (ev.getEntity().getType() != EntityType.PLAYER)
            return;

        if (ev.getDamager() instanceof ExplosiveMinecart) {
            ev.setDamage(ev.getDamage() * DAMAGE_MULTIPLIER);
        }
    }
}
