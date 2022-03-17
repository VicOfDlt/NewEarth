package gg.newearth.listener.basiclistener;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class ShieldListener implements Listener {

    private static final short SHIELD_COOLDOWN = 3;

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent ev) {

        if (ev.getEntity().getType() != EntityType.PLAYER)
            return;

        Player victim = (Player) ev.getEntity();
        if (!victim.isBlocking())
            return;

        if (ev.getDamager().getType() == EntityType.PLAYER) {
            victim.setCooldown(Material.SHIELD, SHIELD_COOLDOWN * 20);
        } else if (ev.getDamager() instanceof Arrow) {
            ProjectileSource projectileSource = ((Arrow) ev.getDamager()).getShooter();
            if (projectileSource instanceof Player) {
                victim.setCooldown(Material.SHIELD, SHIELD_COOLDOWN * 20);
            }
        }
    }
}
