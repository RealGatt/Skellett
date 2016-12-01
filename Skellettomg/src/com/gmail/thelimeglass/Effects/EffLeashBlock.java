package com.gmail.thelimeglass.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LeashHitch;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffLeashBlock extends Effect {

    //(leash|lead) %livingentities% to %block%

    private Expression<LivingEntity> entities;
    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        entities = (Expression<LivingEntity>) e[0];
        block = (Expression<Block>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "(leash|lead) %livingentities% to %block%";
    }

    @Override
    protected void execute(Event e) {
        Entity hitch = null;
        if (block.getSingle(e) == null) return;
        if (block.getSingle(e).getType() != Material.FENCE) {
            Material type = block.getSingle(e).getType();
            block.getSingle(e).setType(Material.FENCE);
            hitch = block.getSingle(e).getLocation().getWorld().spawn(block.getSingle(e).getLocation(), LeashHitch.class);
            block.getSingle(e).setType(type);
        } else {
            hitch = block.getSingle(e).getLocation().getWorld().spawn(block.getSingle(e).getLocation(), LeashHitch.class);
        }
        if (hitch != null && entities.getAll(e) != null) {
            for (LivingEntity entity : entities.getAll(e)) {
                entity.setLeashHolder(hitch);
            }
        }
    }
}