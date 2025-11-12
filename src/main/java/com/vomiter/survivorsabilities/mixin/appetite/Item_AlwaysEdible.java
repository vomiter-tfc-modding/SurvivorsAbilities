package com.vomiter.survivorsabilities.mixin.appetite;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.survivorsabilities.core.SAAttributes;
import com.vomiter.survivorsabilities.core.SAEffects;
import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.dries007.tfc.common.capabilities.food.IFood;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(Item.class)
public class Item_AlwaysEdible {
    @ModifyExpressionValue(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodProperties;canAlwaysEat()Z"))
    private boolean checkAppetite(boolean original, @Local ItemStack foodStack, @Local(argsOnly = true) Player player){
        if(player.getFoodData().needsFood()) return original;
        IFood food = FoodCapability.get(foodStack);
        if(food == null) return original;
        float appetite = (float) Objects.requireNonNull(player.getAttribute(SAAttributes.APPETITE.get())).getValue();
        boolean canEat = food.getData().hunger() <= appetite;
        int overeaten_lvl = (int) Math.ceil(((float)food.getData().hunger()/4f));
        int basic_overeaten_lvl = player.hasEffect(SAEffects.OVEREATEN.get()) ? Objects.requireNonNull(player.getEffect(SAEffects.OVEREATEN.get())).getAmplifier() : 0;
        if(canEat && !player.isCreative()) player.addEffect(new MobEffectInstance(SAEffects.OVEREATEN.get(), 20 * 60 * 10, overeaten_lvl + basic_overeaten_lvl, true, false, false));
        return canEat;
    }
}
