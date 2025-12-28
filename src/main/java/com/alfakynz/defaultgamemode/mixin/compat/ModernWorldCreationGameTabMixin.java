package com.alfakynz.defaultgamemode.mixin.compat;

import com.alfakynz.defaultgamemode.config.Config;
import de.keksuccino.modernworldcreation.ModernWorldCreationGameTab;
import de.keksuccino.modernworldcreation.mixin.mixins.common.client.IMixinCreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModernWorldCreationGameTab.class)
public class ModernWorldCreationGameTabMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CreateWorldScreen parent, CallbackInfo ci) {
        WorldCreationUiState uiState =
                ((IMixinCreateWorldScreen) parent).get_uiState_ModernWorldCreation();

        uiState.setGameMode(
                WorldCreationUiState.SelectedGameMode.valueOf(Config.GAMEMODE.name())
        );
    }
}