package apdevteam.APEmotes.tabCompletors;

import apdevteam.APEmotes.APEmotes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import java.util.Map;

public class PlayerTabComplete implements Listener{
    private Map<String,String > emoteMap;
    public PlayerTabComplete(APEmotes plugin){
        emoteMap = plugin.getEmoteMap();
    }
    @EventHandler
    public void onPlayerTabCompleteEvent(PlayerChatTabCompleteEvent event){

        for (Map.Entry<String,String> entry : emoteMap.entrySet()) {
            int emoteIndex = event.getChatMessage().lastIndexOf(entry.getKey().substring(0, 1));
            if(emoteIndex >= 0 && entry.getKey().startsWith(event.getChatMessage().substring(emoteIndex)))
                event.getTabCompletions().add(entry.getKey());
        }
    }
}
