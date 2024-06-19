package net.irrelevent.neardeadlythreat.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.entity.custom.BrachymiteEntity;
import net.irrelevent.neardeadlythreat.entity.custom.BrachytusEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
        public static final EntityType<BrachytusEntity>  BRACHYTUS = Registry.register (Registries.ENTITY_TYPE,
                new Identifier (NearDeadlyThreat.MOD_ID, "brachytus"),
                FabricEntityTypeBuilder.create (SpawnGroup.MONSTER, BrachytusEntity::new).dimensions (EntityDimensions.fixed (3f, 2f)).build ());
        public static final EntityType<BrachymiteEntity>  BRACHYMITE = Registry.register (Registries.ENTITY_TYPE,
                new Identifier (NearDeadlyThreat.MOD_ID, "brachymite"),
                FabricEntityTypeBuilder.create (SpawnGroup.MONSTER, BrachymiteEntity::new).dimensions (EntityDimensions.fixed (1f, 0.7f)).build ());

}
