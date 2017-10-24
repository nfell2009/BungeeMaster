package me.tonymaster21.bungeemaster.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketGetOnlineCountGlobal;
import org.bukkit.event.Event;

/**
 * Created by TonyMaster21 on 10/22/2017.
 */
public class ExprGetOnlineCountGlobal extends SimpleExpression {
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
        return true;
    }

    @Override
    @Nullable
    protected String[] get (Event e) {
        PacketGetOnlineCountGlobal packet = new PacketGetOnlineCountGlobal();
        Object obj = packet.send();
        Integer onlinecount = (Integer) obj;
        if (onlinecount != null) {
            return new String[] {onlinecount.toString()};
        }
        return null;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[the ](bm|bungeemaster) global player count";
    }
}
