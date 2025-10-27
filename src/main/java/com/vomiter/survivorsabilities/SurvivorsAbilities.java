package com.vomiter.survivorsabilities;

import com.mojang.logging.LogUtils;
import com.vomiter.survivorsabilities.core.SAAttributes;
import com.vomiter.survivorsabilities.core.SAEffects;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(SurvivorsAbilities.MODID)
public class SurvivorsAbilities
{
    public static final String MODID = "survivorsabilities";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static IEventBus modBus;

    public SurvivorsAbilities(ModContainer mod, IEventBus bus)
    {
        modBus = bus;
        common();
    }

    private void common(){
        SAAttributes.ATTRIBUTES.register(modBus);
        SAEffects.EFFECTS.register(modBus);
    }

}
