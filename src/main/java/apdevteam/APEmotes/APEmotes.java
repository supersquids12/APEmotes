package apdevteam.APEmotes;

import com.sun.xml.internal.fastinfoset.util.FixedEntryStringIntMap;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import java.util.HashMap;
//import org.bukkit.event.Listener;
import java.util.*;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
//import org.bukkit.event.server.onTabCompleteEvent;
import org.bukkit.event.server.TabCompleteEvent;




/**
 * Created by Foxtrot2400 on 7/6/17.
 */
public class APEmotes extends JavaPlugin implements Listener {
    //public List<String> EmoteWords;
    private HashMap<String, String> emoteMap = new HashMap<>();

    @Override
    public void onEnable() {
        System.out.println("Enabling emoticons ( ͡° ͜ʖ ͡°)");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        this.getConfig().options().copyDefaults(true);
        //  this.EmoteWords = (List<String>) this.getConfig().getList("EmoteWords");
        FileConfiguration config = this.getConfig();
        emoteMap.put(":example:", "test");
        //HashMap<String,String> emoteMap = new HashMap<>();
        for (HashMap.Entry<String, String> entry : emoteMap.entrySet()) {
            config.addDefault(entry.getKey(), entry.getValue());
        }
        //config.addDefaults(emoteMap);
        config.options().copyDefaults(true);
        saveConfig();


        //this.getConfig().set("")
        //emoteMap = (HashMap<String,String>)config.getValues(false);
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling Emoticons (╯°□°）╯︵ ┻━┻");
        for (Map.Entry<String, String> entry : emoteMap.entrySet()) {
            //  FileConfiguration config = this.getConfig();
            getConfig().set(entry.getKey(), entry.getValue());
        }
        this.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("emotes")) {
            if (args.length == 0) {
                player.sendMessage("Please type in a command!");
            } else if (args.length > 0) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (player.hasPermission("emote.edit")) {

                        //if(command.getName().equalsIgnoreCase("add"))
                        //{
                        getConfig().createSection(args[0].toLowerCase());
                        //ConfigurationSection cs = getConfig().getConfigurationSection(args[0].toLowerCase());
                        //cs.set("emote", )

                        // HashMap<String,String> emoteMap = new HashMap<>();

                        FileConfiguration config = this.getConfig();
                        //emoteMap = new HashMap(config.getValues(false));
                        emoteMap.put(args[1], args[2]);
                        for (Map.Entry<String, String> entry : emoteMap.entrySet()) {
                            config.addDefault(entry.getKey(), entry.getValue());
                        }
                        //config.addDefaults(emoteMap);
                        getLogger().info("arg 1: " + args[1] + " arg 2: " + args[2]);
                        player.sendMessage(" Added emote " + args[1] + " with text " + args[2]);
                        config.options().copyDefaults(true);

                        this.saveConfig();


                        //this.getConfig().set("")
                        //emoteMap = (HashMap<String,String>)config.getValues(false);
                    } else {
                        player.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Insufficient permissions.");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (player.hasPermission("emote.edit")) {
                        player.sendMessage("Still being implemented.");
                        //this.EmoteWords.remove(args[1].toLowerCase());
                        //this.getConfig().set("WordBlacklist", this.EmoteWords);
                        //this.saveConfig();
                        return true;
                    } else {
                        player.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Insufficient permissions.");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (player.hasPermission("emote.edit")) {
                        this.reloadConfig();
                        this.saveConfig();
                        this.getConfig().options().copyDefaults(true);
                        for (Map.Entry<String, String> entry : emoteMap.entrySet()) {
                            //  FileConfiguration config = this.getConfig();
                            getConfig().set(entry.getKey(), entry.getValue());
                        }
                        //this.EmoteWords = (List<String>) this.getConfig().getList("EmoteWords");
                        player.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.YELLOW + " Configuration reloaded.");
                        return true;
                    } else {
                        player.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Insufficient permissions.");
                        return true;
                    }
                }
            } else {
                player.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Invalid command.");
                return true;
            }

        }

        if (sender instanceof Player) {
            player = (Player) sender;
        } else sender.sendMessage("You can't use this command without being player");
        if (command.getName().equalsIgnoreCase("tableflip")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("error");
                return true;
            }
            Player cplayer = (Player) sender;
            cplayer.chat("(╯°□°）╯︵ ┻━┻");
        }
        if (command.getName().equalsIgnoreCase("lenny")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("error");
                return true;
            }
            Player cplayer = (Player) sender;
            cplayer.chat("( ͡° ͜ʖ ͡°)");
        }
        if (command.getName().equalsIgnoreCase("shrug")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("error");
                return true;
            }
            Player cplayer = (Player) sender;
            String shrug = "¯\\_(ツ)_/¯";
            cplayer.chat(shrug);
        }
        if (command.getName().equalsIgnoreCase("disapprove")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("error");
                return true;
            }
            Player cplayer = (Player) sender;
            cplayer.chat("ಠ_ಠ");
        }
        if (command.getName().equalsIgnoreCase("map") || command.getName().equalsIgnoreCase("apmap")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("error");
                return true;
            }
            sender.sendMessage("AP Map Link: http://bit.ly/2uznccX");
        }
        if (command.getName().equalsIgnoreCase("prices") || command.getName().equalsIgnoreCase("tradingprices")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("error");
                return true;
            }
            sender.sendMessage("AP Trading Prices Link: http://bit.ly/2hDWHyu");
        }
        return super.onCommand(sender, command, label, args);
    }

    @EventHandler
    public void onPlayerchat(AsyncPlayerChatEvent event) {
        for (String word : event.getMessage().split(" ")) {
            if (word.contains(":tableflip:")) {
                String message = event.getMessage().replaceAll(":tableflip:", "(╯°□°）╯︵ ┻━┻");
                event.setMessage(message);
            }
            if (word.contains(":shrug:")) {
                String message = event.getMessage().replaceAll(":shrug:", "¯\\_(ツ)_/¯");
                event.setMessage(message);
            }
            if (word.contains(":lenny:")) {
                String message = event.getMessage().replaceAll(":lenny:", "( ͡° ͜ʖ ͡°)");
                event.setMessage(message);
            }
            if (word.contains(":disapprove:")) {
                String message = event.getMessage().replaceAll(":disapprove:", "ಠ_ಠ");
                event.setMessage(message);
            }
            String tempMessage = event.getMessage();
            for (Map.Entry<String, String> entry : emoteMap.entrySet()) {
                tempMessage = tempMessage.replaceAll(entry.getKey(), entry.getValue());
            }
            event.setMessage(tempMessage);


        }
    }

    @EventHandler
    public void onTabCompleteEvent(TabCompleteEvent event){
        for (Map.Entry<String,String> entry : emoteMap.entrySet()) {
            int emoteIndex = event.getBuffer().lastIndexOf(entry.getKey().charAt(0));
            if(emoteIndex > 0 && entry.getKey().startsWith(event.getBuffer().substring(emoteIndex)))
                event.getCompletions().add(entry.getValue());
        }
    }
}







