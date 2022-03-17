package gg.newearth.listener.basiclistener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class DisableFortuneOnGoldOreListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();

        Player player = e.getPlayer();
        if (!player.getInventory().getItemInMainHand().getType().toString().toLowerCase().endsWith("pickaxe"))
            return;

        if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH))
            return;

        if (block.getType() == Material.GOLD_ORE || block.getType() == Material.DEEPSLATE_GOLD_ORE) {
            e.setDropItems(false);
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.RAW_GOLD));
        }
    }
}