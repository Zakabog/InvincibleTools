package com.jmaug.zakabog.InvincibleTools;

import org.bukkit.event.block.BlockListener;

public class InvincibleToolsBlockListener extends BlockListener {
	public static InvincibleTools plugin;
	public InvincibleToolsBlockListener(InvincibleTools instance) {
		plugin = instance;
	}
}