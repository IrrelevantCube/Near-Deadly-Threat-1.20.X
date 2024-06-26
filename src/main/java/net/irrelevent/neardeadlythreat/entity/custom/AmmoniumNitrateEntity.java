package net.irrelevent.neardeadlythreat.entity.custom;

import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

public class AmmoniumNitrateEntity extends Entity implements Ownable {

    private static final TrackedData<Integer> FUSE;
    private static final TrackedData<BlockState> BLOCK_STATE;
    private static final int DEFAULT_FUSE = 180;
    private static final String BLOCK_STATE_NBT_KEY = "block_state";
    public static final String FUSE_NBT_KEY = "fuse";
    @Nullable
    private LivingEntity causingEntity;

    public AmmoniumNitrateEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
    }

    public AmmoniumNitrateEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(EntityType.TNT, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * 1.2831854820251465;
        this.setVelocity(-Math.sin(d) * 0.02, 0.20000000298023224, -Math.cos(d) * 0.02);
        this.setFuse(180);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }



    protected void initDataTracker() {
        this.dataTracker.startTracking(FUSE, 180);
        this.dataTracker.startTracking(BLOCK_STATE, ModBlocks.AMMONIUM_NITRATE.getDefaultState());
    }

    protected MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    public boolean canHit() {
        return !this.isRemoved();
    }

    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.02, 0.0));
        }

        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.CRIT, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 2.0, 0.0);
            }
        }

    }


    private void explode() {
        float f = 26.0F;
        this.getWorld().createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), 26.0F, World.ExplosionSourceType.TNT);
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putShort("fuse", (short)this.getFuse());
        nbt.put("block_state", NbtHelper.fromBlockState(this.getBlockState()));
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.setFuse(nbt.getShort("fuse"));
        if (nbt.contains("block_state", 10)) {
            this.setBlockState(NbtHelper.toBlockState(this.getWorld().createCommandRegistryWrapper(RegistryKeys.BLOCK), nbt.getCompound("block_state")));
        }

    }

    @Nullable
    public LivingEntity getOwner() {
        return this.causingEntity;
    }

    public void copyFrom(Entity original) {
        super.copyFrom(original);
        if (original instanceof AmmoniumNitrateEntity ammoniumNitrateEntity) {
            this.causingEntity = ammoniumNitrateEntity.causingEntity;
        }

    }

    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.15F;
    }

    public void setFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
    }

    public int getFuse() {
        return (Integer)this.dataTracker.get(FUSE);
    }

    public void setBlockState(BlockState state) {
        this.dataTracker.set(BLOCK_STATE, state);
    }

    public BlockState getBlockState() {
        return (BlockState)this.dataTracker.get(BLOCK_STATE);
    }

    static {
        FUSE = DataTracker.registerData(AmmoniumNitrateEntity.class, TrackedDataHandlerRegistry.INTEGER);
        BLOCK_STATE = DataTracker.registerData(AmmoniumNitrateEntity.class, TrackedDataHandlerRegistry.BLOCK_STATE);
    }
}
