package com.vomiter.survivorsabilities.core.effect;

import com.vomiter.survivorsabilities.core.SAAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AppetiteEffect extends MobEffect {
    public static final ResourceLocation MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("survivorsabilities", "appetite_effect");
    protected AppetiteEffect() {
        super(MobEffectCategory.NEUTRAL, 0);
        this.addAttributeModifier(
                SAAttributes.APPETITE.getDelegate(),
                MODIFIER_ID,
                3,
                AttributeModifier.Operation.ADD_VALUE
        );
    }
}
