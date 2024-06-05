package net.irrelevent.neardeadlythreat.entity.custom;

import net.irrelevent.neardeadlythreat.entity.ModEntities;
import net.irrelevent.neardeadlythreat.entity.ai.BrachytusAttackGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.List;

public class BrachytusEntity extends SpellcastingAnimalEntity {
    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData (BrachytusEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState ();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState ();
    public int attackAnimationTimeout = 0;

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

    public BrachytusEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super (entityType, world);
        this.experiencePoints = 10;
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add (0, new SwimGoal (this));
        this.goalSelector.add (4, new WanderAroundGoal (this, 1.0));
        this.goalSelector.add (1, new StopAndLookAtEntityGoal (this, MobEntity.class, 8.0f));
        this.goalSelector.add (2, new LookAroundGoal (this));
        this.goalSelector.add (5, new BrachytusAttackGoal (this, 1d, true));
        this.goalSelector.add (4, new PounceAtTargetGoal (this, 0.6f));
        this.goalSelector.add(7, new SummonVexGoal ());
        this.goalSelector.add (8, new ConjureFangsGoal ());
        this.targetSelector.add (1, new RevengeGoal (this, RaiderEntity.class).setGroupRevenge (new Class[0]));
        this.targetSelector.add (2, new ActiveTargetGoal<PlayerEntity> ((MobEntity) this, PlayerEntity.class, true).setMaxTimeWithoutVisibility (300));
        this.targetSelector.add (3, new ActiveTargetGoal<TurtleEntity> ((MobEntity) this, TurtleEntity.class, false).setMaxTimeWithoutVisibility (300));
        this.targetSelector.add (4, new ActiveTargetGoal<TropicalFishEntity> ((MobEntity) this, TropicalFishEntity.class, true));

    }

    public static DefaultAttributeContainer.Builder createBrachytusAttributes() {
        return MobEntity.createMobAttributes ()
                .add (EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add (EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)
                .add (EntityAttributes.GENERIC_MAX_HEALTH, 30.0)
                .add (EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 5.0)
                .add (EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0);

    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.BRACHYTUS.create (world);
    }

    class SummonVexGoal
            extends SpellcastingAnimalEntity.CastSpellGoal {
        public final TargetPredicate closeVexPredicate;

        SummonVexGoal() {
            super();
            this.closeVexPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(16.0).ignoreVisibility().ignoreDistanceScalingFactor();
        }

        @Override
        public boolean canStart() {
            if (!super.canStart()) {
                return false;
            }
            int i = BrachytusEntity.this.getWorld().getTargets(VexEntity.class, this.closeVexPredicate, BrachytusEntity.this, BrachytusEntity.this.getBoundingBox().expand(16.0)).size();
            return BrachytusEntity.this.random.nextInt(8) + 1 > i;
        }

        @Override
        public int getSpellTicks() {
            return 100;
        }

        @Override
        public int startTimeDelay() {
            return 340;
        }

        @Override
        public void castSpell() {
            ServerWorld serverWorld = (ServerWorld)BrachytusEntity.this.getWorld();
            for (int i = 0; i < 3; ++i) {
                BlockPos blockPos = BrachytusEntity.this.getBlockPos().add(-2 + BrachytusEntity.this.random.nextInt(2), 1, -2 + BrachytusEntity.this.random.nextInt(2));
                VexEntity vexEntity = EntityType.VEX.create(BrachytusEntity.this.getWorld());
                if (vexEntity == null) continue;
                vexEntity.refreshPositionAndAngles(blockPos, 0.0f, 0.0f);
                vexEntity.initialize(serverWorld, BrachytusEntity.this.getWorld().getLocalDifficulty(blockPos), SpawnReason.MOB_SUMMONED, null, null);
                vexEntity.setOwner(BrachytusEntity.this);
                vexEntity.setBounds(blockPos);
                vexEntity.setLifeTicks(20 * (30 + BrachytusEntity.this.random.nextInt(90)));
                serverWorld.spawnEntityAndPassengers(vexEntity);
            }
        }

        @Override
        public SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON;
        }

        @Override
        public SpellcastingAnimalEntity.Spell getSpell() {
            return SpellcastingAnimalEntity.Spell.SUMMON_VEX;
        }
    }

