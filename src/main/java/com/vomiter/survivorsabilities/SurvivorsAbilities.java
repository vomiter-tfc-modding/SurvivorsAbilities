package com.vomiter.survivorsabilities;

import com.mojang.logging.LogUtils;
import com.vomiter.survivorsabilities.core.SAAttributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SurvivorsAbilities.MODID)
public class SurvivorsAbilities
{
    public static final String MODID = "survivorsabilities";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static IEventBus modBus;

    public SurvivorsAbilities(FMLJavaModLoadingContext context)
    {
        modBus = context.getModEventBus();
        common();
    }

    public SurvivorsAbilities()
    {
        modBus = FMLJavaModLoadingContext.get().getModEventBus();
        common();
    }

    private void common(){
        SAAttributes.ATTRIBUTES.register(modBus);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

}
