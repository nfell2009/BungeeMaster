package me.tonymaster21.bungeemaster;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import me.tonymaster21.bungeemaster.Effects.*;
import me.tonymaster21.bungeemaster.Expressions.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
// import java.lime.lang;

/**
 * Created by TonyMaster21 on 10/21/2017.
 */
public class BungeeMaster extends JavaPlugin {
    private static SkriptAddon addonInstance;
    @Override
    public void onEnable(){
        getLogger().info("For help with BungeeMaster, please visit https://bungeemaster.tonymaster21.me");
        if (Bukkit.getPluginManager().getPlugin("Skript") == null) {
            getLogger().info("Skript is not installed on your server. This plugin will not work!");
        }
        if (Bukkit.getPluginManager().getPlugin("BungeeBridgeC") == null) {
            getLogger().info("BungeeBridgeClient is not installed on your server. This plugin will not work!");
        }
        if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
            Skript.registerAddon(this);
            addonInstance = this;
            getAddonInstance.loadClasses("me.tonymaster21.bungeemaster", "effects", "expressions");
        }
    }
    public static SkriptAddon getAddonInstance(){
    if(addonInstance == null) {
        addonInstance = Skript.registerAddon(getInstance());
    }
    return addonInstance;
}

    public void onDisable(){
        getLogger().info("Thank you for using BungeeMaster. Have a nice day!");
    }

}
