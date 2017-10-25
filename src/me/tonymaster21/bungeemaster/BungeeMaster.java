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
            // Effects
            Skript.registerEffect(EffChat.class, "send (bm|bungeemaster) chat[ message] %string% from %string%");
            Skript.registerEffect(EffConnectPlayer.class, "(bm|bungeemaster) connect %string% to server %string%");
            Skript.registerEffect(EffKickAllPlayers.class, "kick all (bm|bungeemaster) players due to %string%");
            Skript.registerEffect(EffKickPlayer.class, "kick (bm|bungeemaster) player with uuid %string% due to %string%");
            Skript.registerEffect(EffMessageAllPlayers.class, "message all (bm|bungeemaster) players %string%");
            Skript.registerEffect(EffStopProxy.class, "stop [the ](bm|bungeemaster) proxy");
            // Expressions
            Skript.registerExpression(ExprGetMOTDServer.class, String.class, ExpressionType.SIMPLE, "[the ](bm|bungeemaster) motd of [the ]server %string%");
            Skript.registerExpression(ExprGetOnlineCountGlobal.class, Integer.class, ExpressionType.SIMPLE, "[the ](bm|bungeemaster) global player count");
            Skript.registerExpression(ExprGetOnlineCountServer.class, Integer.class, ExpressionType.SIMPLE, "[the ](bm|bungeemaster) player count of [the ][server ]%string%");
            Skript.registerExpression(ExprGetPlayerIP.class, String.class, ExpressionType.SIMPLE, "[the ](bm|bungeemaster) ip of [the ][player ]%string%");
            Skript.registerExpression(ExprGetPlayerName.class, String.class, ExpressionType.SIMPLE, "[the ](bm|bungeemaster) player with [the ]uuid [of ]%string%");
            Skript.registerExpression(ExprGetPlayersGlobal.class, String.class, ExpressionType.SIMPLE, "all [online ](bm|bungeemaster) players");
            Skript.registerExpression(ExprGetPlayerUUID.class, String.class, ExpressionType.SIMPLE, "[the ](bm|bungeemaster) uuid of %player%");

        }
    }

    public void onDisable(){
        getLogger().info("Thank you for using BungeeMaster. Have a nice day!");
    }

}
