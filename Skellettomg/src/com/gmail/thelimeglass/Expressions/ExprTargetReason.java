package com.gmail.thelimeglass.Expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityTargetEvent;

import javax.annotation.Nullable;

public class ExprTargetReason extends SimpleExpression<EntityTargetEvent.TargetReason> {

    //target reason

    public Class<? extends EntityTargetEvent.TargetReason> getReturnType() {
        return EntityTargetEvent.TargetReason.class;
    }

    public boolean isSingle() {
        return true;
    }

    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if (!ScriptLoader.isCurrentEvent(EntityTargetEvent.class)) {
            Skript.error("You can not use TargetReason expression in any event but on on target!");
            return false;
        }
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "Target reason";
    }

    @Nullable
    protected EntityTargetEvent.TargetReason[] get(Event e) {
        return new EntityTargetEvent.TargetReason[]{((EntityTargetEvent) e).getReason()};
    }
}