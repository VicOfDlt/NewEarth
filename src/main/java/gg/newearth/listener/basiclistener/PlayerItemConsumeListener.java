package gg.newearth.listener.basiclistener;

import gg.newearth.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerItemConsumeListener implements Listener {

    private static final short GAPPLE_COOLDOWN_SECONDS = (short) Main.getInstance().getConfig().getInt("GAPPLE_COOLDOWN_SECONDS");

    @EventHandler(ignoreCancelled = true)
    public void onGoldenAppleConsume(PlayerItemConsumeEvent ev) {
        if (ev.getItem().getType() != Material.GOLDEN_APPLE)
            return;

        Player player = ev.getPlayer();
        player.setCooldown(Material.GOLDEN_APPLE, GAPPLE_COOLDOWN_SECONDS * 20);
    }
}
