package com.gmail.thelimeglass.Tablist;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprAllTabItems extends SimpleExpression<String> {

    //[(the|all)] [of] [the] tab[list] items

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[(the|all)] [of] [the] tab[list] items";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return TablistManager.getAll();
    }
}