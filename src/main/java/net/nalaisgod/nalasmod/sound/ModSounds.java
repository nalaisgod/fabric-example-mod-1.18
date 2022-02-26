package net.nalaisgod.nalasmod.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;

public class ModSounds {
    public static SoundEvent DOWSING_ROD_FOUND_ORE = registerSoundEvent("dowsing_rod_found_ore");

private static SoundEvent registerSoundEvent(String name) {
    Identifier id = new Identifier(NalasMod.MOD_ID, name);
    return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
}

}
