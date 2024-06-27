package net.irrelevent.neardeadlythreat.effect;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.item.ModItems;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects{

    public static StatusEffect FULL_METAL_JACKET;
    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register (Registries.STATUS_EFFECT, new Identifier (NearDeadlyThreat.MOD_ID, name),
                new FullMetalJacketMobEffect (StatusEffectCategory.BENEFICIAL, 0x000100 ).addAttributeModifier
                        (EntityAttributes.GENERIC_MAX_ABSORPTION, "b68798b8-a5af-41eb-baf3-6a5bd6822ae0", 0.2f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
    }


    public static void registerEffects() {
        NearDeadlyThreat.LOGGER.info("Registering Mod Effects for " + NearDeadlyThreat.MOD_ID);

        FULL_METAL_JACKET = registerStatusEffect ("full_metal_jacket");

    }

}
