package net.nalaisgod.nalasmod.event;

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.nalaisgod.nalasmod.config.ModConfigs;
import net.nalaisgod.nalasmod.util.NalaTitleScreen;

public class ReplaceTitleScreenEvent implements ScreenEvents.BeforeInit{
    @Override
    public void beforeInit(MinecraftClient client, Screen screen, int scaledWidth, int scaledHeight) {
        if(ModConfigs.REPLACE_TITLESCREEN && screen instanceof TitleScreen && !(screen instanceof NalaTitleScreen)) {
            client.setScreen(new NalaTitleScreen());
        }
    }
}
