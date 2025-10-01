package com.vomiter.survivorsabilities.mixin.nourishment;

import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.survivorsabilities.core.SAAttributes;
import net.dries007.tfc.common.capabilities.food.TFCFoodData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(TFCFoodData.class)
public class TFCFoodData_Mixin {

    @Shadow @Final private Player sourcePlayer;

    @Shadow @Final private FoodData delegate;

    @ModifyConstant(method = "addExhaustion", constant = @Constant(floatValue = 0.4f))
    private float tolerateExhaustion(float value, @Local(argsOnly = true) float toAdd){
        if(toAdd > 0){
            float toleranceLvl = (float) sourcePlayer.getAttribute(SAAttributes.HUNGER_TOLERANCE.get()).getValue();
            return Math.max(value * (1 - toleranceLvl/10), 0);
        }
        return value;
    }

}
