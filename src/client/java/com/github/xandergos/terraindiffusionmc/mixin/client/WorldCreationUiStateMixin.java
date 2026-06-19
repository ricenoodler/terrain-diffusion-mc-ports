package com.github.xandergos.terraindiffusionmc.mixin.client;

import com.github.xandergos.terraindiffusionmc.client.WorldScaleSettingsScreen;
import net.minecraft.client.gui.screens.worldselection.PresetEditor;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Makes Terrain Diffusion behave like a vanilla customizable world preset.
 *
 * Vanilla's Customize button already enables itself when getPresetEditor()
 * returns non-null, and already opens that editor when clicked.
 */
@Mixin(WorldCreationUiState.class)
public abstract class WorldCreationUiStateMixin {
    @Unique
    private static final ResourceKey<WorldPreset> TERRAIN_DIFFUSION_PRESET_KEY =
            ResourceKey.create(
                    Registries.WORLD_PRESET,
                    Identifier.fromNamespaceAndPath("terrain-diffusion-mc", "terrain_diffusion")
            );

    @Shadow
    public abstract WorldCreationUiState.WorldTypeEntry getWorldType();

    @Inject(method = "getPresetEditor", at = @At("RETURN"), cancellable = true)
    private void terrainDiffusionMc$getPresetEditor(CallbackInfoReturnable<PresetEditor> callbackInfoReturnable) {
        if (callbackInfoReturnable.getReturnValue() != null) {
            return;
        }

        WorldCreationUiState.WorldTypeEntry worldType = this.getWorldType();

        if (!terrainDiffusionMc$isTerrainDiffusionWorldType(worldType)) {
            return;
        }

        callbackInfoReturnable.setReturnValue(
                (createWorldScreen, worldCreationContext) -> new WorldScaleSettingsScreen(createWorldScreen)
        );
    }

    @Unique
    private static boolean terrainDiffusionMc$isTerrainDiffusionWorldType(WorldCreationUiState.WorldTypeEntry worldType) {
        if (worldType == null) {
            return false;
        }

        if (TERRAIN_DIFFUSION_PRESET_KEY.equals(worldType.preset())) {
            return true;
        }

        return "terrain diffusion".equalsIgnoreCase(worldType.describePreset().getString());
    }
}