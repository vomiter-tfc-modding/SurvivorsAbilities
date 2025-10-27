package com.vomiter.survivorsabilities.core.effect;

import com.vomiter.survivorsabilities.core.SAAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class WorkHorseEffect extends MobEffect {
    public WorkHorseEffect(){
        super(MobEffectCategory.BENEFICIAL, 16768624);
        this.addAttributeModifier(
                SAAttributes.MAX_LOAD,
                ResourceLocation.fromNamespaceAndPath("survivorsabilities", "appetite")
                , 1.5
                , AttributeModifier.Operation.ADD_VALUE);
    }
}
