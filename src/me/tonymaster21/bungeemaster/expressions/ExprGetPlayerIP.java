package me.tonymaster21.bungeemaster.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketGetPlayerIP;
import org.bukkit.event.Event;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * Created by TonyMaster21 on 10/22/2017.
 */
public class ExprGetPlayerIP extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprGetPlayerIP.class, String.class, ExpressionType.SIMPLE, "[the ](bm|bungeemaster) ip of [the ][player ]%string%");
    }
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
        PacketGetPlayerIP packet = new PacketGetPlayerIP(theuuid);
        Object obj = packet.send();
        InetSocketAddress playerip = (InetSocketAddress) obj;
        if (playerip != null) {
            return new String[] {playerip.toString()};
        }
        return null;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[the ](bm|bungeemaster) ip of [the ][player ] " + uuid.getSingle(paramEvent);
    }
}
