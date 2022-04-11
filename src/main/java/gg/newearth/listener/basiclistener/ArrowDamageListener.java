package gg.newearth.listener.basiclistener;

import gg.newearth.Main;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class ArrowDamageListener implements Listener {

    private static final double DAMAGE_INCREASE = Main.getInstance().getConfig().getDouble("ARROW_DAMAGE_INCREASE");

    @EventHandler(ignoreCancelled = true)
    public void onArrowHit(EntityDamageByEntityEvent ev) {
        if (ev.getEntity().getType() != EntityType.PLAYER)
            return;

        if (ev.getDamager() instanceof Arrow) {
            ProjectileSource projectileSource = ((Arrow) ev.getDamager()).getShooter();
            if (projectileSource instanceof Player) {
                ev.setDamage(ev.getDamage() * DAMAGE_INCREASE);
            }
        }
    }
}
