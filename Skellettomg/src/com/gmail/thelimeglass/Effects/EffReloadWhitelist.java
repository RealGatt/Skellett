package com.gmail.thelimeglass.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffReloadWhitelist extends Effect {

    //reload [the] white[ ]list

    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "reload [the] white[ ]list";
    }

    @Override
    protected void execute(Event e) {
        Bukkit.reloadWhitelist();
    }
}