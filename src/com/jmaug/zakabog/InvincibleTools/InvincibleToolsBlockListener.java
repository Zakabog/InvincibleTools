package com.jmaug.zakabog.InvincibleTools;

import org.bukkit.Material;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class InvincibleToolsBlockListener extends BlockListener {
	public static InvincibleTools plugin;
	public InvincibleToolsBlockListener(InvincibleTools instance) {
		plugin = instance;
	}

	public void onBlockDamage(BlockDamageEvent event) {
		ItemStack tool =  event.getItemInHand();

		if(tool != null) {
			if(tool.getType() == Material.STONE_PICKAXE ||
			   tool.getType() == Material.STONE_AXE ||
			   tool.getType() == Material.STONE_HOE ||
			   tool.getType() == Material.STONE_SPADE) {
				tool.setDurability((short) 0);
			}
		}
	}
}