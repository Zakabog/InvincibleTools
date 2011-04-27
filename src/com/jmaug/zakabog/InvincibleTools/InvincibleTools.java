package com.jmaug.zakabog.InvincibleTools;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;

public class InvincibleTools extends JavaPlugin{
    public static Hashtable<String, Integer> invincibleToolsHash = new Hashtable<String, Integer>();
    public static String pluginTitle 	= "";

    private static String materialRegex = "wood|stone|iron|gold|diamond";
    private static String toolRegex		= "hoe|spade|axe|pickaxe|sword";
	private final InvincibleToolsPlayerListener playerListener = new InvincibleToolsPlayerListener(this);


	private void set_invincible_tools (String[] invincibleToolsArray) {
		for (String name : invincibleToolsArray) {
			name = name.replace(" ", "");
			if(name.matches("(" + materialRegex + ")_tools")) {
				this.add_invincible_tools(name.split("_"));
			} else if (name.matches("(" + materialRegex + ")_(" + toolRegex + ")")) {
				invincibleToolsHash.put(name.toUpperCase(), 1);
			} else if (name.matches("(" + materialRegex + ")")) {
				this.add_invincible_tools(name);
				invincibleToolsHash.put(name.toUpperCase() + "_SWORD", 1);
			}
		}
	}

	private void add_invincible_tools (String material) {
		invincibleToolsHash.put(material.toUpperCase() + "_HOE", 1);
		invincibleToolsHash.put(material.toUpperCase() + "_SPADE", 1);
		invincibleToolsHash.put(material.toUpperCase() + "_AXE", 1);
		invincibleToolsHash.put(material.toUpperCase() + "_PICKAXE", 1);
	}

	private void add_invincible_tools (String[] materials) {
		for (String material : materials) {
			if (material.matches(materialRegex)) {
				add_invincible_tools(material);
			}
		}
	}
    
    @Override

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();

		String pluginName		= pdfFile.getName();
		String pluginVersion	= pdfFile.getVersion();

		pluginTitle = pluginName + " " + pluginVersion;
		System.out.println(pluginTitle + " - Plugin has been disabled");
	}

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		PluginDescriptionFile pdfFile = this.getDescription();

		String pluginName		= pdfFile.getName();
		String pluginVersion	= pdfFile.getVersion();

		pluginTitle = pluginName + " " + pluginVersion;

		try {
		    // Check that folder exists, and if not create it
		    File folder = new File("plugins/" + pluginName);
		    if(!folder.exists())
		    	folder.mkdir();

		    // Check that the config file exists, and if not create it
		    File file = new File(folder,"config.yml");
		    if (!file.exists()) {
		    	file.createNewFile();

		    	// This sets stone_tools to be invincible by default
		    	getConfiguration().setProperty("invincible", "stone_tools");
		    	getConfiguration().save();
		    }
		} catch (IOException e) {
			System.out.println(pluginTitle + " - Exception thrown when attempting to check config.yml");
			return;
		}

		String invincibleTools = (String) getConfiguration().getProperty("invincible");

		set_invincible_tools(invincibleTools.split(",")); 

		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);

		System.out.println(pluginTitle + " - Plugin has been enabled");
		System.out.println(pluginTitle + " - The following items are now unbreakable :");
		if (invincibleToolsHash.isEmpty()) {
			System.out.println("    NONE");
		} else {
			for (String key : invincibleToolsHash.keySet()) {
				System.out.println("    " + key);
			}
		}
	}
}
