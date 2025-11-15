package com.vomiter.survivorsabilities.mixin.workhorse;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.survivorsabilities.SAHelper;
import com.vomiter.survivorsabilities.core.SAAttributes;
import net.dries007.tfc.ForgeEventHandler;
import net.dries007.tfc.util.Helpers;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(value = ForgeEventHandler.class, remap = false)
public class ForgeEvent_Mixin {
    @ModifyExpressionValue(
            method = "onPlayerTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/dries007/tfc/util/Helpers;getCarryCount(Lnet/minecraft/world/Container;)Lnet/dries007/tfc/util/Helpers$CarryCount;"
            )
    )
    private static Helpers.CarryCount antiOverburden(Helpers.CarryCount original, @Local Player player) {
        if(!original.equals(Helpers.CarryCount.MORE_THAN_ONE)) return original;
        double maxLoad = Objects.requireNonNull(player.getAttribute(SAAttributes.MAX_LOAD)).getValue();
        if(maxLoad == 1) return original;
        int heavy = SAHelper.countHeavy(player);
        if (heavy > maxLoad) {
            return Helpers.CarryCount.MORE_THAN_ONE;
        } else {
            return Helpers.CarryCount.ONE;
        }
    }
}
