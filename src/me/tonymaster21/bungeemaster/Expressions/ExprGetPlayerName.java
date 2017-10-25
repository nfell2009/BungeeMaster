package me.tonymaster21.bungeemaster.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketGetPlayerName;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * Created by TonyMaster21 on 10/22/2017.
 */
public class ExprGetPlayerName extends SimpleExpression<String> {
    private Expression<String> uuid;
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        uuid = (Expression<String>) e[0];
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle(){
        return true;
    }

    @Override
    @Nullable
    protected String[] get (Event e) {
        UUID theuuid = null;
        theuuid = UUID.fromString(uuid.getSingle(e));
        PacketGetPlayerName packet = new PacketGetPlayerName(theuuid);
        Object obj = packet.send();
        String playername = (String) obj;
        if (playername != null) {
            return new String[] {playername.toString()};
        }
        return null;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[the ](bm|bungeemaster) player with [the ]uuid [of ]%string%";
    }
}
