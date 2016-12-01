package com.gmail.thelimeglass.Scoreboards;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;

import javax.annotation.Nullable;

public class EffTeamRemoveEntry extends Effect {

    //[skellett] (score[ ][board]|board) remove [the] entry [(from|of)] %string% from [the] [team] %team%

    private Expression<String> entry;
    private Expression<Team> team;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        entry = (Expression<String>) e[0];
        team = (Expression<Team>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett] (score[ ][board]|board) remove [the] entry [(from|of)] %string% from [the] [team] %team%";
    }

    @Override
    protected void execute(Event e) {
        team.getSingle(e).removeEntry(entry.getSingle(e));
    }
}