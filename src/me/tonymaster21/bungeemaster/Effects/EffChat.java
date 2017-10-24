package me.tonymaster21.bungeemaster.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketChat;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * Created by TonyMaster21 on 10/21/2017.
 */
public class EffChat extends Effect {

    private Expression<String> message;
    private Expression<String> uuid;
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        message = (Expression<String>) e[0];
        uuid = (Expression<String>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "send (bm|bungeemaster) chat[ message] %string% from %string%";
    }

    @Override
    protected void execute(Event e) {
        if (message != null || uuid != null) {
            UUID theuuid = null;
            theuuid = UUID.fromString(uuid.getSingle(e));
            PacketChat packet = new PacketChat(theuuid, message.getSingle(e));
            Object obj = packet.send();
        }
    }
}
