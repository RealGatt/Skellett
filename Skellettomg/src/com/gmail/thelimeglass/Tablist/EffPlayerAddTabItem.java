package com.gmail.thelimeglass.Tablist;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.inventivetalent.tabapi.TabAPI;

import javax.annotation.Nullable;

public class EffPlayerAddTabItem extends Effect {

    //add tab[list] item [[with] id] %-string% to %players%

    private Expression<String> ID;
    private Expression<Player> players;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        ID = (Expression<String>) e[0];
        players = (Expression<Player>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "add tab[list] item [[with] id] %-string% to %players%";
    }

    @Override
    protected void execute(Event e) {
        if (!TablistManager.containsTabItem(ID.getSingle(e))) {
            return;
        }
        for (Player p : players.getAll(e)) {
            TabAPI.addItem(p, TablistManager.get(ID.getSingle(e)));
        }
    }
}
