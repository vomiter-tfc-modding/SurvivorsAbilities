package com.vomiter.survivorsabilities.mixin.appetite;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.survivorsabilities.core.SAAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(Item.class)
public class Item_AlwaysEdible {
    @ModifyExpressionValue(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodProperties;canAlwaysEat()Z"))
    private boolean checkAppetite(boolean original, @Local ItemStack food, @Local(argsOnly = true) Player player){
        FoodProperties properties = food.getFoodProperties(player);
        if(properties == null) return original;
        float appetite = (float) Objects.requireNonNull(player.getAttribute(SAAttributes.APPETITE.get())).getValue();
        return properties.getNutrition() <= appetite;
    }
}
