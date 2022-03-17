package gg.newearth.listener.basiclistener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

public class EXPBottleListener implements Listener {

    private static Random random = new Random();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if (event.getClickedBlock().getType() != Material.ENCHANTING_TABLE)
            return;

        ItemStack item = event.getItem();
        if(item == null)
            return;

        if (item.getType() != Material.GLASS_BOTTLE)
            return;

        Player player = event.getPlayer();

        // CBA calculating virtual exp amount
        if (player.getLevel() < 2) // if player exp < 16, return
            return;

        event.setCancelled(true);

        player.giveExp(-16);
        item.setAmount(item.getAmount() - 1);
        player.getInventory().addItem(new ItemStack(Material.EXPERIENCE_BOTTLE)).values().forEach(leftover -> dropItem(player, leftover));
    }

    private static void dropItem(Player player, ItemStack item) {
        Location playerLoc = player.getLocation();

        double d1 = Math.sin(playerLoc.getPitch() * 0.017453292);
        double d2 = Math.cos(playerLoc.getPitch() * 0.017453292);
        double d3 = Math.sin(playerLoc.getYaw() * 0.017453292);
        double d4 = Math.cos(playerLoc.getYaw() * 0.017453292);
        double d5 = random.nextDouble() * 6.2831855;
        double d6 = 0.02F * random.nextDouble();

        Item itemEntity = player.getWorld().dropItem(new Location(player.getWorld(), playerLoc.getX(), playerLoc.getY() + player.getEyeHeight() - 0.3D, playerLoc.getZ()), item);

        itemEntity.setVelocity(new Vector((-d3 * d2 * 0.3F) + Math.cos(d5) * d6, -d1 * 0.3F + 0.1F + (random.nextDouble() - random.nextDouble()) * 0.1F, (d4 * d2 * 0.3F) + Math.sin(d5) * d6));
    }
}
