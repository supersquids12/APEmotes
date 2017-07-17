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
        for (Map.Entry<String,String> entry : emoteMap.entrySet()) {
            int emoteIndex = event.getBuffer().lastIndexOf(entry.getKey().substring(0, 1));
            if(emoteIndex >= 0 && entry.getKey().startsWith(event.getBuffer().substring(emoteIndex)))
                tabCompletions.add(entry.getKey());
        }
        event.setCompletions(tabCompletions);
    }
}

