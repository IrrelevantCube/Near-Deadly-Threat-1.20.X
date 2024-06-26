package net.irrelevent.neardeadlythreat.entity.custom;

import net.irrelevent.neardeadlythreat.entity.ModEntities;
import net.irrelevent.neardeadlythreat.entity.ai.BrachymiteAttackGoal;
import net.irrelevent.neardeadlythreat.entity.ai.BrachytusAttackGoal;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

public class BrachytusEntity extends SpellcastingAnimalEntity {
    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData (BrachytusEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState ();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState ();
    public int attackAnimationTimeout = 0;

    public final SwimNavigation waterNavigation;
    public final MobNavigation landNavigation;
    protected void tickWaterBreathingAir(int air) {
            this.setAir(300);
    }
    public void baseTick() {
        int i = this.getAir();
        super.baseTick();
        this.tickWaterBreathingAir(i);
    }
    public boolean isPushedByFluids() {
        return false;
    }

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
        this.experiencePoints = 15;
        this.waterNavigation = new SwimNavigation(this, world);
        this.landNavigation = new MobNavigation(this, world);
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new WanderAroundOnSurfaceGoal (this, 1.0));
        this.goalSelector.add (1, new StopAndLookAtEntityGoal (this, MobEntity.class, 8.0f));
        this.goalSelector.add (2, new LookAroundGoal (this));
        this.goalSelector.add (3, new MeleeAttackGoal (this, 1, true));
        this.goalSelector.add (5, new BrachytusAttackGoal (this, 1d, true));
        this.goalSelector.add (4, new PounceAtTargetGoal (this, 0.7f));
        this.goalSelector.add(5, new WanderAroundOnSurfaceGoal.LeaveWaterGoal (this, 1.0));
        this.goalSelector.add(6, new WanderAroundOnSurfaceGoal.TargetAboveWaterGoal (this, 1.0, this.getWorld().getSeaLevel()));
        this.goalSelector.add(7, new WanderAroundGoal(this, 1.0));
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
    private static class WanderAroundOnSurfaceGoal extends Goal {
        private final PathAwareEntity mob;
        private double x;
        private double y;
        private double z;
        private final double speed;
        private final World world;

        public WanderAroundOnSurfaceGoal(PathAwareEntity mob, double speed) {
            this.mob = mob;
            this.speed = speed;
            this.world = mob.getWorld();
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            if (!this.world.isDay()) {
                return false;
            } else if (this.mob.isTouchingWater()) {
                return false;
            } else {
                Vec3d vec3d = this.getWanderTarget();
                if (vec3d == null) {
                    return false;
                } else {
                    this.x = vec3d.x;
                    this.y = vec3d.y;
                    this.z = vec3d.z;
                    return true;
                }
            }
        }
        
        private static class LeaveWaterGoal extends MoveToTargetPosGoal {
            private final BrachytusEntity brachytus;

            public LeaveWaterGoal(BrachytusEntity brachytus, double speed) {
                super(brachytus, speed, 8, 20);
                this.brachytus = brachytus;
            }

            public boolean canStart() {
                return super.canStart() && !this.brachytus.getWorld().isDay() && this.brachytus.isTouchingWater() && this.brachytus.getY() >= (double)(this.brachytus.getWorld().getSeaLevel() - 3);
            }
            

            public boolean shouldContinue() {
                return super.shouldContinue();
            }

            protected boolean isTargetPos(WorldView world, BlockPos pos) {
                BlockPos blockPos = pos.up();
                return world.isAir(blockPos) && world.isAir(blockPos.up()) ? world.getBlockState(pos).hasSolidTopSurface(world, pos, this.brachytus) : false;
            }

            public void start() {
                this.brachytus.setTargetingUnderwater(false);
                this.brachytus.navigation = this.brachytus.landNavigation;
                super.start();
            }

            public void stop() {
                super.stop();
            }
        }
        private static class TargetAboveWaterGoal extends Goal {
            private final BrachytusEntity brachytus;
            private final double speed;
            private final int minY;
            private boolean foundTarget;
            private boolean targetingUnderwater;

            public TargetAboveWaterGoal(BrachytusEntity brachytus, double speed, int minY) {
                this.brachytus = brachytus;
                this.speed = speed;
                this.minY = minY;
            }
            

            public boolean canStart() {
                return !this.brachytus.getWorld().isDay() && this.brachytus.isTouchingWater() && this.brachytus.getY() < (double)(this.minY - 2);
            }

