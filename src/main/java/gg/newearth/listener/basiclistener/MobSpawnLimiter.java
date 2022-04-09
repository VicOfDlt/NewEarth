package gg.newearth.listener.basiclistener;

import gg.newearth.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.World;
import org.bukkit.GameRule;

public class MobSpawnLimiter extends BukkitRunnable {

    @Override
    public void run() {

        if (Bukkit.getTPS()[1] < 18) {
            for (World world: Bukkit.getWorlds()) {
                if (world.getGameRuleValue(GameRule.DO_MOB_SPAWNING) == true){
                    world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                    Bukkit.broadcastMessage(Main.PREFIX + "Mob spawning has " + ChatColor.RED + "Disabled.");
                }
            }
        } else if (Bukkit.getTPS()[1] > 18) {
            for (World world: Bukkit.getWorlds()) {
                if (world.getGameRuleValue(GameRule.DO_MOB_SPAWNING) == false){
                    world.setGameRule(GameRule.DO_MOB_SPAWNING, true);
                    Bukkit.broadcastMessage(Main.PREFIX + "Mob spawning has " + ChatColor.GREEN + "Enabled.");
                }
            }
        }
    }
}