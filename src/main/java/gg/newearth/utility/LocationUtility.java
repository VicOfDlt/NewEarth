package gg.newearth.utility;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationUtility {

    public static String getLocationStringFormat(Location location) {
        return "X:" + location.getBlockX() + " Y:" + location.getBlockY() + " Z:" + location.getBlockZ();
    }

    public static boolean isOceanBiome(Player player) {
        return player.getWorld().getBiome(player.getLocation().clone()).toString().toLowerCase().endsWith("ocean");
    }
}
