package com.jmaug.zakabog.InvincibleTools;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;

public class InvincibleTools extends JavaPlugin{
//	private final InvincibleToolsBlockListener blockListener = new InvincibleToolsBlockListener(this);
	private final InvincibleToolsPlayerListener playerListener = new InvincibleToolsPlayerListener(this);
    public final HashMap<Player, ArrayList<Block>> firstPluginUsers = new HashMap<Player, ArrayList<Block>>();  

	@Override

	public void onDisable() {
		System.out.println("Invincible Stone Tools Disabled");
	}

	public void onEnable() {
        PluginDescriptionFile pdfFile = this.getDescription();
		PluginManager pm = getServer().getPluginManager();

		String pluginName		= pdfFile.getName();
		String pluginVersion	= pdfFile.getVersion();

//		pm.registerEvent(Event.Type.BLOCK_DAMAGE, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
        
        System.out.println(pluginName + " " + pluginVersion + " has been enabled" );
	}
}
