package com.applugins.apemotesplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
//import minecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
//import org.bukkit.event.Listener;




//

/**
 * Created by j on 7/6/17.
 */
public class APEmotes extends JavaPlugin implements Listener{
    @EventHandler
    public void onPlayerchat(AsyncPlayerChatEvent event) {
        for (String word : event.getMessage().split(" ")) {
            if(word.contains(":tableflip:")){
                //event.setCancelled(true);
                String message = event.getMessage().replaceAll(":tableflip:", "(╯°□°）╯︵ ┻━┻");
                event.setMessage(message);
            }
            if(word.contains(":shrug:")){
                //event.setCancelled(true);
                String message = event.getMessage().replaceAll(":shrug:", "¯\\_(ツ)_/¯");
                event.setMessage(message);
            }
            if(word.contains(":lenny:")){
                //event.setCancelled(true);
                String message = event.getMessage().replaceAll(":lenny:", "( ͡° ͜ʖ ͡°)");
                event.setMessage(message);
            }
            if(word.contains(":disapprove:")){
                //event.setCancelled(true);
                String message = event.getMessage().replaceAll(":disapprove:", "ಠ_ಠ");
                event.setMessage(message);
            }
        }

       // Player player = event.getPlayer();

       // String message = event.getMessage().replaceAll(":emote:", "word to replace with");
       // event.setMessage(message);
    }
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //return super.onCommand(sender, command, label, args);
        Player player = null;
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
        // Player cplayer = (Player) sender;
        return super.onCommand(sender, command, label, args);
    }
    //public PlayerChatListener(Main instance) {
    //    plugin = instance;
    //}

}
//}




