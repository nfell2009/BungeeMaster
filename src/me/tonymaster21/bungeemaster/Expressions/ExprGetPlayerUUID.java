package me.tonymaster21.bungeemaster.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketGetPlayerUUID;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Created by TonyMaster21 on 10/22/2017.
 */
public class ExprGetPlayerUUID extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprGetPlayerUUID.class, String.class, ExpressionType.SIMPLE, "[the ](bm|bungeemaster) uuid of %player%");
    }
    private Expression<Player> player;
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        player = (Expression<Player>) e[0];
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
        PacketGetPlayerUUID packet = new PacketGetPlayerUUID(player.toString());
        Object obj = packet.send();
        String uuid = (String) obj;
        if (uuid != null) {
            return new String[] {uuid};
        }
        return null;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[the ](bm|bungeemaster) uuid of " + player.getSingle(paramEvent);
    }
}
