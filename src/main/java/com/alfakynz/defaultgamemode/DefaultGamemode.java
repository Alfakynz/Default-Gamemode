package com.alfakynz.defaultgamemode;


import com.alfakynz.defaultgamemode.config.Config;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultGamemode implements ModInitializer {
	public static final String MOD_NAME = "Default Gamemode";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		Config.load();
	}
}