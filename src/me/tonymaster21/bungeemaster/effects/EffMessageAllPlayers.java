package me.tonymaster21.bungeemaster.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketConnectPlayer;
import me.dommi2212.BungeeBridge.packets.PacketMessageAllPlayers;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * Created by TonyMaster21 on 10/23/2017.
 */
public class EffMessageAllPlayers extends Effect {
    static {
        Skript.registerEffect(EffMessageAllPlayers.class, "message all (bm|bungeemaster) players %string%");
    }
    private Expression<String> message;
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        message = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "message all (bm|bungeemaster) players " + message.getSingle(paramEvent);
    }

    @Override
    protected void execute(Event e) {
        if (message != null) {
            PacketMessageAllPlayers packet = new PacketMessageAllPlayers(message.getSingle(e));
            Object obj = packet.send();
        }
    }
}
