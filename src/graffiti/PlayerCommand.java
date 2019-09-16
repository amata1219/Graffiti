package graffiti;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface PlayerCommand extends Command {

	@Override
	default void onCommand(CommandSender sender, ArgList args){
		if(!(sender instanceof Player)){
			sender.sendMessage("ゲーム内から実行して下さい。");
			return;
		}

		onCommand((Player) sender, args);
	}

	void onCommand(Player player, ArgList args);
}
