package com.alfakynz.defaultgamemode.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState.SelectedGameMode;

public class ConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("option.default_gamemode.config"))
                .setSavingRunnable(Config::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        var generalCategory = builder.getOrCreateCategory(Component.translatable("option.default_gamemode.config.general"));

        generalCategory.addEntry(entryBuilder
                .startEnumSelector(
                        Component.translatable("option.default_gamemode.config.gamemode"),
                        SelectedGameMode.class,
                        Config.GAMEMODE
                )
                .setEnumNameProvider(value ->
                        Component.translatable(
                                "option.default_gamemode.config.gamemode." + value.name().toLowerCase()
                        )
                )
                .setDefaultValue(SelectedGameMode.CREATIVE)
                .setSaveConsumer(newValue -> Config.GAMEMODE = newValue)
                .build()
        );

        return builder.build();
    }
}