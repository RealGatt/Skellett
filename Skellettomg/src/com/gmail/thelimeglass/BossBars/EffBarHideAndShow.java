package com.gmail.thelimeglass.BossBars;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.boss.BossBar;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffBarHideAndShow extends Effect {

    //[skellett] (1�hide|2�show) [boss[ ]]bar %bossbar%

    Boolean toggle = true;
    private Expression<BossBar> bar;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        bar = (Expression<BossBar>) e[0];
        if (parser.mark == 2) {
            toggle = false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett] (1�hide|2�show) [boss[ ]]bar %bossbar%";
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void execute(Event e) {
        if (toggle) {
            bar.getSingle(e).hide();
        } else {
            bar.getSingle(e).show();
        }
    }
}