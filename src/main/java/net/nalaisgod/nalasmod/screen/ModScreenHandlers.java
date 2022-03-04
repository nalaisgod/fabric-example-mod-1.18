package net.nalaisgod.nalasmod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;

public class ModScreenHandlers {
    public static ScreenHandlerType<OriginiteBlasterScreenHandler> ORIGINITE_BLASTER_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(NalasMod.MOD_ID, "originite_blaster"),
                    OriginiteBlasterScreenHandler::new);
}
