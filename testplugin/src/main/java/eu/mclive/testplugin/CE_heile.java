package eu.mclive.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CE_heile implements CommandExecutor{
	
	private TestPlugin plugin;
	public CE_heile(TestPlugin testplugin) {
		plugin = testplugin;
	}
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("heile")) {
			if (args.length == 0) {
				if (!(sender instanceof Player)) {
					System.out.println("Dieser Befehl ist nur für Spieler.");
					return true;
				}
				Player player = (Player) sender;
				player.setHealth(20.0);
				player.sendMessage(ChatColor.GREEN + "Du wurdest geheilt.");
				return true;
			}
			if (args.length == 1) {
				Player ziel = plugin.getServer().getPlayer(args[0]);
				Player player = (Player) sender;
				if (ziel == null) {
					sender.sendMessage(ChatColor.RED + "Dieser Spieler wurde nicht gefunden!");
					return true;
				}
				ziel.setHealth(20.0);
				ziel.sendMessage(ChatColor.GREEN + "Du wurdest von " + ChatColor.RED + player.getName() + ChatColor.GREEN + " geheilt!");
				player.sendMessage(ChatColor.GREEN + "Du hast " + ChatColor.RED + ziel.getName() + ChatColor.GREEN + " geheilt!");
				return true;
				
			}
			if (args.length>1) {
				return false;
			}
		}
		
		return false;
		
	}
	
}
