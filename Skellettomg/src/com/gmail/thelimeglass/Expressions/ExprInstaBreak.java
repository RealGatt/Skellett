package com.gmail.thelimeglass.Expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockDamageEvent;

import javax.annotation.Nullable;

public class ExprInstaBreak extends SimpleExpression<Boolean> {

    //[event] inst(ant|a) break [state]

    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    public boolean isSingle() {
        return true;
    }

    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if (!ScriptLoader.isCurrentEvent(BlockDamageEvent.class)) {
            Skript.error("You can not use Insta Break expression in any event but on block damage event!");
            return false;
        }
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "Insta break";
    }

    @Nullable
    protected Boolean[] get(Event e) {
        return new Boolean[]{((BlockDamageEvent) e).getInstaBreak()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == ChangeMode.SET)
            ((BlockDamageEvent) e).setInstaBreak(((Boolean) delta[0]));
        if (mode == ChangeMode.RESET)
            ((BlockDamageEvent) e).setInstaBreak(((BlockDamageEvent) e).getInstaBreak());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.RESET)
            return CollectionUtils.array(Boolean.class);
        return null;
    }
}