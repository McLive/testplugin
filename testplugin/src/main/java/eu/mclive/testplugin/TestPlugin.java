package eu.mclive.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {

@Override
public void onEnable() {
	System.out.println("[TestPlugin] Plugin erfolgreich geladen!");
	this.getCommand("heile").setExecutor(new CE_heile(this)); 
	this.getCommand("heile").setPermissionMessage(ChatColor.RED + "Dazu fehlt dir die Berechtigung!");
}

@Override
public void onDisable() {
	System.out.println("[TestPlugin] Plugin erfolgreich deaktiviert!");
}



}
