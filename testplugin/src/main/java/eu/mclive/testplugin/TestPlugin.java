package eu.mclive.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin implements Listener {

@Override
public void onEnable() {
	System.out.println("[TestPlugin] Plugin erfolgreich geladen!");
	getServer().getPluginManager().registerEvents(this, this);
	this.getCommand("heile").setExecutor(new CE_heile(this)); 
	this.getCommand("heile").setPermissionMessage(ChatColor.RED + "Dazu fehlt dir die Berechtigung!");
}

@Override
public void onDisable() {
	System.out.println("[TestPlugin] Plugin erfolgreich deaktiviert!");
}

@EventHandler
public void onAttack(EntityDamageByEntityEvent event) {
	if(event.getDamager() instanceof Player) {
		if (event.getEntity() instanceof Player) {
			Player attacker = null;
			Player defender = null;
			attacker = (Player)event.getDamager();
			defender = (Player)event.getEntity();
		
			attacker.sendMessage(ChatColor.GREEN + "Du hast " + ChatColor.RED + defender.getName()  + ChatColor.GREEN + " angegriffen.");
			defender.sendMessage(ChatColor.GREEN + "Du wurdest von " + ChatColor.RED + attacker.getName() + ChatColor.GREEN + " angegriffen.");
		}
	}
}


}
