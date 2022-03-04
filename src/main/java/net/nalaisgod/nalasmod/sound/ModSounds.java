package net.nalaisgod.nalasmod.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;

public class ModSounds {
    public static SoundEvent DOWSING_ROD_FOUND_ORE = registerSoundEvent("dowsing_rod_found_ore");

    public static SoundEvent ORIGINITE_LAMP_BREAK = registerSoundEvent("originite_lamp_break");
    public static SoundEvent ORIGINITE_LAMP_STEP = registerSoundEvent("originite_lamp_step");
    public static SoundEvent ORIGINITE_LAMP_PLACE = registerSoundEvent("originite_lamp_place");
    public static SoundEvent ORIGINITE_LAMP_HIT = registerSoundEvent("originite_lamp_hit");
    public static SoundEvent ORIGINITE_LAMP_FALL = registerSoundEvent("originite_lamp_fall");


    public static SoundEvent BAR_BRAWL = registerSoundEvent("bar_brawl");

    public static final BlockSoundGroup ORIGINITE_SOUNDS = new BlockSoundGroup(1f,1f,
            ModSounds.ORIGINITE_LAMP_BREAK, ModSounds.ORIGINITE_LAMP_STEP, ModSounds.ORIGINITE_LAMP_PLACE,
            ModSounds.ORIGINITE_LAMP_HIT, ModSounds.ORIGINITE_LAMP_FALL);


    private static SoundEvent registerSoundEvent(String name) {
    Identifier id = new Identifier(NalasMod.MOD_ID, name);
    return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
}

}
