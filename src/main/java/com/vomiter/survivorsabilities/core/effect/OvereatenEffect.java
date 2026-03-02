package com.vomiter.survivorsabilities.core.effect;

import com.vomiter.survivorsabilities.core.SAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.jetbrains.annotations.NotNull;

public class OvereatenEffect extends MobEffect {
    public OvereatenEffect() {
        super(MobEffectCategory.NEUTRAL, 0);
        this.addAttributeModifier(SAAttributes.APPETITE.get(), "ac495014-1d8f-4a9d-8149-6ef7dd0e6430", 0, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public double getAttributeModifierValue(int amp, @NotNull AttributeModifier p_19458_) {
        return 5 * (amp + 1) * -1;
    }
}
