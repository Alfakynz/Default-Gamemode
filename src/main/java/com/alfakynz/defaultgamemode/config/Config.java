package com.alfakynz.defaultgamemode.config;

import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState.SelectedGameMode;
import net.minecraft.world.Difficulty;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.alfakynz.defaultgamemode.DefaultGamemode.LOGGER;

public class Config {

    private static final Path CONFIG_PATH = Path.of("config", "default-gamemode.txt");

    public static SelectedGameMode GAMEMODE = SelectedGameMode.CREATIVE;
    public static Difficulty DIFFICULTY = Difficulty.NORMAL;

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

                if (line.startsWith("difficulty=")) {
                    String value = line.substring("difficulty=".length()).toLowerCase();

                    switch (value) {
                        case "peaceful" -> DIFFICULTY = Difficulty.PEACEFUL;
                        case "easy" -> DIFFICULTY = Difficulty.EASY;
                        case "hard" -> DIFFICULTY = Difficulty.HARD;
                        default -> DIFFICULTY = Difficulty.NORMAL;
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load Default Gamemode configuration.", e);
        }
    }

    public static void save() {
        try (Writer writer = new FileWriter(CONFIG_PATH.toString())) {
            writer.write("# Choose your default gamemode and difficulty for new worlds.\n");

            // Gamemode
            writer.write("# Options: survival, creative, hardcore, spectator\n");
            String gamemodeString;
            switch (GAMEMODE) {
                case SURVIVAL -> gamemodeString = "survival";
                case HARDCORE -> gamemodeString = "hardcore";
                case DEBUG -> gamemodeString = "spectator";
                default -> gamemodeString = "creative";
            }
            writer.write("gamemode=" + gamemodeString + "\n\n");

            // Difficulty
            writer.write("# Options: peaceful, easy, normal, hard\n");
            String difficultyString;
            switch (DIFFICULTY) {
                case PEACEFUL -> difficultyString = "peaceful";
                case EASY -> difficultyString = "easy";
                case HARD -> difficultyString = "hard";
                default -> difficultyString = "normal";
            }
            writer.write("difficulty=" + difficultyString);
        } catch (IOException e) {
            LOGGER.error("Failed to save Default Gamemode configuration.", e);
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
                         
                         # Options: peaceful, easy, normal, hard
                         difficulty=normal
                         """);
        } catch (IOException e) {
            LOGGER.error("Failed to create Default Gamemode configuration file.", e);
        }
    }
}