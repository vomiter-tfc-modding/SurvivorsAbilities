package com.vomiter.survivorsabilities;

import com.vomiter.survivorsabilities.core.SAAttributes;
import net.dries007.tfc.common.capabilities.size.IItemSize;
import net.dries007.tfc.common.capabilities.size.ItemSizeManager;
import net.dries007.tfc.common.capabilities.size.Size;
import net.dries007.tfc.common.capabilities.size.Weight;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SAHelper {
    public static int countHeavy(Container container, double max_load){
        int count = 0;

        for(int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                IItemSize size = ItemSizeManager.get(stack);
                if (size.getWeight(stack) == Weight.VERY_HEAVY && size.getSize(stack) == Size.HUGE) {
                    ++count;
                    if (count > max_load) {
                        return count;
                    }
                }
            }
        }

        return count;

    }

    public static int countHeavy(Player player){
        int count = 0;
        Container container = player.getInventory();
        double max_load = player.getAttribute(SAAttributes.MAX_LOAD.get()).getValue();
        for(int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                IItemSize size = ItemSizeManager.get(stack);
                if (size.getWeight(stack) == Weight.VERY_HEAVY && size.getSize(stack) == Size.HUGE) {
                    ++count;
                    if (count > max_load) {
                        return count;
                    }
                }
            }
        }

        return count;
    }

}
