package com.gmail.thelimeglass.Tablist;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.inventivetalent.tabapi.TabAPI;

import javax.annotation.Nullable;

public class EffPlayerRemoveList extends Effect {

    //(remove|reset|delete) [the] tab[list] [items] (of|for) %players%

    private Expression<Player> players;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        players = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "(remove|reset|delete) [the] tab[list] [items] (of|for) %players%";
    }

    @Override
    protected void execute(Event e) {
        for (Player p : players.getAll(e)) {
            TabAPI.removeTab(p);
        }
    }
}
