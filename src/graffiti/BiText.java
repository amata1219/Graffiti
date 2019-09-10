package graffiti;

import java.util.function.Consumer;

import org.bukkit.entity.Player;

public class BiText {

	public static BiText of(String japanise, String english){
		return new BiText(Text.of(japanise), Text.of(english));
	}

	private final Text japanise, english;

	public BiText(Text japanise, Text english){
		this.japanise = japanise;
		this.english = english;
	}

	public BiText format(Object... objects){
		japanise.format(objects);
		english.format(objects);
		return this;
	}

	public void accept(Player player, Consumer<String> action){
		if(player.getLocale().equals("ja_jp")) japanise.accept(action);
		else english.accept(action);
	}

}
