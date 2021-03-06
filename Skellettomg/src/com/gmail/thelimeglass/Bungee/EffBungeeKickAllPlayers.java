package com.gmail.thelimeglass.Bungee;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Skellett;
import me.dommi2212.BungeeBridge.packets.PacketKickAllPlayers;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffBungeeKickAllPlayers extends Effect {

    //[skellett[cord]] kick [all] [bungee[ ][cord]] players [from bungee[ ][cord]] [(by reason of|because [of]|on account of|due to)] %string%

    private Expression<String> reason;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        reason = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett[cord]] kick [all] [bungee[ ][cord]] players [from bungee[ ][cord]] [(by reason of|because [of]|on account of|due to)] %string%";
    }

    @Override
    protected void execute(Event e) {
        String msg = reason.getSingle(e);
        if (msg == null) {
            msg = "Kicked from the bungeecord";
        }
        PacketKickAllPlayers packet = new PacketKickAllPlayers(Skellett.cc(msg));
        packet.send();
    }
}
