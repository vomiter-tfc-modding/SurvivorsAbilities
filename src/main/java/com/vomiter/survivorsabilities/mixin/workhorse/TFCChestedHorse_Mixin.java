package com.vomiter.survivorsabilities.mixin.workhorse;

import com.vomiter.survivorsabilities.SAHelper;
import com.vomiter.survivorsabilities.core.SAAttributes;
import net.dries007.tfc.common.entities.livestock.horse.TFCChestedHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TFCChestedHorse.class, remap = false)
public class TFCChestedHorse_Mixin {
    @Shadow private boolean overburdened;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/dries007/tfc/common/entities/livestock/horse/TFCChestedHorse;tickAnimalData()V"), remap = true)
    private void antiOverburden(CallbackInfo ci) {
        var self = (TFCChestedHorse)(Object) this;
        if(overburdened){
            double max_load = self.getAttribute(SAAttributes.MAX_LOAD).getValue();
            overburdened = (SAHelper.countHeavy(((AbstractHorseAccessor)self).getInv(), max_load) > max_load);
        }
    }
}
