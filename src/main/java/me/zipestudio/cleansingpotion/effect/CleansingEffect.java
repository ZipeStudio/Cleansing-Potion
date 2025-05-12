package me.zipestudio.cleansingpotion.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;

import java.util.Arrays;
import java.util.List;

public class CleansingEffect extends StatusEffect {

    public CleansingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    //? if >=1.21.2 {
    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {

        List<StatusEffectInstance> effectsToRemove = entity.getStatusEffects().stream()
                .filter(effect ->
                        effect.getEffectType().value().getCategory() == StatusEffectCategory.HARMFUL
                                && effect.getAmplifier() <= amplifier
                )
                .toList();

        for (StatusEffectInstance effectInstance : effectsToRemove) {
            entity.removeStatusEffect(effectInstance.getEffectType());

            world.playSound(
                    null,
                    entity.getBlockPos(),
                    SoundEvents.BLOCK_LAVA_EXTINGUISH,
                    entity.getSoundCategory(),
                    0.5F,
                    1.0F
            );

            world.spawnParticles(
                    ParticleTypes.ELECTRIC_SPARK,
                    entity.getX(), entity.getY() + 1, entity.getZ(),
                    10,
                    0.3, 0.5, 0.3,
                    0.1
            );
        }

        return super.applyUpdateEffect(world, entity, amplifier);
    }
    //?} else {
    /*@Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!(entity.getWorld() instanceof ServerWorld world)) {
            return super.applyUpdateEffect(entity, amplifier);
        }

        List<StatusEffectInstance> effectsToRemove = entity.getStatusEffects().stream()
                .filter(effect ->
                        effect.getEffectType().value().getCategory() == StatusEffectCategory.HARMFUL
                                && effect.getAmplifier() <= amplifier
                )
                .toList();

        for (StatusEffectInstance effectInstance : effectsToRemove) {
            entity.removeStatusEffect(effectInstance.getEffectType());

            world.playSound(
                    null,
                    entity.getBlockPos(),
                    SoundEvents.BLOCK_LAVA_EXTINGUISH,
                    entity.getSoundCategory(),
                    0.5F,
                    1.0F
            );

            world.spawnParticles(
                    ParticleTypes.ELECTRIC_SPARK,
                    entity.getX(), entity.getY() + 1, entity.getZ(),
                    10,
                    0.3, 0.5, 0.3,
                    0.1
            );
        }

        return super.applyUpdateEffect(entity, amplifier);
    }
     *///?}

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}