package apdevteam.APEmotes.tabCompletors;

import apdevteam.APEmotes.APEmotes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import java.util.Map;

public class PlayerTabComplete implements Listener{
    private final APEmotes plugin;
    public PlayerTabComplete(APEmotes plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerTabCompleteEvent(PlayerChatTabCompleteEvent event){

        for (String emoteKey : plugin.getEmoteMap().keySet()) {
            int emoteIndex = event.getChatMessage().lastIndexOf(":");
            if(emoteIndex >= 0 && (":"+emoteKey).startsWith(event.getChatMessage().substring(emoteIndex)))
                event.getTabCompletions().add(":" + emoteKey + ":");
        }
    }
}
