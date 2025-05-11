package me.zipestudio.cleansingpotion.mixin;

import me.zipestudio.cleansingpotion.effect.ModEffects;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
import java.util.stream.Collectors;

@Mixin(BeaconBlockEntity.class)
public class BeaconEffectsMixin {

    @Shadow
    @Final
    @Mutable
    private static List<List<RegistryEntry<StatusEffect>>> EFFECTS_BY_LEVEL;

    @Shadow
    @Final
    @Mutable
    private static Set<RegistryEntry<StatusEffect>> EFFECTS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addCustomEffectToBeacon(CallbackInfo ci) {

        //? if >=1.21.2 {
        RegistryEntry<StatusEffect> cleansing = Registries.STATUS_EFFECT.getEntry(ModEffects.CLEANSING.value());
        //?} else {
        /*RegistryEntry<StatusEffect> cleansing = Registries.STATUS_EFFECT.getEntry(ModEffects.CLEANSING.getKey().orElseThrow()).orElseThrow();
        *///?}

        List<List<RegistryEntry<StatusEffect>>> modifiedLevels = new ArrayList<>(EFFECTS_BY_LEVEL);

        List<RegistryEntry<StatusEffect>> levelOne = new ArrayList<>(modifiedLevels.get(2));
        levelOne.add(cleansing);
        modifiedLevels.set(2, levelOne);

        EFFECTS_BY_LEVEL = modifiedLevels;

        Set<RegistryEntry<StatusEffect>> newSet = new HashSet<>(EFFECTS);
        newSet.add(cleansing);
        EFFECTS = newSet;
    }
}