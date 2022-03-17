package gg.newearth.listener.basiclistener;

import gg.newearth.utility.TownyChatUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent ev) {
        TownyChatUtility.switchToNationChat(ev.getPlayer());
        TownyChatUtility.switchToModChat(ev.getPlayer());
    }
}
