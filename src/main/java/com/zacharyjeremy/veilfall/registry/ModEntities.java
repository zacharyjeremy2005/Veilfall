package com.zacharyjeremy.veilfall.registry;

import com.zacharyjeremy.veilfall.Veilfall;
import com.zacharyjeremy.veilfall.entity.VeilfallBoss;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Veilfall.MOD_ID);

    public static final RegistryObject<EntityType<Zombie>> VEIL_STALKER = zombie("veil_stalker");
    public static final RegistryObject<EntityType<Zombie>> ROTWALKER = zombie("rotwalker");
    public static final RegistryObject<EntityType<Zombie>> ASHEN_HUSK = zombie("ashen_husk");
    public static final RegistryObject<EntityType<Zombie>> BLIGHT_CRAWLER = zombie("blight_crawler");
    public static final RegistryObject<EntityType<Zombie>> HOLLOW_SENTINEL = zombie("hollow_sentinel");
    public static final RegistryObject<EntityType<Zombie>> RIFTBOUND = zombie("riftbound");
    public static final RegistryObject<EntityType<Zombie>> FLESH_MARIONETTE = zombie("flesh_marionette");
    public static final RegistryObject<EntityType<Zombie>> DREAD_WALKER = zombie("dread_walker");
    public static final RegistryObject<EntityType<Zombie>> VEIL_THRALL = zombie("veil_thrall");
    public static final RegistryObject<EntityType<Zombie>> CORRUPTION_BRUTE = zombie("corruption_brute");

    public static final RegistryObject<EntityType<VeilfallBoss>> THE_UNMAKING = ENTITY_TYPES.register(
            "the_unmaking",
            () -> EntityType.Builder.<VeilfallBoss>of(VeilfallBoss::new, MobCategory.MONSTER)
                    .sized(0.9F, 2.7F).clientTrackingRange(10).updateInterval(3).build("the_unmaking")
    );

    private ModEntities() {}

    private static RegistryObject<EntityType<Zombie>> zombie(String name) {
        return ENTITY_TYPES.register(name, () -> EntityType.Builder.<Zombie>of(Zombie::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F).clientTrackingRange(8).updateInterval(3).build(name));
    }

    @Mod.EventBusSubscriber(modid = Veilfall.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class ModBusEvents {
        private ModBusEvents() {}

        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                registerPlacement(VEIL_STALKER.get());
                registerPlacement(ROTWALKER.get());
                registerPlacement(ASHEN_HUSK.get());
                registerPlacement(BLIGHT_CRAWLER.get());
                registerPlacement(HOLLOW_SENTINEL.get());
                registerPlacement(RIFTBOUND.get());
                registerPlacement(FLESH_MARIONETTE.get());
                registerPlacement(DREAD_WALKER.get());
                registerPlacement(VEIL_THRALL.get());
                registerPlacement(CORRUPTION_BRUTE.get());
                registerPlacement(THE_UNMAKING.get());
            });
        }

        @SubscribeEvent
        public static void attributes(EntityAttributeCreationEvent event) {
            var zombieAttributes = Zombie.createAttributes().build();
            event.put(VEIL_STALKER.get(), zombieAttributes);
            event.put(ROTWALKER.get(), zombieAttributes);
            event.put(ASHEN_HUSK.get(), zombieAttributes);
            event.put(BLIGHT_CRAWLER.get(), zombieAttributes);
            event.put(HOLLOW_SENTINEL.get(), zombieAttributes);
            event.put(RIFTBOUND.get(), zombieAttributes);
            event.put(FLESH_MARIONETTE.get(), zombieAttributes);
            event.put(DREAD_WALKER.get(), zombieAttributes);
            event.put(VEIL_THRALL.get(), zombieAttributes);
            event.put(CORRUPTION_BRUTE.get(), zombieAttributes);
            event.put(THE_UNMAKING.get(), VeilfallBoss.createAttributes().build());
        }
    }

    private static <T extends Mob> void registerPlacement(EntityType<T> type) {
        SpawnPlacements.register(type, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, reason, pos, random) -> level.getBlockState(pos.below()).is(ModBlocks.CORRUPTED_GROUND.get())
                        && Monster.checkMonsterSpawnRules((EntityType<? extends Monster>) entityType, level, reason, pos, random));
    }
}
