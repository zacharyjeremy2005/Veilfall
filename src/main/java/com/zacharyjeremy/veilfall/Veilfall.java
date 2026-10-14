package com.zacharyjeremy.veilfall;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Veilfall.MOD_ID)
public final class Veilfall {
    public static final String MOD_ID = "veilfall";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Veilfall() {
        LOGGER.info("Veilfall is awakening. The world is no longer safe.");
    }
}
