package gg.newearth.listener.townylistener;

import com.gmail.goosius.siegewar.TownOccupationController;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.event.NewDayEvent;
import com.palmergames.bukkit.towny.event.TownUpkeepCalculationEvent;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyWorld;
import com.palmergames.bukkit.towny.object.Translatable;
import com.palmergames.bukkit.towny.object.economy.AccountObserver;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OccupiedTownTaxListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onTaxCollect(NewDayEvent ev) {
        TownyWorld world = TownyAPI.getInstance().getTownyWorld("world");
        if (world == null)
            return;

        for (Town town: world.getTowns().values()) {
            if (TownOccupationController.isTownOccupied(town)) {
                Nation occupyingNation = TownOccupationController.getTownOccupier(town);
                double tax = Math.max(TownySettings.getTownUpkeepCost(town), 20);
                if (tax < 100) { // to prevent it errors and taxes 100000 G
                    String townMsg = ChatColor.AQUA + "Your town paid " + tax
                            + "G of occupation tax to " + occupyingNation.getName();
                    String nationMsg = ChatColor.AQUA + "Your nation collected " + tax
                            + "G of occupation tax from " + town.getName();

                    town.getAccount().withdraw(tax, townMsg);
                    TownyMessaging.sendPrefixedTownMessage(town, townMsg);

                    occupyingNation.getAccount().deposit(tax, nationMsg);
                    TownyMessaging.sendPrefixedNationMessage(occupyingNation, nationMsg);
                }
            }
        }
    }
}
