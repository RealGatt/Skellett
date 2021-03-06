package com.gmail.thelimeglass.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.SkellettAPI.SkellettFiles;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprFilesGetBoolean extends SimpleExpression<Boolean> {

    //[skellett] [get] boolean [of] [node] %string% from [file] %string%

    private Expression<String> tag;
    private Expression<String> file;

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        tag = (Expression<String>) e[0];
        file = (Expression<String>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[skellett] [get] boolean [of] [node] %string% from [file] %string%";
    }

    @Override
    @Nullable
    protected Boolean[] get(Event e) {
        return new Boolean[]{SkellettFiles.getBoolean(file.getSingle(e), tag.getSingle(e))};
    }
}