package gg.newearth.listener.townylistener;

import gg.newearth.combat.CombatHandler;
import gg.newearth.utility.LocationUtil;
import com.gmail.goosius.siegewar.SiegeController;
import com.gmail.goosius.siegewar.objects.Siege;
import com.gmail.goosius.siegewar.utils.SiegeWarDistanceUtil;
import com.palmergames.bukkit.towny.event.actions.TownyItemuseEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class SiegeZoneListener implements Listener {

    @EventHandler
    public void tntCartExplode(EntityExplodeEvent event){
        Location location = event.getLocation();
        if(event.getEntityType().equals(EntityType.MINECART_TNT) && SiegeWarDistanceUtil.isLocationInActiveSiegeZone(location)){
            Siege siege = getActiveSiegeInLocation(location);
            assert siege != null;
            if(location.getY() < siege.getFlagLocation().getY()){
                event.blockList().clear();
             }

        }
    }


    @EventHandler
    public void townyItemuseEvent (TownyItemuseEvent event) {
        if(SiegeWarDistanceUtil.isLocationInActiveSiegeZone(event.getLocation()) && event.getMaterial().equals(Material.ENDER_PEARL)){
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void playerTeleportEvent(PlayerTeleportEvent event){
        if((SiegeWarDistanceUtil.isLocationInActiveSiegeZone(event.getPlayer().getLocation()) && event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL))){
            if(!LocationUtil.isSafe(event.getTo())){
                event.setCancelled(true);
            }
        }
    }

    //Prevent people from using clickable blocks
    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player player = event.getPlayer();
            if(SiegeWarDistanceUtil.isLocationInActiveSiegeZone(player.getLocation()) && CombatHandler.isTagged(player) && !player.isSneaking()){
                // Player is in SiegeZone, In combat and not Sneaking
                Material type = event.getClickedBlock().getType();
                if(isClickableBlock(type)){
                    // Is a clickable block.
                    event.setCancelled(true);
                }
                    // Is not a clickable block.
            }
        }
    }

    public static Siege getActiveSiegeInLocation(Location location) {
        for (Siege siege : SiegeController.getSieges()) {
            if (siege.getStatus().isActive() && SiegeWarDistanceUtil.isInSiegeZone(location, siege)) {
                return siege;
            }
        }
        return null;
    }

    public static boolean isClickableBlock(Material type) {
        if (type == null || !type.isBlock()) {
            return false;
        }
        return switch (type) {
            case DISPENSER, NOTE_BLOCK, CRAFTING_TABLE, FURNACE, BLAST_FURNACE, JUKEBOX, CAKE, ENCHANTING_TABLE, BREWING_STAND, BEACON, ANVIL, HOPPER, DROPPER, FLETCHING_TABLE, LECTERN, CARTOGRAPHY_TABLE, CHEST, TRAPPED_CHEST -> true;
            default -> false;
        };
    }
}
