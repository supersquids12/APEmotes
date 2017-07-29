package apdevteam.APEmotes.tabCompletors;

import apdevteam.APEmotes.APEmotes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.ArrayList;
import java.util.Map;

public class TabComplete implements Listener{
    private Map<String, String > emoteMap;
    public TabComplete(APEmotes plugin){
        emoteMap = plugin.getEmoteMap();
    }
    @EventHandler
    public void onTabCompleteEvent(TabCompleteEvent event){
        ArrayList<String> tabCompletions = new ArrayList<>(event.getCompletions());
        for (String emoteKey : emoteMap.keySet()) {
            int emoteIndex = event.getBuffer().lastIndexOf(":");
            if(emoteIndex >= 0 && (":" + emoteKey).startsWith(event.getBuffer().substring(emoteIndex)))
                tabCompletions.add(":" + emoteKey + ":");
        }
        event.setCompletions(tabCompletions);
    }
}

