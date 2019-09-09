package graffiti;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public interface Command extends CommandExecutor {

	@Override
	default boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args){
		onCommand(sender, args);
		return true;
	}

	void onCommand(CommandSender sender, String[] args);

}
