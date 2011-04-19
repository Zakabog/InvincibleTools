package com.jmaug.zakabog.InvincibleTools;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class InvincibleToolsPlayerListener extends PlayerListener {
	public static InvincibleTools plugin;

	public InvincibleToolsPlayerListener(InvincibleTools instance) {
		plugin = instance;
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		ItemStack tool =  event.getItem();

		if(tool != null) {
			if(InvincibleTools.invincibleToolsHash.containsKey(tool.getType().toString())) {
				tool.setDurability((short) 0);
			}
		}
	}
}
