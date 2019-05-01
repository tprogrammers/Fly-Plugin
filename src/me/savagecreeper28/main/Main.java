package me.savagecreeper28.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    // Prefix for the plugin.
    String prefix = "§6[§eFly§6] §7";

    // onEnable() = method to start the plugin

    @Override
    public void onEnable() {

        // Print a line into console.
        Bukkit.getLogger().info(prefix + "§aPlugin enabled.");

        // Load the command.
        this.getCommand("fly").setExecutor(this);

        // Load the onConfig() method.
        onConfig();

    }

    // onDisable() = method to stop the plugin.

    @Override
    public void onDisable() {

        // Print a line into console.
        Bukkit.getLogger().info(prefix + "§cPlugin disabled.");

    }

    // onConfig() = method to load the plugin.

    public void onConfig(){

        // Create strings (strings can be found in README.md).
        getConfig().set("fly_enabled", "§aYou can now fly.");
        getConfig().set("fly_disabled", "§cYou can not more fly.");
        getConfig().set("fly_no_permissions", "§cYou have no permissions for executing this command.");
        getConfig().set("fly_false_usage", "§cUse /fly");

        // Write everything in the config.yml
        getConfig().options().copyDefaults(true);

        // Save the config.
        saveConfig();

    }

    // onCommand() = method to write the command.

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if the player types "/fly"
        if(command.getName().equalsIgnoreCase("fly")){

            // Define the player.
            Player p = (Player)sender;

            // Check if the player is a player. If not, return a message.
            if(sender instanceof Player){

                // Check the args. If they are longer than 0, return a message
                if(args.length == 0){

                    // Check, if the player has permissions. If not, return a message.
                    if(p.hasPermission("fly.enable")){

                        // Check if the player has already fly enabled.
                        if(p.getAllowFlight()){

                            // Turn the fly mode off and send a message.
                            p.setAllowFlight(false);
                            p.sendMessage(prefix + getConfig().getString("fly_disabled"));

                        }else{

                            // If not activated, set the flying on an return a message.
                            p.setAllowFlight(true);
                            p.sendMessage(prefix + getConfig().getString("fly_enabled"));

                        }

                    }else{

                        // Return a message...
                        p.sendMessage(prefix + getConfig().getString("fly_no_permissions"));

                    }

                }else{

                    // Return the message.
                    p.sendMessage(prefix + getConfig().getString("fly_false_usage"));

                }

            }else{

                // Return the message..
                sender.sendMessage(prefix + "§cYou are not a player. Please execute this command ingame.");

            }

        }

        return true;
    }
}
