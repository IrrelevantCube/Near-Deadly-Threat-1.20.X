package net.irrelevent.neardeadlythreat.item;

import net.irrelevent.neardeadlythreat.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent CRAB_LEGS = new FoodComponent.Builder().hunger (2).saturationModifier(0.90f)
            .statusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 800), 0.90f).build();


}
