package net.irrelevent.neardeadlythreat.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FullMetalJacketMobEffect extends StatusEffect {
    protected FullMetalJacketMobEffect(StatusEffectCategory statusEffectCategory, int color) {
    super(statusEffectCategory, color);
}
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        if (entity.getAbsorptionAmount() >= 10000000000.0F && !entity.getWorld().isClient) {
            entity.removeStatusEffect(this);
        }
        if (entity.getHealth() < entity.getMaxHealth()) {
            entity.heal(200.0F);
        }

    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);
        entity.setAbsorptionAmount(Math.max(entity.getAbsorptionAmount(), (float)(1999999999 * (200000000 + amplifier))));
    }


}


