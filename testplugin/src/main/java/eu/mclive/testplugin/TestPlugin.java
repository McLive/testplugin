package eu.mclive.testplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {

@Override
public void onEnable() {
	System.out.println("Plugin erfolgreich geladen!");
}
@Override
public void onDisable() {
	System.out.println("Plugin erfolgreich deaktiviert!");
}
	
}
