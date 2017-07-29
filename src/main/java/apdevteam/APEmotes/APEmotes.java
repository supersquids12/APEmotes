package apdevteam.APEmotes;

import apdevteam.APEmotes.tabCompletors.PlayerTabComplete;
import apdevteam.APEmotes.tabCompletors.TabComplete;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
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
        getServer().getPluginManager().registerEvents(this, this);
        if(getServer().getVersion().contains("1.9"))
            getServer().getPluginManager().registerEvents(new PlayerTabComplete(this), this);
        else
            getServer().getPluginManager().registerEvents(new TabComplete(this), this);

        this.getConfig().options().copyDefaults(true);
        //  this.EmoteWords = (List<String>) this.getConfig().getList("EmoteWords");
        FileConfiguration config = this.getConfig();
        emoteMap.put(":tableflip:", "(╯°□°）╯︵ ┻━┻");
        emoteMap.put(":shrug:", "¯\\_(ツ)_/¯");
        emoteMap.put(":lenny:", "( ͡° ͜ʖ ͡°)");
        emoteMap.put(":disapprove:", "ಠ_ಠ");
        //HashMap<String,String> emoteMap = new HashMap<>();
        for (HashMap.Entry<String, String> entry : emoteMap.entrySet()) {
            config.addDefault(entry.getKey(), entry.getValue());
        }
        //config.addDefaults(emoteMap);
        config.options().copyDefaults(true);
        saveConfig();
        loadCommands();

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
                return true;
            }
            if (args[0].equalsIgnoreCase("add")) {
                if (!sender.hasPermission("emote.edit")) {
                    sender.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Insufficient permissions.");
                    return true;
                }
                getConfig().createSection(args[0].toLowerCase());
//                FileConfiguration config = this.getConfig();
                emoteMap.put(args[1], args[2]);
//                for (Map.Entry<String, String> entry : emoteMap.entrySet()) {
//                    config.addDefault(entry.getKey(), entry.getValue());
//                }
                getLogger().info("arg 1: " + args[1] + " arg 2: " + args[2]);
                sender.sendMessage(" Added emote " + args[1] + " with text " + args[2]);
//                config.options().copyDefaults(true);

//                this.saveConfig();
                return true;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                if (!sender.hasPermission("emote.edit")) {
                    sender.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Insufficient permissions.");
                    return true;
                }
                sender.sendMessage("Still being implemented.");
                //this.EmoteWords.remove(args[1].toLowerCase());
                //this.getConfig().set("WordBlacklist", this.EmoteWords);
                //this.saveConfig();
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("emote.edit")) {
                    sender.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Insufficient permissions.");
                    return true;
                }
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
            }
            sender.sendMessage(ChatColor.GREEN + "[Emotes]" + ChatColor.RED + " Invalid command.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("map") || command.getName().equalsIgnoreCase("apmap")) {
            sender.sendMessage("AP Map Link: http://bit.ly/2uznccX");
            return true;
        }
        if (command.getName().equalsIgnoreCase("prices") || command.getName().equalsIgnoreCase("tradingprices")) {
            sender.sendMessage("AP Trading Prices Link: http://bit.ly/2hDWHyu");
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String tempMessage = event.getMessage();
        for (Map.Entry<String, String> entry : emoteMap.entrySet()) {
            tempMessage = tempMessage.replaceAll(entry.getKey(), entry.getValue());
        }
        event.setMessage(tempMessage);
    }

    public HashMap<String, String> getEmoteMap(){
        return emoteMap;
    }

    private void loadCommands(){
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            for (String emote : emoteMap.keySet())
                commandMap.register("apemotes", new EmoteCommand(emote));
        } catch (IllegalAccessException | SecurityException | NoSuchFieldException | IllegalArgumentException e) {
            e.printStackTrace();

        }
    }

    private class EmoteCommand extends Command implements PluginIdentifiableCommand {

        private EmoteCommand(String name) {
            super(name);
            this.description = "Sends the " + name + " emote in chat";
            this.usageMessage = "/" + name;
            this.setPermission("StaffMode." + name);
            this.setAliases(new ArrayList<String>());
        }

        @Override
        public boolean execute(CommandSender sender, String s, String[] strings) {
            if(!(sender instanceof Player)){
                sender.sendMessage(ChatColor.RED + "You need to be a player to use that command.");
                return true;
            }

            if (!sender.hasPermission(this.getPermission())) {
                sender.sendMessage(ChatColor.RED + "You don't have permission.");
                return true;
            }
            ((Player) sender).chat(emoteMap.get(s.toLowerCase()));

            return true;
        }

        @Override
        public Plugin getPlugin() {
            return APEmotes.this;
        }
    }

}







