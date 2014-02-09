package eu.mclive.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {

@Override
public void onEnable() {
	System.out.println("[TestPlugin] Plugin erfolgreich geladen!");
	this.getCommand("heile").setPermissionMessage(ChatColor.RED + "Dazu fehlt dir die Berechtigung!");
}

@Override
public void onDisable() {
	System.out.println("[TestPlugin] Plugin erfolgreich deaktiviert!");
}

@Override
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
		if (args.length>0) {
			return false;
		}
	}
	
	return false;
	
}

}
