package apdevteam.APEmotes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;





/**
 * Created by Foxtrot2400 on 7/6/17.
 */
public class APEmotes extends JavaPlugin implements Listener {
    //public List<String> EmoteWords;
    private HashMap<String, String> emoteMap = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("Enabling emoticons ( ͡° ͜ʖ ͡°)");
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
        getLogger().info("Disabling Emoticons (╯°□°）╯︵ ┻━┻");
        for (Map.Entry<String, String> entry : emoteMap.entrySet()) {
            //  FileConfiguration config = this.getConfig();
            getConfig().set(entry.getKey(), entry.getValue());
        }
        this.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("emotes")) {
            if (args.length == 0) {
                sender.sendMessage("Please type in a command!");
            } else if (args.length > 0) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (sender.hasPermission("emote.edit")) {

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
                        sender.sendMessage(" Added emote " + args[1] + " with text " + args[2]);
                        config.options().copyDefaults(true);

                        this.saveConfig();


                        //this.getConfig().set("")
                        //emoteMap = (HashMap<String,String>)config.getValues(false);
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Insufficient permissions.");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (sender.hasPermission("emote.edit")) {
                        sender.sendMessage("Still being implemented.");
                        //this.EmoteWords.remove(args[1].toLowerCase());
                        //this.getConfig().set("WordBlacklist", this.EmoteWords);
                        //this.saveConfig();
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Insufficient permissions.");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("emote.edit")) {
                        this.reloadConfig();
                        this.saveConfig();
                        this.getConfig().options().copyDefaults(true);
                        for (Map.Entry<String, String> entry : emoteMap.entrySet()) {
                            //  FileConfiguration config = this.getConfig();
                            getConfig().set(entry.getKey(), entry.getValue());
                        }
                        //this.EmoteWords = (List<String>) this.getConfig().getList("EmoteWords");
                        sender.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.YELLOW + " Configuration reloaded.");
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Insufficient permissions.");
                        return true;
                    }
                }
            } else {
                sender.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Invalid command.");
                return true;
            }

        }

        if (command.getName().equalsIgnoreCase("map") || command.getName().equalsIgnoreCase("apmap")) {
            sender.sendMessage("AP Map Link: http://bit.ly/2uznccX");
            return true;
        }
        if (command.getName().equalsIgnoreCase("prices") || command.getName().equalsIgnoreCase("tradingprices")) {
            sender.sendMessage("AP Trading Prices Link: http://bit.ly/2hDWHyu");
            return true;
        }

        if (!(sender instanceof Player)){
            sender.sendMessage("You can't use this command without being player");
            return true;
        }
        Player player = (Player)sender;
        if (command.getName().equalsIgnoreCase("tableflip")) {
            player.chat("(╯°□°）╯︵ ┻━┻");
            return true;
        }
        if (command.getName().equalsIgnoreCase("lenny")) {
            player.chat("( ͡° ͜ʖ ͡°)");
            return true;
        }
        if (command.getName().equalsIgnoreCase("shrug")) {
            player.chat("¯\\_(ツ)_/¯");
            return true;
        }
        if (command.getName().equalsIgnoreCase("disapprove")) {
            player.chat("ಠ_ಠ");
            return true;
        }

        return false;
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
        ArrayList<String> tabCompletions = new ArrayList<>(event.getCompletions());
        for (Map.Entry<String,String> entry : emoteMap.entrySet()) {
            int emoteIndex = event.getBuffer().lastIndexOf(entry.getKey().substring(0, 1));
            if(emoteIndex >= 0 && entry.getKey().startsWith(event.getBuffer().substring(emoteIndex)))
                tabCompletions.add(entry.getValue());
        }
        event.setCompletions(tabCompletions);
    }
}







