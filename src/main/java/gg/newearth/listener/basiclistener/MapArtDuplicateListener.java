package gg.newearth.listener.basiclistener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

public class MapArtDuplicateListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent ev) {
        if (ev.getClickedInventory() == null)
            return;

        if (ev.getClickedInventory().getType() != InventoryType.CARTOGRAPHY)
            return;

        if (ev.getSlotType() != InventoryType.SlotType.RESULT)
            return;

        if (ev.getCurrentItem() == null)
            return;

        if (ev.getCurrentItem().getType() != Material.FILLED_MAP)
            return;

        ItemStack map = ev.getCurrentItem();
        MapMeta mapMeta = (MapMeta) map.getItemMeta();
        if (mapMeta.getMapView() != null && mapMeta.getMapView().isLocked()) {
            ev.getWhoClicked().sendMessage(ChatColor.RED + "Locked maps cannot be duplicated.");
            ev.setCancelled(true);
        }

    }
}
