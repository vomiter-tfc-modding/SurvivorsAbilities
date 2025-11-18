package com.vomiter.survivorsabilities;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SurvivorsAbilities.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SAConfig {
    public static class Common {
        public final ForgeConfigSpec.BooleanValue forceCancelOverburden;
        public Common(ForgeConfigSpec.Builder builder) {
            forceCancelOverburden = builder
                    .comment("This is a fallback option when some mods in your modpack override the effect of workhorse. It should be false if the effect works just fine.")
                    .define("forceCancelOverburden", false);
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        COMMON = new Common(builder);
        COMMON_SPEC = builder.build();
    }

}
