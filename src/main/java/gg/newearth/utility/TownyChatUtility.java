package gg.newearth.utility;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import gg.newearth.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TownyChatUtility {

    public static void switchToNationChat(Player player) {
        Resident townyPlayer = TownyAPI.getInstance().getResident(player);
        if (townyPlayer == null)
            return;

        Town town = TownyAPI.getInstance().getResidentTownOrNull(townyPlayer);
        if (town == null)
            return;

        new BukkitRunnable() {
            public void run() {
                if (TownyAPI.getInstance().getTownNationOrNull(town) == null)
                    player.performCommand("tc");
                else
                    player.performCommand("nc");
            }
        }.runTaskLater(Main.getInstance(), 20);
    }

    public static void switchToModChat(Player player) {
        if (player.isOp()) {
            new BukkitRunnable() {
                public void run() {
                    player.performCommand("mod");
                }
            }.runTaskLater(Main.getInstance(), 20);
        }
    }
}
