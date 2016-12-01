package com.gmail.thelimeglass.BossBars;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprBarPlayers extends SimpleExpression<Player> {

    //[skellett] [(the|all)] [of] [the] player[[']s] [(in|of)] [the] [boss[ ]]bar %bossbar%
    //[skellett] %bossbar%'s player[[']s]

    private Expression<BossBar> bar;

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        bar = (Expression<BossBar>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[skellett] [(the|all)] [of] [the] player[[']s] [(in|of)] [the] [boss[ ]]bar %bossbar%";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return bar.getSingle(e).getPlayers().toArray(new Player[bar.getSingle(e).getPlayers().size()]);
    }
}