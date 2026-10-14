package com.zacharyjeremy.veilfall.item;

import java.util.function.Supplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.common.ForgeSpawnEggItem;

/** Spawn egg that safely resolves its entity type after Forge registry setup. */
public final class DeferredVeilSpawnEggItem extends ForgeSpawnEggItem {
    public DeferredVeilSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> entityType,
                                    int backgroundColor, int highlightColor, Properties properties) {
        super(entityType, backgroundColor, highlightColor, properties);
    }
}
