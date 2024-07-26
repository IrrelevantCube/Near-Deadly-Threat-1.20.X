package net.irrelevent.neardeadlythreat.effect;

import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModEffects {
    private static final int FULL_METAL_JACKET_PADDING_DURATION = 22;
    public static StatusEffect AQUA_PROFICIENT;
    public static StatusEffect FULL_METAL_JACKET;



    public static StatusEffect registerJacketStatusEffect(String name) {
        return Registry.register (Registries.STATUS_EFFECT, new Identifier (NearDeadlyThreat.MOD_ID, name),
                new FullMetalJacketMobEffect (StatusEffectCategory.BENEFICIAL, 0x000100).addAttributeModifier
                                (EntityAttributes.GENERIC_MAX_ABSORPTION, "b68798b8-a5af-41eb-baf3-6a5bd6822ae0", 100.0f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
                        .addAttributeModifier (EntityAttributes.GENERIC_MAX_HEALTH, "2a7951a5-1705-471b-b7f8-443fa2345277", 200.0f, EntityAttributeModifier.Operation.ADDITION));
        //.getFactorCalculationDataSupplier(Optional.ofNullable((StatusEffectInstance.FactorCalculationData)this.factorCalculationDataSupplier.get())));
    }

    public static StatusEffect registerAquaStatusEffect(String name) {
        return Registry.register (Registries.STATUS_EFFECT, new Identifier (NearDeadlyThreat.MOD_ID, name),
                new AquaProficientMobEffect (StatusEffectCategory.NEUTRAL, 0x1DAAB4).addAttributeModifier
                        (EntityAttributes.GENERIC_ATTACK_SPEED, "0a2e6128-aa9a-44e8-822f-87b0d52ba81b", 0.2f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));


    }


    public static void registerEffects() {
        NearDeadlyThreat.LOGGER.info ("Registering Mod Effects for " + NearDeadlyThreat.MOD_ID);


        AQUA_PROFICIENT = registerAquaStatusEffect ("aqua_proficient");
        FULL_METAL_JACKET = registerJacketStatusEffect ("full_metal_jacket");
    }
}
