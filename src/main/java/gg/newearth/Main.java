package gg.newearth;

import gg.newearth.abuse.AbuseDetectorListener;
import gg.newearth.chat.ChatListener;
import gg.newearth.combat.CombatHandler;
import gg.newearth.combat.bossbar.BossBarTask;
import gg.newearth.combat.listener.CombatListener;
import gg.newearth.command.BasicCommand;
import gg.newearth.command.SayCommand;
import gg.newearth.command.CombatTagCommand;
import gg.newearth.listener.basiclistener.*;
import gg.newearth.listener.townylistener.OccupiedTownTaxListener;
import gg.newearth.listener.townylistener.SiegeZoneListener;
import gg.newearth.listener.townylistener.TownyExploitListener;
import gg.newearth.listener.townylistener.VillagerDeathListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    private static Logger log = Bukkit.getLogger();

    public static final String PREFIX = ChatColor.of("#AEC6CF").toString() + ChatColor.BOLD + "New" + ChatColor.of("#D7F4D2").toString() + ChatColor.BOLD + "Earth" + ChatColor.WHITE + " : " + ChatColor.RESET + ChatColor.of("#D1C3B7").toString() + ChatColor.BOLD;

    public CombatHandler combatHandler;

    public CombatHandler getCombatHandler() {
        return combatHandler;
    }

    @Override
    public void onEnable() {
        instance = this;
        log.info(PREFIX + "Hi everyone!");

        this.getCommand("say").setExecutor(new SayCommand());
        this.getCommand("recipes").setExecutor(new BasicCommand());
        this.getCommand("rules").setExecutor(new BasicCommand());
        this.getCommand("store").setExecutor(new BasicCommand());
        this.getCommand("map").setExecutor(new BasicCommand());
        this.getCommand("combattag").setExecutor(new CombatTagCommand());
        getServer().getPluginManager().registerEvents(new DisableFortuneOnGoldOreListener(), this);
        getServer().getPluginManager().registerEvents(new TownyExploitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemConsumeListener(), this);
        getServer().getPluginManager().registerEvents(new ShieldListener(), this);
        getServer().getPluginManager().registerEvents(new HorseListener(), this);
        getServer().getPluginManager().registerEvents(new ArrowDamageListener(), this);
        getServer().getPluginManager().registerEvents(new VillagerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new AbuseDetectorListener(), this);
        getServer().getPluginManager().registerEvents(new MapArtDuplicateListener(), this);
        getServer().getPluginManager().registerEvents(new VillagerListener(), this);
        getServer().getPluginManager().registerEvents(new CropXPListener(), this);
        getServer().getPluginManager().registerEvents(new EXPBottleListener(), this);
        getServer().getPluginManager().registerEvents(new VillagerEggCraftListener(), this);
//        getServer().getPluginManager().registerEvents(new SunlightListener(), this);
        getServer().getPluginManager().registerEvents(new SiegeZoneListener(), this);
        getServer().getPluginManager().registerEvents(new CombatListener(), this);
        getServer().getPluginManager().registerEvents(new FurnaceBurnListener(), this);
        getServer().getPluginManager().registerEvents(new AnimalKillListener(), this);
        getServer().getPluginManager().registerEvents(new OccupiedTownTaxListener(), this);

        new BukkitRunnable() {
            public void run() {
                getServer().getPluginManager().registerEvents(new ChatListener(), Main.getInstance()); // delay so TownyChat registers first
            }
        }.runTaskLater(Main.getInstance(), 20 * 10);

		runTasks();

        Bukkit.broadcastMessage(PREFIX + "Plugin enabled.");
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage(PREFIX + "Plugin disabled.");
        for (Player p: this.getServer().getOnlinePlayers()) {
            if (CombatHandler.isTagged(p)) {
                CombatHandler.removeTag(p);
            }
        }
    }

    private void runTasks() {
        new BossBarTask().runTaskTimer(this, 10, 10);
    }
}
