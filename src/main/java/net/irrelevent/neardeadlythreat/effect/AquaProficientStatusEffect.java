package net.irrelevent.neardeadlythreat.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.Vec3d;

public class AquaProficientStatusEffect extends StatusEffect {
    public AquaProficientStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super (statusEffectCategory, color);
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int gravity) {
        if (entity != null) {
            Vec3d vec3d = (entity.applyFluidMovingSpeed (32, true, new Vec3d (32, 32, 32)));
            boolean b = entity.canBreatheInWater ();

        }
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int pAmplifier) {
        return true;
    }


}


