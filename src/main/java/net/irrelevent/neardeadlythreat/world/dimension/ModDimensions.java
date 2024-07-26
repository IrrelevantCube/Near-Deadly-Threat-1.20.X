package net.irrelevent.neardeadlythreat.world.dimension;

import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> TENEBRION_KEY = RegistryKey.of (RegistryKeys.DIMENSION,
            new Identifier (NearDeadlyThreat.MOD_ID, "tenebrion"));
    public static final RegistryKey<World> TENEBRION_LEVEL_KEY = RegistryKey.of (RegistryKeys.WORLD,
            new Identifier (NearDeadlyThreat.MOD_ID, "tenebrion"));
    public static final RegistryKey<DimensionType> TENEBRION_DIM_TYPE = RegistryKey.of (RegistryKeys.DIMENSION_TYPE,
            new Identifier (NearDeadlyThreat.MOD_ID, "tenebrion_dim_type"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register (TENEBRION_DIM_TYPE, new DimensionType (
                OptionalLong.of (12000),
                false,
                true,
                false,
                false,
                16.0,
                false,
                false,
                -128,
                256,
                256,
                BlockTags.INFINIBURN_END,
                DimensionTypes.OVERWORLD_ID,
                0.1f,
                new DimensionType.MonsterSettings (false, false, UniformIntProvider.create (0, 0), 0)));

    }
}
