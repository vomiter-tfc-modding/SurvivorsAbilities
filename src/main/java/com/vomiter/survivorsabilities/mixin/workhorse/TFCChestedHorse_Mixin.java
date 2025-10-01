package com.vomiter.survivorsabilities.mixin.workhorse;

import com.vomiter.survivorsabilities.core.SAAttributes;
import net.dries007.tfc.common.entities.livestock.horse.TFCChestedHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TFCChestedHorse.class, remap = false)
public class TFCChestedHorse_Mixin {
    @ModifyConstant(method = "containerChanged", constant = @Constant(intValue = 0), remap = true)
    private int antiOverburnden(int constant) {
        var self = (TFCChestedHorse)(Object) this;
        return (int) (self.getAttribute(SAAttributes.MAX_LOAD.get()).getValue() - 1);
    }
}
