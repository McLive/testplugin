package eu.mclive.testplugin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin implements Listener {
	 MySQL mysql;
	 Connection c = null;
@Override
public void onEnable() {
	System.out.println("[TestPlugin] Plugin erfolgreich geladen!");
	getServer().getPluginManager().registerEvents(this, this);
	this.getCommand("heile").setExecutor(new CE_heile(this)); 
	this.getCommand("heile").setPermissionMessage(ChatColor.RED + "Dazu fehlt dir die Berechtigung!");
	this.getCommand("helptest").setExecutor(new CE_heile(this)); 
	this.getCommand("helptest").setPermissionMessage(ChatColor.RED + "Dazu fehlt dir die Berechtigung!");
	//TestPlugin plugin = this;
	MySQL MySQL = new MySQL(this, "s.freecraft.eu", "3306", "test", "test", "test");
	c = mysql.openConnection();
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

@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled=true)
public void onInventoryMoveItemEvent(InventoryMoveItemEvent event) {
    //Get destination container
    InventoryHolder destHolder = event.getDestination().getHolder();
    
    //since we only care about hoppers, then ignore everything else
    if (destHolder instanceof Hopper) {
    	System.out.println("Hello, my name is onInventoryMoveItemEvent!");
        //Get the location of the hopper in question.
        Block hopperBlock = ((Hopper) destHolder).getBlock();
        System.out.print("Inventory moved to: " + event.getDestination());
        System.out.print("Item: " + event.getItem());
        System.out.print("Source inventory: " + event.getSource());
        //Get the cache, if any, for the hopper location.
        //String cache = TestPlugin.knownHoppersCache_Get(hopperBlock);

        //get the itemStack that was moved and convert it to a String for comparison
        //String eventItemInformation = TestPlugin.GetItemInformationForInventory(event.getItem(), false);
        ItemStack eventItemInformation = event.getItem();            
        //debug if requested
                
    }
}















/*
@EventHandler
public void onPlayerMove(PlayerMoveEvent Event) {
	Player player = event.getPlayer();
	Location playerLoc = player.getLocation();
    player.sendMessage("Your X Coordinates : " + playerLoc.getX());
    player.sendMessage("Your Y Coordinates : " + playerLoc.getY());
    player.sendMessage("Your Z Coordinates : " + playerLoc.getZ());
}
*/

public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
	Player player = (Player) sender;
	if(commandLable.equalsIgnoreCase("haus")){
		Location loc = player.getLocation();
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		Statement statement;
		try {
			statement = mysql.openConnection().createStatement();
			statement.executeQuery("INSERT INTO users (`ign`, `x`, `y`, `z`) VALUES ('" + player + "', '" + x + "', '" + y + "', '" + z + "');");
			player.sendMessage(ChatColor.GREEN + "Your home has been set to this place.");
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	 if(commandLable.equalsIgnoreCase("haustp")){
		 Statement statement;
		 try {
			 statement = mysql.openConnection().createStatement();
			 ResultSet res = statement.executeQuery("SELECT * FROM users WHERE ign = '" + player + "';");
			 res.next();
			 if(res.getString("ign") == null) {
				 player.sendMessage(ChatColor.RED + "You have not set any home yet. Use /sethome");
			 } else {
				 double x = res.getInt("x");
				 double y = res.getInt("y");
				 double z = res.getInt("z");
				 Location loc = player.getLocation();
				 loc.setX(x);
				 loc.setY(y);
				 loc.setZ(z);
				 player.teleport(loc);
			 }
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return false;
	 }
	 return false;
	
}

}
