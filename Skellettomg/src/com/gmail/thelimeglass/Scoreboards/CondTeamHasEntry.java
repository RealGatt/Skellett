package com.gmail.thelimeglass.Scoreboards;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;

import javax.annotation.Nullable;

public class CondTeamHasEntry extends Condition {

    //[skellett] (score[ ][board]|board) (1�(ha(s|ve)|contain[s])|2�(do[es](n't| not) have| do[es](n't| not) contain)) [the] [entry] %string% [(in|within)] the [team] %team%

    Boolean boo = true;
    private Expression<String> entry;
    private Expression<Team> team;

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        entry = (Expression<String>) e[0];
        team = (Expression<Team>) e[1];
        if (parser.mark == 2) {
            boo = false;
        }
        return true;
    }

    public String toString(@Nullable Event e, boolean arg1) {
        return "[skellett] (score[ ][board]|board) (1�(ha(s|ve)|contain[s])|2�(do[es](n't| not) have| do[es](n't| not) contain)) [the] [entry] %string% [(in|within)] the [team] %team%";
    }

    public boolean check(Event e) {
        if (team.getSingle(e).hasEntry(entry.getSingle(e))) {
            if (boo == true) {
                return true;
            } else {
                return false;
            }
        } else {
            if (boo == false) {
                return true;
            } else {
                return false;
            }
        }
    }
}