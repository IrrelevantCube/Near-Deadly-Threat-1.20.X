package net.irrelevent.neardeadlythreat.world.biome.surface;

import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import terrablender.api.SurfaceRuleManager;

public class ModMaterialRules {

    private static final MaterialRules.MaterialRule TRIDYMITE = makeStateRule (ModBlocks.TRIDYMITE);
    private static final MaterialRules.MaterialRule SANDSTONE = makeStateRule (Blocks.SANDSTONE);
    private static final MaterialRules.MaterialRule DIRT = makeStateRule (Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule (Blocks.GRASS_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water (-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence (MaterialRules.condition (isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return MaterialRules.sequence (
                MaterialRules.sequence (MaterialRules.condition (MaterialRules.biome (ModBiomes.TRIDYMITE_BEACH),
                                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, TRIDYMITE)),
                        MaterialRules.condition (MaterialRules.STONE_DEPTH_CEILING, SANDSTONE)),

                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, grassSurface)

                );

    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block (block.getDefaultState ());
    }

}
