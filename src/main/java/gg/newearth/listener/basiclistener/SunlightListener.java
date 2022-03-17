package gg.newearth.listener.basiclistener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

public class SunlightListener implements Listener {

    @EventHandler
    public void onCropGrow(BlockGrowEvent e){
        if (e.getBlock().getType() == Material.NETHER_WART)
            return;

        if(e.getBlock().getLightFromSky() < 15){
            e.setCancelled(true);
        }
    }
}
