package com.vomiter.survivorsabilities.core;

import com.vomiter.survivorsabilities.SurvivorsAbilities;
import com.vomiter.survivorsabilities.core.effect.WorkHorseEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SAEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SurvivorsAbilities.MODID);
    public static final RegistryObject<MobEffect> WorkHorse = EFFECTS.register("workhorse", WorkHorseEffect::new);

}
