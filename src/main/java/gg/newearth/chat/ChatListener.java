package gg.newearth.chat;

import com.palmergames.bukkit.TownyChat.Chat;
import com.palmergames.bukkit.TownyChat.channels.Channel;
import gg.newearth.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private Channel globalChannel;

    public ChatListener() {
        Chat townyChat = (Chat) Bukkit.getPluginManager().getPlugin("TownyChat");

        if (townyChat == null) {
            Main.getInstance().getLogger().info(ChatColor.RED + "TownyChat plugin is missing!");
            return;
        }

        if (townyChat.getChannelsHandler() != null) {
            globalChannel = townyChat.getChannelsHandler().getChannel("general");

        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        if (!event.getMessage().startsWith("!"))
            return;

        event.getRecipients().addAll(Bukkit.getOnlinePlayers());

        event.setMessage(event.getMessage().replaceFirst("!", ""));
        globalChannel.chatProcess(event);
    }
}
