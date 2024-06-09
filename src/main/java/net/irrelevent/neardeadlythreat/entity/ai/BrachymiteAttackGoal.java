package net.irrelevent.neardeadlythreat.entity.ai;

import net.irrelevent.neardeadlythreat.entity.custom.BrachymiteEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Hand;

public class BrachymiteAttackGoal extends MeleeAttackGoal {
    private final BrachymiteEntity entity;
    private int attackDelay = 20;
    private int ticksUntilNextAttack = 20;
    private boolean shouldCountTillNextAttack = false;
    public BrachymiteAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super (mob, speed, pauseWhenMobIdle);
        entity = ((BrachymiteEntity)mob);
    }

    @Override
    public void start() {
        super.start ();
        attackDelay = 20;
        ticksUntilNextAttack = 20;
    }
    protected void attack(LivingEntity pEnemy) {
        if (isEnemyWithinAttackingDistance (pEnemy)) {
            shouldCountTillNextAttack = true;

            if (isTimeToStartAttackAnimation ()) {
                entity.setAttacking (true);
            }

            if(isTimeToAttack ()) {
                this.mob.getLookControl ().lookAt (pEnemy.getX (), pEnemy.getY (), pEnemy.getZ ());
            }
        } else {
            resetCooldown ();
            shouldCountTillNextAttack = false;
            entity.setAttacking (false);
            entity.attackAnimationTimeout = 0;
        }

    }

    private boolean isEnemyWithinAttackingDistance(LivingEntity pEnemy) {
        return this.entity.distanceTo (pEnemy) <= 2f;
    }



    @Override
    protected void resetCooldown() {
        this.ticksUntilNextAttack = this.getTickCount (attackDelay * 2);
    }
    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }
    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }
    protected void performAttack(LivingEntity pEnemy) {
        this.resetCooldown ();
        this.mob.swingHand (Hand.MAIN_HAND);
        this.mob.tryAttack (pEnemy);
    }

    @Override
    public void tick() {
        super.tick ();
        if(shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max (this.ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        entity.setAttacking (false);
        super.stop ();
    }
}
