package com.gmail.thelimeglass.Scoreboards;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;

import javax.annotation.Nullable;

public class EffRegisterObjective extends Effect {

    //register [new] (score[ ][board]|[skellett[ ]]board) objective %string% with [criteria] %string% [[(in|from)] %-scoreboard%]
    //register [new] objective %string% with [criteria] %string% [(in|from)] (score[ ][board]|[skellett[ ]]board) [%-scoreboard%]

    private Expression<String> obj;
    private Expression<String> criteria;
    private Expression<Scoreboard> scoreboard;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        obj = (Expression<String>) e[0];
        criteria = (Expression<String>) e[1];
        scoreboard = (Expression<Scoreboard>) e[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "register [new] (score[ ][board]|[skellett[ ]]board) objective %string% with [criteria] %string% [[(in|from)] %-scoreboard%]";
    }

    @Override
    protected void execute(Event e) {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        if (scoreboard != null) {
            board = scoreboard.getSingle(e);
        }
        if (board.getObjective(obj.getSingle(e)) != null) {
            return;
        }
        board.registerNewObjective(obj.getSingle(e), criteria.getSingle(e));
    }
}