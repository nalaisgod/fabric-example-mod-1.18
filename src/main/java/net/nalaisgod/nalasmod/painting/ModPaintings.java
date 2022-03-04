package net.nalaisgod.nalasmod.painting;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;

public class ModPaintings {

    public static final PaintingMotive SUNSET = registerPainting("sunset", new PaintingMotive(32,16));
    public static final PaintingMotive PLANT = registerPainting("plant", new PaintingMotive(16,16));
    public static final PaintingMotive WANDERER = registerPainting("wanderer", new PaintingMotive(16,32));
    public static final PaintingMotive EL_ORB = registerPainting("el_orb", new PaintingMotive(16,16));
    public static final PaintingMotive BEACONS = registerPainting("beacons", new PaintingMotive(16,32));

    private static PaintingMotive registerPainting(String name, PaintingMotive paintingMotive) {
        return Registry.register(Registry.PAINTING_MOTIVE, new Identifier(NalasMod.MOD_ID, name), paintingMotive);
    }

    public static void registerPaintings() {
        System.out.println("Register Paintings for " + NalasMod.MOD_ID);
    }
}
