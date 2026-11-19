package com.vomiter.survivorsabilities.util;

import net.dries007.tfc.common.blocks.rock.Ore;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TFCFortuneMap {
    // Cache item mappings only; every drop must own its stack and data.
    private static final Map<Item, List<Item>> ENTRIES = new HashMap<>();

    public static List<ItemStack> getFortuneResult(Item item) {
        return getFortuneResult(item.getDefaultInstance());
    }

    public static List<ItemStack> getFortuneResult(ItemStack source) {
        if (source.isEmpty()) return List.of();

        var targets = ENTRIES.computeIfAbsent(source.getItem(), TFCFortuneMap::resolveTargets);
        var results = new ArrayList<ItemStack>();
        for (Item target : targets) {
            var template = source.transmuteCopy(target, 1);
            int maxStackSize = Math.max(1, template.getMaxStackSize());
            for (int remaining = source.getCount(); remaining > 0;) {
                int count = Math.min(remaining, maxStackSize);
                results.add(template.copyWithCount(count));
                remaining -= count;
            }
        }
        return results;
    }

    private static List<Item> resolveTargets(Item item) {
        var id = BuiltInRegistries.ITEM.getKey(item);
        var path = id.getPath();
        if (!path.startsWith("ore/")) return List.of();

        var grades = Ore.Grade.values();
        var oreName = path.substring("ore/".length());
        for (int i = 0; i < grades.length; i++) {
            var prefix = grades[i].name().toLowerCase(Locale.ROOT) + "_";
            if (!oreName.startsWith(prefix)) continue;

            var nextGrade = grades[i < grades.length - 1 ? i + 1 : 0];
            var targetId = ResourceLocation.fromNamespaceAndPath(
                    id.getNamespace(),
                    "ore/" + nextGrade.name().toLowerCase(Locale.ROOT) + "_"
                            + oreName.substring(prefix.length()));
            var target = BuiltInRegistries.ITEM.get(targetId);
            if (target == null || target.getDefaultInstance().isEmpty()) return List.of();

            return i < grades.length - 1 ? List.of(target) : List.of(item, target);
        }
        return List.of(item, item);
    }
}
