package com.gmail.thelimeglass.BossBars;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffBarRemovePlayer extends Effect {

    //[skellett] remove %player% from [the] [boss[ ]]bar %bossbar%

    private Expression<Player> player;
    private Expression<BossBar> bar;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<Player>) e[0];
        bar = (Expression<BossBar>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett] remove %player% from [the] [boss[ ]]bar %bossbar%";
    }

    @Override
    protected void execute(Event e) {
        bar.getSingle(e).removePlayer(player.getSingle(e));
    }
}