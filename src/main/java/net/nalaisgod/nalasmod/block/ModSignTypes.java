package net.nalaisgod.nalasmod.block;

import net.minecraft.util.SignType;
import net.nalaisgod.nalasmod.mixin.SignTypeAccessor;

public class ModSignTypes {
    public static final SignType SOUL_BLOSSOM =
            SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("soul_blossom"));
}
