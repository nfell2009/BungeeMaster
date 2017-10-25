package me.tonymaster21.bungeemaster.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketGetOnlineCountServer;
import org.bukkit.event.Event;

/**
 * Created by TonyMaster21 on 10/22/2017.
 */
public class ExprGetOnlineCountServer extends SimpleExpression<Integer> {
    static {
        Skript.registerExpression(ExprGetOnlineCountServer.class, Integer.class, ExpressionType.SIMPLE, "[the ](bm|bungeemaster) player count of [the ][server ]%string%");
    }
    private Expression<String> nameOfServer;
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        nameOfServer = (Expression<String>) e[0];
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle(){
        return true;
    }

    @Override
    @Nullable
    protected Integer[] get (Event e) {
        if (nameOfServer != null) {
            PacketGetOnlineCountServer packet = new PacketGetOnlineCountServer(nameOfServer.getSingle(e));
            Object obj = packet.send();
            Integer onlinecount = (Integer) obj;
            if (onlinecount != null) {
                return new Integer[] {onlinecount};
            }
        }
        return null;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[the ](bm|bungeemaster) player count of [the ][server ] " + nameOfServer.getSingle(paramEvent);
    }
}
