package com.jmaug.zakabog.InvincibleTools;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class InvincibleToolsPlayerListener extends PlayerListener {
	public static InvincibleTools plugin;

	public InvincibleToolsPlayerListener(InvincibleTools instance) {
		plugin = instance;
	}
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack tool =  event.getItem();

		player.sendMessage("Interaction!");
		
		if(tool != null) {
			if(tool.getType() == Material.STONE_PICKAXE ||
			   tool.getType() == Material.STONE_AXE ||
			   tool.getType() == Material.STONE_HOE ||
			   tool.getType() == Material.STONE_SPADE) {
				player.sendMessage("Stone Tools!");
				tool.setDurability((short) 0);

			}
		}
	}
}
