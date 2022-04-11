package gg.newearth.listener.basiclistener;

import gg.newearth.Main;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class HorseListener implements Listener {

    private static final double DAMAGE_REDUCTION = Main.getInstance().getConfig().getDouble("HORSE_DAMAGE_REDUCTION");

    @EventHandler(ignoreCancelled = true)
    public void onHorseDamage(EntityDamageEvent ev) {
        if (!(ev.getEntity() instanceof Horse))
            return;

        Horse horse = (Horse) ev.getEntity();
        if (ev.getCause() == EntityDamageEvent.DamageCause.FIRE || ev.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
            ev.setCancelled(true);
            return;
        }

        ev.setDamage(ev.getDamage() - ev.getDamage() * DAMAGE_REDUCTION);

        if (horse.getPassengers().size() == 0)
            return;

        new BukkitRunnable() {
            public void run() {
                unRearHorse(horse);
            }
        }.runTaskLater(Main.getInstance(), 5);

        new BukkitRunnable() {
            public void run() {
                unRearHorse(horse);
            }
        }.runTaskLater(Main.getInstance(), 10);

        new BukkitRunnable() {
            public void run() {
                unRearHorse(horse);
            }
        }.runTaskLater(Main.getInstance(), 15);

        new BukkitRunnable() {
            public void run() {
                unRearHorse(horse);
            }
        }.runTaskLater(Main.getInstance(), 19);

    }

    private void unRearHorse(Horse horse) {
        if (!horse.isDead() && horse.isRearing()) {
            horse.setRearing(false);
        }
    }
}
