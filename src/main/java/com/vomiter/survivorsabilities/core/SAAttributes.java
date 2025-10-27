package com.vomiter.survivorsabilities.core;

import com.vomiter.survivorsabilities.SurvivorsAbilities;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class SAAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(Registries.ATTRIBUTE, SurvivorsAbilities.MODID);

    public static final DeferredHolder<Attribute, Attribute> MAX_LOAD = ATTRIBUTES.register(
            "max_load",
            () -> new RangedAttribute(
                    "attribute.name." + SurvivorsAbilities.MODID + ".max_load",
                    1.0D, 1.0D, 31.0D
            ).setSyncable(true)
    );

    public static final DeferredHolder<Attribute, Attribute> HUNGER_TOLERANCE = ATTRIBUTES.register(
            "hunger_tolerance",
            () -> new RangedAttribute(
                    "attribute.name." + SurvivorsAbilities.MODID + ".hunger_tolerance",
                    0.0D, 0.0D, 10.0D
            ).setSyncable(true)
    );

    public static final DeferredHolder<Attribute, Attribute> APPETITE = ATTRIBUTES.register(
            "appetite",
            () -> new RangedAttribute(
                    "attribute.name." + SurvivorsAbilities.MODID + ".appetite",
                    0.0D, 0.0D, 20.0D
            ).setSyncable(true)
    );


}
