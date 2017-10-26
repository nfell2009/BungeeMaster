package me.tonymaster21.bungeemaster;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
// import java.lime.lang;

/**
 * Created by TonyMaster21 on 10/21/2017.
 */
public class BungeeMaster extends JavaPlugin {
    private static SkriptAddon addonInstance;
    @Override
    public void onEnable(){
        try {
            getLogger().info("For help with BungeeMaster, please visit https://bungeemaster.tonymaster21.me");
            
            if (Bukkit.getPluginManager().getPlugin("Skript") == null) {
                disableBungeeMaster("BungeeMaster requires Skript but it couldn't be found. Disabling BungeeMaster...");
            }
            if (Bukkit.getPluginManager().getPlugin("BungeeBridgeC") == null) {
                disableBungeeMaster("BungeeMaster requires BungeeBridgeC but it couldn't be found. Disabling BungeeMaster...");
            }
            if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
                addonInstance = Skript.registerAddon(this);
                addonInstance.loadClasses("me.tonymaster21.bungeemaster", "effects", "expressions");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void disableBungeeMaster(String reason) {
        getLogger().severe(reason);
        Bukkit.getServer().getPluginManager().disablePlugin(this);
    }
    public static SkriptAddon getAddonInstance(){
        return addonInstance;
    }

    public void onDisable(){
        getLogger().info("Thank you for using BungeeMaster. Have a nice day!");
    }

}
