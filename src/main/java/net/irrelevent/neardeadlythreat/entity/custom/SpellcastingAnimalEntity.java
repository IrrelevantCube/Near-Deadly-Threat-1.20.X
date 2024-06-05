package net.irrelevent.neardeadlythreat.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public class SpellcastingAnimalEntity extends AnimalEntity {

    public static final TrackedData<Byte> SPELL = DataTracker.registerData(SpellcastingAnimalEntity.class, TrackedDataHandlerRegistry.BYTE);
    public static Object CastSpellGoal;
    public int spellTicks;
    public SpellcastingAnimalEntity.Spell spell = SpellcastingAnimalEntity.Spell.NONE;
    public SpellcastingAnimalEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super (entityType, world);
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SPELL, (byte)0);
    }



    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.spellTicks = nbt.getInt("SpellTicks");
    }



    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("SpellTicks", this.spellTicks);
    }

    public boolean isSpellcasting() {
        if (this.getWorld().isClient) {
            return this.dataTracker.get(SPELL) > 0;
        }
        return this.spellTicks > 0;
    }

    public void setSpell(SpellcastingAnimalEntity.Spell spell) {
        this.spell = spell;
        this.dataTracker.set(SPELL, (byte)spell.id);
    }

    public SpellcastingAnimalEntity.Spell getSpell() {
        if (!this.getWorld().isClient) {
            return this.spell;
        }
        return SpellcastingAnimalEntity.Spell.byId(this.dataTracker.get(SPELL).byteValue());
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        if (this.spellTicks > 0) {
            --this.spellTicks;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient && this.isSpellcasting()) {
            SpellcastingAnimalEntity.Spell spell = this.getSpell();
            double d = spell.particleVelocity[0];
            double e = spell.particleVelocity[1];
            double f = spell.particleVelocity[2];
            float g = this.bodyYaw * ((float)Math.PI / 180) + MathHelper.cos((float)this.age * 0.6662f) * 0.25f;
            float h = MathHelper.cos(g);
            float i = MathHelper.sin(g);
            this.getWorld().addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + (double)h * 0.6, this.getY() + 1.8, this.getZ() + (double)i * 0.6, d, e, f);
            this.getWorld().addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() - (double)h * 0.6, this.getY() + 1.8, this.getZ() - (double)i * 0.6, d, e, f);
        }
    }

    public int getSpellTicks() {
        return this.spellTicks;
    }

    public SoundEvent getCastSpellSound() {
        return null;
    }

    public static enum Spell {
        NONE(0, 0.0, 0.0, 0.0),
        SUMMON_VEX(1, 0.7, 0.7, 0.8),
        FANGS(2, 0.4, 0.3, 0.35),
        DISAPPEAR(3, 0.3, 0.3, 0.8),
        BLINDNESS(4, 0.1, 0.1, 0.2);

        private static final IntFunction<Spell> BY_ID;
        final int id;
        final double[] particleVelocity;

        private Spell(int id, double particleVelocityX, double particleVelocityY, double particleVelocityZ) {
            this.id = id;
            this.particleVelocity = new double[]{particleVelocityX, particleVelocityY, particleVelocityZ};
        }

        public static SpellcastingAnimalEntity.Spell byId(int id) {
            return BY_ID.apply(id);
        }

        static {
            BY_ID = ValueLists.createIdToValueFunction((ToIntFunction<Spell>) spell -> spell.id, SpellcastingAnimalEntity.Spell.values(), ValueLists.OutOfBoundsHandling.ZERO);
        }
    }

    public abstract class CastSpellGoal
            extends Goal {
        public int spellCooldown;
        public int startTime;

        public CastSpellGoal (){
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = SpellcastingAnimalEntity.this.getTarget();
            if (livingEntity == null || !livingEntity.isAlive()) {
                return false;
            }
            if (SpellcastingAnimalEntity.this.isSpellcasting()) {
                return false;
            }
            return SpellcastingAnimalEntity.this.age >= this.startTime;
        }

        @Override
        public boolean shouldContinue() {
            LivingEntity livingEntity = SpellcastingAnimalEntity.this.getTarget();
            return livingEntity != null && livingEntity.isAlive() && this.spellCooldown > 0;
        }

        @Override
        public void start() {
            this.spellCooldown = this.getTickCount(this.getInitialCooldown());
            SpellcastingAnimalEntity.this.spellTicks = this.getSpellTicks();
            this.startTime = SpellcastingAnimalEntity.this.age + this.startTimeDelay();
            SoundEvent soundEvent = this.getSoundPrepare();
            if (soundEvent != null) {
                SpellcastingAnimalEntity.this.playSound(soundEvent, 1.0f, 1.0f);
            }
            SpellcastingAnimalEntity.this.setSpell(this.getSpell());
        }

        @Override
        public void tick() {
            --this.spellCooldown;
            if (this.spellCooldown == 0) {
                this.castSpell();
                SpellcastingAnimalEntity.this.playSound(SpellcastingAnimalEntity.this.getCastSpellSound(), 1.0f, 1.0f);
            }
        }

        public abstract void castSpell();

        public int getInitialCooldown() {
            return 20;
        }

        public abstract int getSpellTicks();

        public abstract int startTimeDelay();

        @Nullable
        public abstract SoundEvent getSoundPrepare();

        public abstract SpellcastingAnimalEntity.Spell getSpell();
    }

    public class LookAtTargetGoal
            extends Goal {
        public LookAtTargetGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            return SpellcastingAnimalEntity.this.getSpellTicks() > 0;
        }

        @Override
        public void start() {
            super.start();
            SpellcastingAnimalEntity.this.navigation.stop();
        }

        @Override
        public void stop() {
            super.stop();
            SpellcastingAnimalEntity.this.setSpell(SpellcastingAnimalEntity.Spell.NONE);
        }


    }
}

