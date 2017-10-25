package me.tonymaster21.bungeemaster.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketConnectPlayer;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * Created by TonyMaster21 on 10/23/2017.
 */
public class EffKickPlayer extends Effect {
    static {
        Skript.registerEffect(EffKickPlayer.class, "kick (bm|bungeemaster) player with uuid %string% due to %string%");
    }
    private Expression<String> uuid;
    private Expression<String> message;
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        uuid = (Expression<String>) e[0];
        message = (Expression<String>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "kick (bm|bungeemaster) player with uuid " + uuid.getSingle(paramEvent) + " due to " + message.getSingle(paramEvent);
    }

    @Override
    protected void execute(Event e) {
        if (uuid != null || message != null) {
            UUID theuuid = null;
            theuuid = UUID.fromString(uuid.getSingle(e));
            PacketConnectPlayer packet = new PacketConnectPlayer(theuuid, message.getSingle(e));
            Object obj = packet.send();
        }
    }
}
