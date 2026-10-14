package com.zacharyjeremy.veilfall.entity;

import com.zacharyjeremy.veilfall.registry.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class VeilfallBoss extends Zombie {
    private int pulseCooldown = 0;
    private int summonCooldown = 0;

    public VeilfallBoss(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        this.xpReward = 80;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.ATTACK_DAMAGE, 14.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.85D);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) return;

        if (pulseCooldown > 0) pulseCooldown--;
        if (summonCooldown > 0) summonCooldown--;

        if (pulseCooldown <= 0) {
            pulseCooldown = 240;
            for (LivingEntity target : level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(5.0D),
                    entity -> entity != this && entity.isAlive() && !isAlliedTo(entity))) {
                target.hurt(damageSources().mobAttack(this), 7.0F);
                double dx = target.getX() - getX();
                double dz = target.getZ() - getZ();
                target.push(dx * 0.8D, 0.45D, dz * 0.8D);
            }
        }

        if (getHealth() <= getMaxHealth() * 0.70F && summonCooldown <= 0) {
            summonCooldown = 300;
            for (int i = 0; i < 2; i++) {
                Zombie minion = new Zombie(ModEntities.VEIL_STALKER.get(), level());
                minion.moveTo(getX() + (random.nextDouble() - 0.5D) * 4.0D, getY(),
                        getZ() + (random.nextDouble() - 0.5D) * 4.0D, random.nextFloat() * 360.0F, 0.0F);
                level().addFreshEntity(minion);
            }
        }
    }
}
