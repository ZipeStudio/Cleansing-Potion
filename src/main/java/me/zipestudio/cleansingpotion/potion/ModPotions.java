package me.zipestudio.cleansingpotion.potion;

import me.zipestudio.cleansingpotion.ModServer;
import me.zipestudio.cleansingpotion.effect.ModEffects;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {

    public static final RegistryEntry<Potion> CLEANSING_POTION = registerPotion("cleansing",
            new Potion("cleansing", (new StatusEffectInstance(ModEffects.CLEANSING, 1800, 0))));

    public static final RegistryEntry<Potion> LONG_CLEANSING_POTION = registerPotion("long_cleansing",
            new Potion("cleansing", new StatusEffectInstance(ModEffects.CLEANSING, 3600, 0)));

    public static final RegistryEntry<Potion> STRONG_CLEANSING_POTION = registerPotion("strong_cleansing",
            new Potion("cleansing", new StatusEffectInstance(ModEffects.CLEANSING, 1800, 1)));

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(ModServer.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        ModServer.LOGGER.info("Registering Mod Potions for " + ModServer.MOD_ID);

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Items.TOTEM_OF_UNDYING, ModPotions.CLEANSING_POTION);

            builder.registerPotionRecipe(ModPotions.CLEANSING_POTION, Items.REDSTONE, ModPotions.LONG_CLEANSING_POTION);
            builder.registerPotionRecipe(ModPotions.CLEANSING_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_CLEANSING_POTION);
        });

    }

}
