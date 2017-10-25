package me.tonymaster21.bungeemaster.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketGetPlayersGlobal;
import org.bukkit.event.Event;

import java.util.List;
import java.util.UUID;

/**
 * Created by TonyMaster21 on 10/22/2017.
 */
public class ExprGetPlayersGlobal extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprGetPlayersGlobal.class, String.class, ExpressionType.SIMPLE, "all [online ](bm|bungeemaster) players");
    }
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle(){
        return false;
    }

    @Override
    @Nullable
    protected String[] get (Event e) {
        PacketGetPlayersGlobal packet = new PacketGetPlayersGlobal();
        Object obj = packet.send();
        List<UUID> uuids = (List<UUID>) obj;

        if (uuids != null) {
            return uuids.stream().map(Object::toString).toArray(String[]::new);
        }
        return null;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "all [online ](bm|bungeemaster) players";
    }
}
