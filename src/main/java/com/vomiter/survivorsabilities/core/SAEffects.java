package com.vomiter.survivorsabilities.core;

import com.vomiter.survivorsabilities.SurvivorsAbilities;
import com.vomiter.survivorsabilities.core.effect.OvereatenEffect;
import com.vomiter.survivorsabilities.core.effect.WorkHorseEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SAEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, SurvivorsAbilities.MODID);
    public static final DeferredHolder<MobEffect, WorkHorseEffect> WorkHorse = EFFECTS.register("workhorse", WorkHorseEffect::new);
    public static final DeferredHolder<MobEffect, OvereatenEffect> Overeaten = EFFECTS.register("overeaten", OvereatenEffect::new);
    public static final DeferredHolder<MobEffect, OvereatenEffect> APPETITE = EFFECTS.register("appetite", OvereatenEffect::new);


}
