package com.gmail.thelimeglass.Nametags;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffResetNametag extends Effect {

    //[skellett] reset [the] [name][ ]tag [with] [id] %string%

    private Expression<String> nametag;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        nametag = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett] reset [the] [name][ ]tag [with] [id] %string%";
    }

    @Override
    protected void execute(Event e) {
        SkellettNametags.resetNametag(nametag.getSingle(e));
    }
}
