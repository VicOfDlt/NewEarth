package gg.newearth.command;

import gg.newearth.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BasicCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        switch (cmd.getLabel().toLowerCase()) {
            case "recipes" -> {
                sender.sendMessage(Main.PREFIX + "§eYou can find our custom recipes at");
                sender.sendMessage(Component.text("§bhttps://newearth.gg/recipes").clickEvent(ClickEvent.openUrl("https://newearth.gg/recipes")));
            }
            case "store" -> {
                sender.sendMessage(Main.PREFIX + "§eYou can buy ranks at");
                sender.sendMessage(Component.text("§bhttps://newearth.tebex.io/category/2011099").clickEvent(ClickEvent.openUrl("https://newearth.tebex.io/category/2011099")));
            }
            case "rules" -> {
                sender.sendMessage(Main.PREFIX + "§eYou can find our rules at");
                sender.sendMessage(Component.text("§bhttps://newearth.gg/rules").clickEvent(ClickEvent.openUrl("https://newearth.gg/rules")));
            }
            case "map" -> {
                sender.sendMessage(Main.PREFIX + "§eExplore map at");
                sender.sendMessage(Component.text("§bhttps://map.newearth.gg").clickEvent(ClickEvent.openUrl("https://map.newearth.gg")));
            }
            case "website" -> {
                sender.sendMessage(Main.PREFIX + "§eOur Website!");
                sender.sendMessage(Component.text("§bhttps://newearth.gg").clickEvent(ClickEvent.openUrl("https://newearth.gg")));
            }
        }
        return true; // TODO: Add custom /help, /commands
    }
}