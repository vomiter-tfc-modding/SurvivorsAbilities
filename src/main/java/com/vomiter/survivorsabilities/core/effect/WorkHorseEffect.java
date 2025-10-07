package com.vomiter.survivorsabilities.core.effect;

import com.vomiter.survivorsabilities.core.SAAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class WorkHorseEffect extends MobEffect {
    public WorkHorseEffect(){
        super(MobEffectCategory.BENEFICIAL, 16768624);
        this.addAttributeModifier(SAAttributes.MAX_LOAD.get(), "b20b104c-4029-4ace-8db5-3971dd51caf0", 0, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public double getAttributeModifierValue(int amp, AttributeModifier p_19458_) {
        return 1.5 * (amp + 1);
    }
}
