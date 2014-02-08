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
	System.out.println("Test");
	System.out.println("Test2! :D");
	System.out.println("Test3");
	System.out.println("Test4");
	System.out.println("Test5");
	System.out.println("Test6");
}
	
}
