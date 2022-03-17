package gg.newearth.listener.basiclistener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.concurrent.ThreadLocalRandom;

public class CropXPListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent ev) {
        Block block = ev.getBlock();
        if (block.getType() == Material.WHEAT || block.getType() == Material.POTATOES
                || block.getType() == Material.CARROTS || block.getType() == Material.BEETROOTS) {
            Ageable ageable = (Ageable) block.getBlockData();
            if (ageable.getAge() == ageable.getMaximumAge()) {
                int randomInt = ThreadLocalRandom.current().nextInt(4) + 1;
                if (randomInt == 1) {
                    ev.getPlayer().giveExp(1);
                }
            }
        }
    }
}
