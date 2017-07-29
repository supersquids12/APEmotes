package apdevteam.APEmotes.tabCompletors;

import apdevteam.APEmotes.APEmotes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.ArrayList;

public class TabComplete implements Listener{
    private final APEmotes plugin;
    public TabComplete(APEmotes plugin){
        this.plugin=plugin;
    }
    @EventHandler
    public void onTabCompleteEvent(TabCompleteEvent event){
        ArrayList<String> tabCompletions = new ArrayList<>(event.getCompletions());
        for (String emoteKey : plugin.getEmoteMap().keySet()) {
            if(!event.getSender().hasPermission("StaffMode." + emoteKey))
                continue;
            int emoteIndex = event.getBuffer().lastIndexOf(":");
            if(emoteIndex >= 0 && (":" + emoteKey).startsWith(event.getBuffer().substring(emoteIndex)))
                tabCompletions.add(":" + emoteKey + ":");
        }
        event.setCompletions(tabCompletions);
    }
}

