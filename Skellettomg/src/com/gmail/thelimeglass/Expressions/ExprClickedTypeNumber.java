package com.gmail.thelimeglass.Expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.annotation.Nullable;

public class ExprClickedTypeNumber extends SimpleExpression<Number> {

    //[skellett] click[ed] type num[ber]

    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    public boolean isSingle() {
        return true;
    }

    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if (!ScriptLoader.isCurrentEvent(InventoryClickEvent.class)) {
            Skript.error("You can not use clicked type number expression in any event but inventory click!");
            return false;
        }
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "clicked type number";
    }

    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{((InventoryClickEvent) e).getHotbarButton()};
    }
}