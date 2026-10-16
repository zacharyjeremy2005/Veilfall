package com.zacharyjeremy.veilfall.entity;

import com.zacharyjeremy.veilfall.registry.ModEntities;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class VeilfallBoss extends Zombie {
    private int pulseCooldown = 100;
    private int summonCooldown = 180;
    private int phaseTwoPulse = 0;

    public VeilfallBoss(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        this.xpReward = 100;
        this.setCustomName(Component.literal("§5§lTHE UNMAKING"));
        this.setCustomNameVisible(true);
        this.setPersistenceRequired();
        this.setCanBreakDoors(true);
        this.setBaby(false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 420.0D)
                .add(Attributes.ATTACK_DAMAGE, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.FOLLOW_RANGE, 56.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.95D)
                .add(Attributes.ARMOR, 12.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 6.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide || isDeadOrDying()) return;

        boolean phaseTwo = getHealth() <= getMaxHealth() * 0.5F;
        if (pulseCooldown > 0) pulseCooldown--;
        if (summonCooldown > 0) summonCooldown--;
        if (phaseTwoPulse > 0) phaseTwoPulse--;

        if (level() instanceof ServerLevel serverLevel && tickCount % 5 == 0) {
            serverLevel.sendParticles(phaseTwo ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.REVERSE_PORTAL,
                    getX(), getY() + getBbHeight() * 0.65D, getZ(),
                    phaseTwo ? 8 : 4, getBbWidth() * 0.7D, getBbHeight() * 0.35D,
                    getBbWidth() * 0.7D, phaseTwo ? 0.025D : 0.005D);
            if (phaseTwo && tickCount % 20 == 0) {
                serverLevel.sendParticles(ParticleTypes.FLASH, getX(), getY() + 1.0D, getZ(), 1, 0.1D, 0.1D, 0.1D, 0.0D);
            }
        }

        if (pulseCooldown <= 0) {
            pulseCooldown = phaseTwo ? 100 : 170;
            double radius = phaseTwo ? 9.0D : 6.0D;
            float damage = phaseTwo ? 12.0F : 8.0F;
            if (level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(phaseTwo ? ParticleTypes.SONIC_BOOM : ParticleTypes.PORTAL,
                        getX(), getY() + 1.0D, getZ(), phaseTwo ? 1 : 32,
                        radius * 0.45D, 0.5D, radius * 0.45D, 0.08D);
                serverLevel.playSound(null, blockPosition(),
                        phaseTwo ? SoundEvents.WITHER_BREAK_BLOCK : SoundEvents.END_PORTAL_SPAWN,
                        SoundSource.HOSTILE, 2.4F, phaseTwo ? 0.65F : 0.85F);
            }
            for (Player target : level().getEntitiesOfClass(Player.class, getBoundingBox().inflate(radius),
                    player -> player.isAlive() && !player.isCreative() && !player.isSpectator())) {
                target.hurt(damageSources().mobAttack(this), damage);
                double dx = target.getX() - getX();
                double dz = target.getZ() - getZ();
                double length = Math.max(0.1D, Math.sqrt(dx * dx + dz * dz));
                target.push(dx / length * (phaseTwo ? 1.7D : 1.1D), phaseTwo ? 0.75D : 0.45D,
                        dz / length * (phaseTwo ? 1.7D : 1.1D));
            }
        }

        if (phaseTwo && phaseTwoPulse <= 0) {
            phaseTwoPulse = 260;
            if (level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.REVERSE_PORTAL, getX(), getY() + 1.0D, getZ(),
                        90, 2.8D, 1.5D, 2.8D, 0.18D);
                serverLevel.playSound(null, blockPosition(), SoundEvents.WITHER_SPAWN,
                        SoundSource.HOSTILE, 1.8F, 0.7F);
            }
            heal(24.0F);
        }

        if (getHealth() <= getMaxHealth() * 0.72F && summonCooldown <= 0) {
            summonCooldown = phaseTwo ? 170 : 260;
            int minions = phaseTwo ? 4 : 2;
            for (int i = 0; i < minions; i++) {
                Zombie minion = ModEntities.VEIL_STALKER.get().create(level());
                if (minion == null) continue;
                minion.moveTo(getX() + (random.nextDouble() - 0.5D) * 6.0D, getY(),
                        getZ() + (random.nextDouble() - 0.5D) * 6.0D, random.nextFloat() * 360.0F, 0.0F);
                minion.setTarget(getTarget());
                level().addFreshEntity(minion);
            }
            if (level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.SOUL, getX(), getY() + 1.0D, getZ(),
                        35, 1.6D, 1.0D, 1.6D, 0.08D);
                serverLevel.playSound(null, blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON,
                        SoundSource.HOSTILE, 1.6F, 0.55F);
            }
        }
    }
}
