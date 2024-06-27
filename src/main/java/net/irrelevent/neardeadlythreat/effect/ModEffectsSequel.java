package net.irrelevent.neardeadlythreat.effect;

import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffectsSequel {
    public static StatusEffect AQUA_PROFICIENT;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register (Registries.STATUS_EFFECT, new Identifier (NearDeadlyThreat.MOD_ID, name),
                new AquaProficientMobEffect (StatusEffectCategory.NEUTRAL, 0x1DAAB4 ).addAttributeModifier
                        (EntityAttributes.GENERIC_ATTACK_SPEED, "0a2e6128-aa9a-44e8-822f-87b0d52ba81b", 0.2f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
    }


    public static void registerEffects() {
        NearDeadlyThreat.LOGGER.info("Registering Mod Effects for " + NearDeadlyThreat.MOD_ID);

        AQUA_PROFICIENT = registerStatusEffect ("aqua_proficient");


    }

}