            public boolean shouldContinue() {
                return this.canStart() && !this.foundTarget;
            }

            public void tick() {
                if (this.brachytus.getY() < (double)(this.minY - 1) && (this.brachytus.getNavigation().isIdle() || this.brachytus.hasFinishedCurrentPath())) {
                    Vec3d vec3d = NoPenaltyTargeting.findTo(this.brachytus, 4, 8, new Vec3d(this.brachytus.getX(), (double)(this.minY - 1), this.brachytus.getZ()), 1.5707963705062866);
                    if (vec3d == null) {
                        this.foundTarget = true;
                        return;
                    }

                    this.brachytus.getNavigation().startMovingTo(vec3d.x, vec3d.y, vec3d.z, this.speed);
                }
                

            }
            
            public void setTargetingUnderwater(boolean targetingUnderwater) {
                this.targetingUnderwater = targetingUnderwater;
            }


            public void start() {
                this.brachytus.setTargetingUnderwater(true);
                this.foundTarget = false;
            }

            public void stop() {
                this.brachytus.setTargetingUnderwater(false);
            }

        }


        public boolean shouldContinue() {
            return !this.mob.getNavigation().isIdle();
        }

        public void start() {
            this.mob.getNavigation().startMovingTo(this.x, this.y, this.z, this.speed);
        }

        @Nullable
        private Vec3d getWanderTarget() {
            Random random = this.mob.getRandom();
            BlockPos blockPos = this.mob.getBlockPos();

            for(int i = 0; i < 10; ++i) {
                BlockPos blockPos2 = blockPos.add(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);
                if (this.world.getBlockState(blockPos2).isOf(Blocks.WATER)) {
                    return Vec3d.ofBottomCenter(blockPos2);
                }
            }

            return null;
        }
    }
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement() && this.isTouchingWater() && this.isTargetingUnderwater()) {
            this.updateVelocity(0.5F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
        } else {
            super.travel(movementInput);
        }

    }

    public void updateSwimming() {
        if (!this.getWorld().isClient) {
            if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
            } else {
                this.navigation = this.landNavigation;
                this.setSwimming(false);
            }
        }

    }

    private boolean isTargetingUnderwater() {
        return false;
    }

    private void setTargetingUnderwater(boolean b) {
        
    }
    
    

    private boolean hasFinishedCurrentPath() {
        Path path = this.getNavigation().getCurrentPath();
        if (path != null) {
            BlockPos blockPos = path.getTarget();
            if (blockPos != null) {
                double d = this.squaredDistanceTo((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
                if (d < 4.0) {
                    return true;
                }
            }
        }

        return false;
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
            int i = BrachytusEntity.this.getWorld().getTargets(BrachymiteEntity.class, this.closeVexPredicate, BrachytusEntity.this, BrachytusEntity.this.getBoundingBox().expand(16.0)).size();
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
                BrachymiteEntity brachymiteEntity = ModEntities.BRACHYMITE.create(BrachytusEntity.this.getWorld());
                if (brachymiteEntity == null) continue;
                brachymiteEntity.refreshPositionAndAngles(blockPos, 0.0f, 0.0f);
                brachymiteEntity.initialize(serverWorld, BrachytusEntity.this.getWorld().getLocalDifficulty(blockPos), SpawnReason.MOB_SUMMONED, null, null);
                brachymiteEntity.setOwner(BrachytusEntity.this);
                brachymiteEntity.setBounds(blockPos);
                brachymiteEntity.setLifeTicks(20 * (30 + BrachytusEntity.this.random.nextInt(90)));
                serverWorld.spawnEntityAndPassengers(brachymiteEntity);
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
            if (BrachytusEntity.this.squaredDistanceTo(livingEntity) < 10.0) {
                float g;
                int i;
                for (i = 0; i < 5; ++i) {
                    g = f + (float)i * (float)Math.PI * 0.4f;
                    this.conjureFangs(BrachytusEntity.this.getX() + (double)MathHelper.cos(g) * 0.5, BrachytusEntity.this.getZ() + (double)MathHelper.sin(g) * 1.5, d, e, g, 0);
                }
                for (i = 0; i < 8; ++i) {
                    g = f + (float)i * (float)Math.PI * 2.0f / 8.0f + 2.2566371f;
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
                BrachytusEntity.this.getWorld().spawnEntity(new EvokerFangsEntity (BrachytusEntity.this.getWorld(), x, (double)blockPos.getY() + d, z, yaw, warmup, BrachytusEntity.this));
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





