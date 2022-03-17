package gg.newearth.command;

import gg.newearth.Main;
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
                sender.sendMessage("§bhttps://newearth.gg/recipes");
            }
            case "store" -> {
                sender.sendMessage(Main.PREFIX + "§eYou can buy ranks at");
                sender.sendMessage("§bhttps://newearth.tebex.io/category/2011099");
            }
            case "rules", "rule" -> {
                sender.sendMessage(Main.PREFIX + "§eYou can find our rules at");
                sender.sendMessage("§bhttps://newearth.gg/rules");
            }
            case "map" -> {
                sender.sendMessage(Main.PREFIX + "§eExplore map at");
                sender.sendMessage("§bhttps://newearth.gg/map");
            }
        }

        return true; // TODO: Add custom /help, /commands, /vote
    }
}