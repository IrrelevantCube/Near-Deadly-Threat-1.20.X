package net.irrelevent.neardeadlythreat.effect;

import net.fabricmc.api.ModInitializer;
import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects{
    public static StatusEffect AQUA_PROFICIENT;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register (Registries.STATUS_EFFECT, new Identifier (NearDeadlyThreat.MOD_ID, name),
                new AquaProficientStatusEffect (StatusEffectCategory.NEUTRAL, 0x85FCB4 ));
    }

    public static void registerEffects() {
        AQUA_PROFICIENT = registerStatusEffect ("aqua_proficient");
    }
}