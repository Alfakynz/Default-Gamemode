package com.alfakynz.defaultgamemode;


import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultGamemode implements ModInitializer {
	public static final String MOD_ID = "default-gamemode";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Default Gamemode Mod Initialized");
	}
}