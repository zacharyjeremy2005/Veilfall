package com.zacharyjeremy.veilfall;

import com.mojang.logging.LogUtils;
import com.zacharyjeremy.veilfall.network.VeilfallNetwork;
import com.zacharyjeremy.veilfall.registry.ModBlocks;
import com.zacharyjeremy.veilfall.registry.ModItems;
import com.zacharyjeremy.veilfall.registry.ModEntities;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Veilfall.MOD_ID)
public final class Veilfall {
    public static final String MOD_ID = "veilfall";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Veilfall() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModEntities.ENTITY_TYPES.register(modBus);
        VeilfallNetwork.register();
        LOGGER.info("Veilfall is awakening. The world is no longer safe.");
    }
}
