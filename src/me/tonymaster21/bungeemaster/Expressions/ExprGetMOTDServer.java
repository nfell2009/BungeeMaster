package me.tonymaster21.bungeemaster.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketGetMOTDServer;
import org.bukkit.event.Event;

/**
 * Created by TonyMaster21 on 10/22/2017.
 */
public class ExprGetMOTDServer extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprGetMOTDServer.class, String.class, ExpressionType.SIMPLE, "[the ](bm|bungeemaster) motd of [the ]server %string%");
    }
    private Expression<String> nameOfServer;
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        nameOfServer = (Expression<String>) e[0];
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
        if (nameOfServer != null) {
            PacketGetMOTDServer packet = new PacketGetMOTDServer(nameOfServer.getSingle(e));
            Object obj = packet.send();
            String servermotd = (String) obj;
            if (servermotd != null) {
                return new String[] {servermotd};
            }
        }
        return null;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[the ](bm|bungeemaster) motd of [the ]server " + nameOfServer.getSingle(paramEvent);
    }

}
