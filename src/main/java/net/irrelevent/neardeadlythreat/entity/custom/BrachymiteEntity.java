package net.irrelevent.neardeadlythreat.entity.custom;

import net.irrelevent.neardeadlythreat.entity.ModEntities;
import net.irrelevent.neardeadlythreat.entity.ai.BrachymiteAttackGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BrachymiteEntity extends AnimalEntity implements Ownable{
    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData (BrachymiteEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState ();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState ();
    public int attackAnimationTimeout = 0;
    private BlockPos bounds;
    private int lifeTicks;
    private boolean alive;
    private MobEntity owner;



    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick ();
        if(this.getWorld ().isClient()){
            setupAnimationStates ();
        }
    }
    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
        if(this.isAttacking ()&& attackAnimationTimeout<= 0) {
            attackAnimationTimeout = 40;
            attackAnimationState.start (this.age);
        }else {
            --this.attackAnimationTimeout;
        }
        if(!this.isAttacking ()) {
            attackAnimationState.stop ();
        }

    }

    public BrachymiteEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super (entityType, world);
        this.experiencePoints = 1;
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add (0, new SwimGoal (this));
        this.goalSelector.add (4, new WanderAroundGoal (this, 1.0));
        this.goalSelector.add (1, new StopAndLookAtEntityGoal (this, MobEntity.class, 8.0f));
        this.goalSelector.add (2, new LookAroundGoal (this));
        this.goalSelector.add (5, new MeleeAttackGoal (this, 1d, false));
        this.goalSelector.add (6, new BrachymiteAttackGoal (this, 1d, true));
        this.targetSelector.add (1, new RevengeGoal (this, RaiderEntity.class).setGroupRevenge (new Class[0]));
        this.targetSelector.add (3, new ActiveTargetGoal<PlayerEntity> ((MobEntity) this, PlayerEntity.class, true).setMaxTimeWithoutVisibility (300));
        this.targetSelector.add (4, new ActiveTargetGoal<TurtleEntity> ((MobEntity) this, TurtleEntity.class, false).setMaxTimeWithoutVisibility (300));
        this.targetSelector.add (5, new ActiveTargetGoal<TropicalFishEntity> ((MobEntity) this, TropicalFishEntity.class, true));

    }

    public static DefaultAttributeContainer.Builder createBrachymiteAttributes() {
        return MobEntity.createMobAttributes ()
                .add (EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add (EntityAttributes.GENERIC_FOLLOW_RANGE, 25.0)
                .add (EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add (EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0);

    }




    @Override
    @Nullable
    public MobEntity getOwner() {
        return this.owner;
    }



    public void setOwner(MobEntity owner) {
        this.owner = owner;
    }


    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.BRACHYMITE.create (world);
    }



    public void setAttacking(boolean attacking) {
        this.dataTracker.set (ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get (ATTACKING);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker ();
        this.dataTracker.startTracking(ATTACKING, false);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT;
    }


    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_DROWNED_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_DEATH;
    }

    public void setOwner(BrachytusEntity brachytusEntity) {
    }

    public void setBounds(BlockPos blockPos) {
    }


    public void setLifeTicks(int i) {
    }
}





