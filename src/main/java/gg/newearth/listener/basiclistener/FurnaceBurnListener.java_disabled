package gg.newearth.listener.basiclistener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

public class FurnaceBurnListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onLavaUsedAsFuel(FurnaceBurnEvent ev) {
        Material fuelType = ev.getFuel().getType();
        if (fuelType == Material.LAVA_BUCKET || fuelType == Material.BLAZE_ROD ||
                fuelType == Material.DRIED_KELP_BLOCK || fuelType == Material.BAMBOO ||
                fuelType == Material.SCAFFOLDING ||
                fuelType.toString().toLowerCase().endsWith("carpet") ||
                fuelType.toString().toLowerCase().endsWith("wool") ||
                fuelType.toString().toLowerCase().contains("banner") ||
                fuelType.toString().toLowerCase().endsWith("mushroom_block")) {

            final ItemStack fuel = ev.getFuel().clone();
            ev.getFuel().setAmount(0);
            ev.getBlock().getWorld().dropItemNaturally(ev.getBlock().getLocation().clone(), fuel);
            ev.setCancelled(true);
        }
    }
}
