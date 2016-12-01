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
import com.gmail.thelimeglass.Events.EvtAsyncDamage;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprAsyncDamage extends SimpleExpression<Number> {

    //(smashhit|async) damage [(received|taken)]

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if (!ScriptLoader.isCurrentEvent(EvtAsyncDamage.class)) {
            Skript.error("You can not use Async damage expression in any event but the async damage event!");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "(smashhit|async) damage [(received|taken)]";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{((EvtAsyncDamage) e).getDamage()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == ChangeMode.SET) {
            ((EvtAsyncDamage) e).setDamage((Number) delta[0]);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == ChangeMode.SET) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }
}