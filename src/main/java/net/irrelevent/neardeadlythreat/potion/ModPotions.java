package net.irrelevent.neardeadlythreat.potion;


import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModPotions{
    public static Potion AEGIS;
    public static Potion LONG_AEGIS;
    public static Potion STRONG_AEGIS;

    public static Potion registerAegisPotion(String name) {
        return Registry.register (Registries.POTION, new Identifier (NearDeadlyThreat.MOD_ID, name),
                new Potion (new StatusEffectInstance (ModEffects.FULL_METAL_JACKET, 3600, 0)));
    }
    public static Potion registerLongAegisPotion(String name) {
        return Registry.register (Registries.POTION, new Identifier (NearDeadlyThreat.MOD_ID, name),
                new Potion (new StatusEffectInstance (ModEffects.FULL_METAL_JACKET, 9600, 0)));
    }
    public static Potion registerStrongAegisPotion(String name) {
        return Registry.register (Registries.POTION, new Identifier (NearDeadlyThreat.MOD_ID, name),
                new Potion (new StatusEffectInstance (ModEffects.FULL_METAL_JACKET, 1800, 5)));
    }
    //public static final Potion AEGIS =
            //Registry.register (Registries.POTION, new Identifier (NearDeadlyThreat.MOD_ID, "aegis_potion"),
                    //new Potion(new StatusEffectInstance(ModEffects.FULL_METAL_JACKET, 3600, 0)));
   //public static final Potion LONG_AEGIS =
            //Registry.register (Registries.POTION, new Identifier (NearDeadlyThreat.MOD_ID, "long_aegis_potion"),
                    //new Potion (new StatusEffectInstance(ModEffects.FULL_METAL_JACKET, 9600, 0)));
    //public static final Potion STRONG_AEGIS =
            //Registry.register (Registries.POTION, new Identifier (NearDeadlyThreat.MOD_ID, "strong_aegis_potion"),
                    //new Potion (new StatusEffectInstance (ModEffects.FULL_METAL_JACKET, 1800, 5)));

    public static void registerPotions(){
        AEGIS = registerAegisPotion("aegis_potion");
        LONG_AEGIS = registerLongAegisPotion("long_aegis_potion");
        STRONG_AEGIS = registerStrongAegisPotion("strong_aegis_potion");
    }

    public static void registerPotionsRecipes(){
        BrewingRecipeRegistry.registerPotionRecipe (Potions.AWKWARD, Item.fromBlock (ModBlocks.IRON_II_ALUMINATE), ModPotions.AEGIS);
        BrewingRecipeRegistry.registerPotionRecipe (ModPotions.AEGIS, Items.GLOWSTONE, ModPotions.STRONG_AEGIS);
        BrewingRecipeRegistry.registerPotionRecipe (ModPotions.AEGIS, Items.REDSTONE, ModPotions.LONG_AEGIS);
    }
}
