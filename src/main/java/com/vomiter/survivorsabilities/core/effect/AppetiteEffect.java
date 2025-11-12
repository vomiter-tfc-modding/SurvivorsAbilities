package com.vomiter.survivorsabilities.core.effect;

import com.vomiter.survivorsabilities.core.SAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AppetiteEffect extends MobEffect {
    public AppetiteEffect() {
        super(MobEffectCategory.NEUTRAL, 0);
        this.addAttributeModifier(SAAttributes.APPETITE.get(), "b20b104c-4029-4ace-8db5-3971dd51caf0", 0, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public double getAttributeModifierValue(int amp, AttributeModifier p_19458_) {
        return 3 * (amp + 1);
    }

}
