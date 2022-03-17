package gg.newearth.abuse;

import gg.newearth.Main;
import gg.newearth.utility.LocationUtility;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class AbuseDetectorListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent ev) {
        if (!ev.getPlayer().isOp())
            return;

        if (ev.getPlayer().getGameMode() != GameMode.CREATIVE)
            return;

        if (!(ev.getBlock().getState() instanceof InventoryHolder))
            return;

        Block block = ev.getBlock();
        Main.getInstance().getLogger().info(ChatColor.RED +
                ev.getPlayer().getName()
                + " placed a block that can contain items. "
                + ChatColor.RESET + "Block: " + block.getType()
                + "Location: " + LocationUtility.getLocationStringFormat(block.getLocation())
        );
    }

    @EventHandler(ignoreCancelled = true)
    public void onAdminDropItem(PlayerDropItemEvent ev) {
        if (!ev.getPlayer().isOp())
            return;

        if (ev.getPlayer().getGameMode() != GameMode.CREATIVE)
            return;

        Player player = ev.getPlayer();
        ItemStack droppedItem = ev.getItemDrop().getItemStack();
        Location playerLoc = player.getLocation().clone();
        Main.getInstance().getLogger().info(
                ChatColor.RED + player.getName() + " dropped items on the ground. "
                        + ChatColor.RESET + "Item: " + droppedItem.getType()
                        +" Amount: " + droppedItem.getAmount()
                        + " Item Location: " + LocationUtility.getLocationStringFormat(ev.getItemDrop().getLocation().clone())
                        + " Player Location: " + LocationUtility.getLocationStringFormat(playerLoc)
        );

        for (Player nearbyPlayer: playerLoc.getNearbyPlayers(100)) {
            double distance = nearbyPlayer.getLocation().clone().distance(playerLoc);
            if (!nearbyPlayer.equals(player))
                Main.getInstance().getLogger().info("Nearby player detected: " + nearbyPlayer.getName() + ", Distance: " + distance);
        }

        Main.getInstance().getLogger().info("");

    }

}
