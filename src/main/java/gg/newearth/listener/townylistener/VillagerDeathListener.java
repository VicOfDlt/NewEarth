package gg.newearth.listener.townylistener;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import gg.newearth.Main;
import gg.newearth.utility.LocationUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class VillagerDeathListener implements Listener {

    @EventHandler
    public void onVillagerDeath(EntityDeathEvent ev) {
        if (ev.getEntity().getType() != EntityType.VILLAGER)
            return;

        Villager villager = (Villager) ev.getEntity();
        final Location location = villager.getLocation().clone();
        TownBlock townBlock = TownyAPI.getInstance().getTownBlock(location);

        if (townBlock == null) {
            if (villager.getKiller() == null)
                Main.getInstance().getLogger().info(ChatColor.RED + "Villager killed in wilderness. Location: "
                        + LocationUtility.getLocationStringFormat(location));
            else
                Main.getInstance().getLogger().info(ChatColor.RED + "Villager killed in wilderness. Location: "
                        + LocationUtility.getLocationStringFormat(location) + ", Killer: " + villager.getKiller().getName());
        } else {
            Town town = townBlock.getTownOrNull();
            if (town == null)
                return;

            if (villager.getKiller() == null)
                Main.getInstance().getLogger().info(ChatColor.RED + "Villager killed in a town of " + town.getName() +". Location: "
                        + LocationUtility.getLocationStringFormat(location));
            else
                Main.getInstance().getLogger().info(ChatColor.RED + "Villager killed in a town of " + town.getName() +". Location: "
                        + LocationUtility.getLocationStringFormat(location) + ", Killer: " + villager.getKiller().getName());
        }
    }
}
