package com.gmail.thelimeglass.Bungee;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.dommi2212.BungeeBridge.packets.PacketGetOnlineCountServer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprBungeeCountServer extends SimpleExpression<Integer> {

    //[skellett[cord]] [get] online bungee[[ ]cord] players on [server] %string%

    private Expression<String> server;

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        server = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[skellett[cord]] [get] online bungee[[ ]cord] players on [server] %string%";
    }

    @Override
    @Nullable
    protected Integer[] get(Event e) {
        PacketGetOnlineCountServer packet = new PacketGetOnlineCountServer(server.getSingle(e));
        Object obj = packet.send();
        Integer players = (Integer) obj;
        return new Integer[]{players};
    }
}