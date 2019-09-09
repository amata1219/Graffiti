package graffiti;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sender {

	public final CommandSender sender;

	public Sender(CommandSender sender){
		this.sender = sender;
	}

	public boolean isNotPlayer(){
		return !(sender instanceof Player);
	}

	public Player asPlayer(){
		return (Player) sender;
	}

}
