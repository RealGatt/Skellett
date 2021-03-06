package com.gmail.thelimeglass.Scoreboards;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;

import javax.annotation.Nullable;

public class EffUnregisterTeam extends Effect {

    //[skellett] unregister [the] (score[ ][board]|board) team %team%

    private Expression<Team> team;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        team = (Expression<Team>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett] unregister [the] (score[ ][board]|board) team %team%";
    }

    @Override
    protected void execute(Event e) {
        team.getSingle(e).unregister();
    }
}