package gg.newearth.listener.basiclistener;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.event.NewDayEvent;
import gg.newearth.Main;
import gg.newearth.utility.LocationUtility;
import gg.newearth.utility.TimeUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.ParseException;
import java.util.*;

public class VillagerListener implements Listener {

    private static final int VILLAGER_LIFESPAN = 15;

    @EventHandler(ignoreCancelled = true)
    public void onVillagerCured(EntityTransformEvent ev) {
        if (ev.getTransformReason() == EntityTransformEvent.TransformReason.CURED) {
            ev.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onRightClickVillager(PlayerInteractEntityEvent ev) throws ParseException {
        Player player = ev.getPlayer();
        if (ev.getRightClicked().getType() == EntityType.ZOMBIE_VILLAGER) {
            if (player.getInventory().getItemInMainHand().getType() == Material.GOLDEN_APPLE) {
                player.sendMessage(ChatColor.RED + "Zombie villager curing is disabled on NewEarth. You can craft villager spawn eggs with gold blocks. Use "
                        + ChatColor.AQUA + " /recipes" + ChatColor.RED + " to view our other custom crafting recipes.");
                ev.setCancelled(true);
                return;
            }
        }

        if (ev.getRightClicked().getType() == EntityType.VILLAGER) {
            Villager villager = (Villager) ev.getRightClicked();
            List<MerchantRecipe> recipes = villager.getRecipes();

            if (villager.getProfession() == Villager.Profession.CLERIC) {
                for (MerchantRecipe recipe : recipes) {
                    if (recipe.getResult().getType() == Material.EXPERIENCE_BOTTLE) {
                        recipe.setIngredients(new ArrayList<>() {{
                            add(new ItemStack(Material.EMERALD, 1));
                        }});
                        recipe.setExperienceReward(false);
                    }
                }
            }

            String spawnDateString = villager.getPersistentDataContainer().get(new NamespacedKey(Main.getInstance(), "spawn_date"), PersistentDataType.STRING);
            if (spawnDateString == null) {
                player.sendMessage(ChatColor.RED + "Internal error occurred. Please submit a support ticket in our Support Discord.");
                ev.setCancelled(true);
                return;
            }

            Date spawnDate = TimeUtility.parseDateString(spawnDateString);
            Date currentDate = TimeUtility.getCurrentDate();
            String currentDateString = TimeUtility.getDateStringFormat(currentDate);
            if (spawnDateString.equals(currentDateString))
                return;

            long dateDifference = TimeUtility.getDateDifference(spawnDate, currentDate);
            player.sendMessage(ChatColor.GOLD + "[Villager] " + ChatColor.AQUA + "Villager age: " + dateDifference +
                    " days. Villagers despawn after " + VILLAGER_LIFESPAN + " days.");
            if (dateDifference > VILLAGER_LIFESPAN) {
                ev.setCancelled(true);
                player.sendMessage(ChatColor.RED + "This villager exceeded its lifespan of " + VILLAGER_LIFESPAN + " days and has been removed.");
                villager.remove();
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onVillagerSpawn(CreatureSpawnEvent ev) {
        if (ev.getEntityType() != EntityType.VILLAGER)
            return;

        if (ev.getSpawnReason() == CreatureSpawnEvent.SpawnReason.BREEDING) {
            ev.setCancelled(true);
            return;
        }

        Villager villager = (Villager) ev.getEntity();
        villager.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "spawn_date"),
                PersistentDataType.STRING, TimeUtility.getCurrentDateStringFormat());
    }

    @EventHandler(ignoreCancelled = true)
    public void onVillagerBreed(EntityBreedEvent ev) {
        if (ev.getEntity().getType() == EntityType.VILLAGER)
            ev.setCancelled(true);
    }
}
