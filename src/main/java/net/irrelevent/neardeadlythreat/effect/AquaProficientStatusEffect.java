package net.irrelevent.neardeadlythreat.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class AquaProficientStatusEffect extends StatusEffect {
    public AquaProficientStatusEffect(StatusEffectCategory category, int color) {
        super (StatusEffectCategory.NEUTRAL, 0x85FCB4);
    }

    @Override
    public boolean canApplyUpdateEffect (int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            ((PlayerEntity) entity).canBreatheInWater();
            ((PlayerEntity) entity).shouldSwimInFluids();
            Vec3d vec3d = ((PlayerEntity) entity).applyFluidMovingSpeed (32, false, new Vec3d ( 32, 32, 32));
        }
    }

}
