package com.vomiter.survivorsabilities.mixin.appetite;

import net.dries007.tfc.common.component.food.FoodData;
import net.dries007.tfc.common.component.food.NutritionData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.LinkedList;

@Mixin(NutritionData.class)
public interface NutritionDataAccessor {
    @Accessor("records")
    LinkedList<FoodData> getRecords();

}
