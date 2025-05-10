package me.zipestudio.cleansingpotion.effect;

import me.zipestudio.cleansingpotion.ModServer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> CLEANSING = registerStatusEffect(
            "cleansing",
            new CleansingEffect(StatusEffectCategory.BENEFICIAL, 16707436)
    );

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(ModServer.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        ModServer.LOGGER.info("Registering Mod Effects for " + ModServer.MOD_ID);
    }

}
