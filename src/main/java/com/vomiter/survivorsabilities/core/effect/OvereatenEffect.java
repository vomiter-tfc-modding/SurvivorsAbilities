package com.vomiter.survivorsabilities.core.effect;

import com.vomiter.survivorsabilities.core.SAAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class OvereatenEffect extends MobEffect {
    public OvereatenEffect() {
        super(MobEffectCategory.NEUTRAL, 0);
        this.addAttributeModifier(
                SAAttributes.APPETITE,
                ResourceLocation.fromNamespaceAndPath("survivorsabilities", "overeaten"),
                -5,
                AttributeModifier.Operation.ADD_VALUE);
    }
}
