package com.github.xandergos.terraindiffusionmc.mixin.client;

import com.github.xandergos.terraindiffusionmc.client.WorldScaleSettingsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Adds a direct Terrain Diffusion settings button to the Create World screen.
 *
 * This avoids relying on Minecraft's internal CreateWorldScreen.WorldTab method names,
 * which changed in 26.1.2.
 */
@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin extends Screen {
    protected CreateWorldScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void terrainDiffusionMc$addTerrainDiffusionSettingsButton(CallbackInfo callbackInfo) {
        int buttonWidth = 180;
        int buttonHeight = 20;
        int x = this.width - buttonWidth - 10;
        int y = 10;

        this.addRenderableWidget(
                Button.builder(
                                Component.literal("Terrain Diffusion Settings"),
                                button -> Minecraft.getInstance().setScreen(
                                        new WorldScaleSettingsScreen((CreateWorldScreen) (Object) this)
                                )
                        )
                        .bounds(x, y, buttonWidth, buttonHeight)
                        .build()
        );
    }
}
