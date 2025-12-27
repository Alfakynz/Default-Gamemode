package com.alfakynz.defaultgamemode.mixin;

import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.class)
public abstract class GamemodeMixin {

	@Final
    @Shadow
    WorldCreationUiState uiState;

	@Inject(method = "init", at = @At("TAIL"))
	private void setDefaultGamemodeCreative(CallbackInfo ci) {
		this.uiState.setGameMode(WorldCreationUiState.SelectedGameMode.CREATIVE);
	}
}