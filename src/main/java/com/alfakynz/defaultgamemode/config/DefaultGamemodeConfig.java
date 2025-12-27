package com.alfakynz.defaultgamemode.config;

import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState.SelectedGameMode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.alfakynz.defaultgamemode.DefaultGamemode.LOGGER;

public class DefaultGamemodeConfig {

    private static final Path CONFIG_PATH = Path.of("config", "default-gamemode.txt");

    public static SelectedGameMode GAMEMODE = SelectedGameMode.CREATIVE;

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            createDefaultFile();
            return;
        }

        try {
            List<String> lines = Files.readAllLines(CONFIG_PATH);
            for (String line : lines) {
                line = line.trim();

                if (line.startsWith("#") || line.isEmpty()) continue;

                if (line.startsWith("gamemode=")) {
                    String value = line.substring("gamemode=".length()).toLowerCase();

                    switch (value) {
                        case "survival" -> GAMEMODE = SelectedGameMode.SURVIVAL;
                        case "hardcore" -> GAMEMODE = SelectedGameMode.HARDCORE;
                        case "spectator" -> GAMEMODE = SelectedGameMode.DEBUG;
                        default -> GAMEMODE = SelectedGameMode.CREATIVE;
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load Default Gamemode configuration.", e);
        }
    }

    private static void createDefaultFile() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH,
                    """
                         # Choose your default gamemode for new worlds.
                         # Options: survival, creative, hardcore, spectator
                         gamemode=creative
                         """);
        } catch (IOException e) {
            LOGGER.error("Failed to create Default Gamemode configuration file.", e);
        }
    }
}