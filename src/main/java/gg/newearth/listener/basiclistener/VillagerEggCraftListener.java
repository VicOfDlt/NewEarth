package gg.newearth.listener.basiclistener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class VillagerEggCraftListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onVillagerEggCraft(PrepareItemCraftEvent ev) {
        short goldblockCount = 0;
        for (ItemStack item: ev.getInventory().getMatrix()) {
            if (item == null)
                break;
            if (item.getType() != Material.GOLD_BLOCK)
                break;
            if (item.getAmount() != 8)
                break;
            goldblockCount++;
        }

        if (goldblockCount == 9) {
            ev.getInventory().setResult(new ItemStack(Material.VILLAGER_SPAWN_EGG));
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onVillagerEggClick(InventoryClickEvent ev) {
        if (ev.getClickedInventory() == null)
            return;

        if (ev.getClickedInventory().getType() != InventoryType.WORKBENCH)
            return;

        if (ev.getSlotType() != InventoryType.SlotType.RESULT)
            return;

        if (ev.getCurrentItem() == null)
            return;

        if (ev.getCurrentItem().getType() != Material.VILLAGER_SPAWN_EGG)
            return;

        for (ItemStack item: ((CraftingInventory) ev.getClickedInventory()).getMatrix()) {
            item.setAmount(0);
        }
    }
}
