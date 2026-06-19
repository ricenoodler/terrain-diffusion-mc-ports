package com.github.xandergos.terraindiffusionmc.mixin.client;

import com.github.xandergos.terraindiffusionmc.client.WorldScaleSettingsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Reuses vanilla's World tab "Customize" button for Terrain Diffusion worlds.
 */
@Mixin(targets = "net.minecraft.client.gui.screens.worldselection.CreateWorldScreen$WorldTab")
public abstract class CreateWorldScreenWorldTabMixin {
    @Shadow
    private Button customizeTypeButton;

    private static final ResourceKey<WorldPreset> TERRAIN_DIFFUSION_PRESET_KEY =
            ResourceKey.create(Registries.WORLD_PRESET, Identifier.fromNamespaceAndPath("terrain-diffusion-mc", "terrain_diffusion"));

    @Inject(method = "method_48676", at = @At("TAIL"), require = 0)
    private void terrainDiffusionMc$enableCustomizeButtonForTerrainDiffusion(WorldCreationUiState worldCreator, CallbackInfo callbackInfo) {
        if (isTerrainDiffusionWorldTypeSelected()) {
            customizeTypeButton.active = true;
        }
    }

    @Inject(method = "method_48680", at = @At("HEAD"), cancellable = true, require = 0)
    private void terrainDiffusionMc$forceCustomizeAvailable(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (isTerrainDiffusionWorldTypeSelected()) {
            callbackInfoReturnable.setReturnValue(true);
        }
    }

    @Inject(method = "method_48681", at = @At("HEAD"), cancellable = true, require = 0)
    private void terrainDiffusionMc$forceCustomizeVisible(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (isTerrainDiffusionWorldTypeSelected()) {
            callbackInfoReturnable.setReturnValue(true);
        }
    }

    @Inject(method = "openPresetEditor", at = @At("HEAD"), cancellable = true, require = 0)
    private void terrainDiffusionMc$openTerrainScaleScreen(CallbackInfo callbackInfo) {
        if (!isTerrainDiffusionWorldTypeSelected()) {
            return;
        }

        Minecraft minecraftClient = Minecraft.getInstance();
        CreateWorldScreen createWorldScreen = terrainDiffusionMc$getCreateWorldScreen();

        if (minecraftClient != null && createWorldScreen != null) {
            minecraftClient.setScreen(new WorldScaleSettingsScreen(createWorldScreen));
            callbackInfo.cancel();
        }
    }

    private CreateWorldScreen terrainDiffusionMc$getCreateWorldScreen() {
        Minecraft minecraftClient = Minecraft.getInstance();

        if (minecraftClient != null && minecraftClient.screen instanceof CreateWorldScreen createWorldScreen) {
            return createWorldScreen;
        }

        return null;
    }

    private boolean isTerrainDiffusionWorldTypeSelected() {
        CreateWorldScreen createWorldScreen = terrainDiffusionMc$getCreateWorldScreen();

        if (createWorldScreen == null) {
            return false;
        }

        WorldCreationUiState worldCreator = createWorldScreen.getUiState();

        if (worldCreator == null) {
            return false;
        }

        WorldCreationUiState.WorldTypeEntry worldType = worldCreator.getWorldType();

        if (worldType == null) {
            return false;
        }

        if (TERRAIN_DIFFUSION_PRESET_KEY.equals(worldType.preset())) {
            return true;
        }

        return "terrain diffusion".equalsIgnoreCase(worldType.describePreset().getString());
    }
}