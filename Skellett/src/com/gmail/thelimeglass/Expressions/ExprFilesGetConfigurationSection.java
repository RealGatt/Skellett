package com.gmail.thelimeglass.Expressions;

import javax.annotation.Nullable;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Event;

import com.gmail.thelimeglass.SkellettAPI.SkellettFiles;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprFilesGetConfigurationSection extends SimpleExpression<ConfigurationSection>{
	
	//[skellett] [get] [config][uration] section [of] [node] %string% from [file] %string%
	
	private Expression<String> tag;
	private Expression<String> file;
	@Override
	public Class<? extends ConfigurationSection> getReturnType() {
		return ConfigurationSection.class;
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
		return "[skellett] [get] [config][uration] section [of] [node] %string% from [file] %string%";
	}
	@Override
	@Nullable
	protected ConfigurationSection[] get(Event e) {
		return new ConfigurationSection[]{SkellettFiles.getSection(file.getSingle(e), tag.getSingle(e))};
	}
}