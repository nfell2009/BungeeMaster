package me.tonymaster21.bungeemaster.Effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketStopProxy;
import org.bukkit.event.Event;

/**
 * Created by TonyMaster21 on 10/23/2017.
 */
public class EffStopProxy extends Effect {

    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "stop [the ](bm|bungeemaster) proxy";
    }

    @Override
    protected void execute(Event e) {
        PacketStopProxy packet = new PacketStopProxy();
        Object obj = packet.send();
        Skript.error("[BungeeMaster] It is recommended that you stop the server when ending the proxy to prevent errors.");
    }
}
