package gg.newearth.listener.basiclistener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GrindstoneListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getClickedInventory().getType() == InventoryType.GRINDSTONE
        && e.getSlotType() == InventoryType.SlotType.RESULT){
            Inventory inv = e.getClickedInventory();
            if(e.getCurrentItem() != null){
                if(e.getInventory().getItem(0) == null
                || e.getInventory().getItem(1) == null){
                    //Disenchant
                    if(isBook(e.getCurrentItem().getType()) && Math.random() <= 0.3){
                        if(inv.getItem(0) != null){
                            inv.remove(inv.getItem(0));
                        } else {
                            inv.remove(inv.getItem(1));
                        }
                        e.getWhoClicked().sendMessage(ChatColor.RED + "Your book was destroyed when disenchanting!");
                        e.setCancelled(true);
                    }
                } else {
                    //Repair
                    if(!isBook(e.getCurrentItem().getType()) && Math.random() <= 0.1){
                        inv.remove(inv.getItem((int) (Math.round(Math.random()))));
                        e.getWhoClicked().sendMessage(ChatColor.RED + "Your equipment was destroyed when repairing!");
                        e.setCancelled(true);
                    }
                }

            }
        }
    }

    public boolean isBook(Material m) {
        if(m == Material.BOOK){
            return true;
        } else {
            return false;
        }
    }
}
