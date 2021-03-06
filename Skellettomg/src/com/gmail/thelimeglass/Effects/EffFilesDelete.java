package com.gmail.thelimeglass.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.SkellettAPI.SkellettFiles;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffFilesDelete extends Effect {

    //[skellett] d[elete][ ][f][ile] %string%

    private Expression<String> file;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        file = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett] d[elete][ ][f][ile] %string%";
    }

    @Override
    protected void execute(Event e) {
        SkellettFiles.deleteFile(file.getSingle(e));
    }
}