    class ConjureFangsGoal
            extends SpellcastingAnimalEntity.CastSpellGoal {
        ConjureFangsGoal() {
            super ();
        }


        @Override
        public int getSpellTicks() {
            return 40;
        }

        @Override
        public int startTimeDelay() {
            return 100;
        }

        @Override
        public void castSpell() {
            LivingEntity livingEntity = BrachytusEntity.this.getTarget();
            double d = Math.min(livingEntity.getY(), BrachytusEntity.this.getY());
            double e = Math.max(livingEntity.getY(), BrachytusEntity.this.getY()) + 1.0;
            float f = (float)MathHelper.atan2(livingEntity.getZ() - BrachytusEntity.this.getZ(), livingEntity.getX() - BrachytusEntity.this.getX());
            if (BrachytusEntity.this.squaredDistanceTo(livingEntity) < 9.0) {
                float g;
                int i;
                for (i = 0; i < 5; ++i) {
                    g = f + (float)i * (float)Math.PI * 0.4f;
                    this.conjureFangs(BrachytusEntity.this.getX() + (double)MathHelper.cos(g) * 0.5, BrachytusEntity.this.getZ() + (double)MathHelper.sin(g) * 1.5, d, e, g, 0);
                }
                for (i = 0; i < 8; ++i) {
                    g = f + (float)i * (float)Math.PI * 2.0f / 8.0f + 1.2566371f;
                    this.conjureFangs(BrachytusEntity.this.getX() + (double)MathHelper.cos(g) * 1.5, BrachytusEntity.this.getZ() + (double)MathHelper.sin(g) * 2.5, d, e, g, 3);
                }
            } else {
                for (int i = 0; i < 16; ++i) {
                    double h = 1.25 * (double)(i + 1);
                    int j = 1 * i;
                    this.conjureFangs(BrachytusEntity.this.getX() + (double)MathHelper.cos(f) * h, BrachytusEntity.this.getZ() + (double)MathHelper.sin(f) * h, d, e, f, j);
                }
            }
        }

        private void conjureFangs(double x, double z, double maxY, double y, float yaw, int warmup) {
            BlockPos blockPos = BlockPos.ofFloored(x, y, z);
            boolean bl = false;
            double d = 0.0;
            do {
                BlockState blockState2;
                VoxelShape voxelShape;
                BlockPos blockPos2 = blockPos.down();
                BlockState blockState = BrachytusEntity.this.getWorld().getBlockState(blockPos2);
                if (!blockState.isSideSolidFullSquare(BrachytusEntity.this.getWorld(), blockPos2, Direction.UP)) continue;
                if (!BrachytusEntity.this.getWorld().isAir(blockPos) && !(voxelShape = (blockState2 = BrachytusEntity.this.getWorld().getBlockState(blockPos)).getCollisionShape(BrachytusEntity.this.getWorld(), blockPos)).isEmpty()) {
                    d = voxelShape.getMax(Direction.Axis.Y);
                }
                bl = true;
                break;
            } while ((blockPos = blockPos.down()).getY() >= MathHelper.floor(maxY) - 1);
            if (bl) {
                BrachytusEntity.this.getWorld().spawnEntity(new EvokerFangsEntity(BrachytusEntity.this.getWorld(), x, (double)blockPos.getY() + d, z, yaw, warmup, BrachytusEntity.this));
            }



        }





        @Override
        public SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_ATTACK;
        }

        @Override
        public SpellcastingAnimalEntity.Spell getSpell() {
            return SpellcastingAnimalEntity.Spell.FANGS;
        }
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

    @Override
    public SoundEvent getCastSpellSound() {
        return SoundEvents.ENTITY_EVOKER_CAST_SPELL;
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
}





