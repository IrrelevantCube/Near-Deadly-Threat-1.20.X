package net.irrelevent.neardeadlythreat.world.biome.surface;

import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules1 {
    private static final MaterialRules.MaterialRule TRIDYMITE = makeStateRule (ModBlocks.TRIDYMITE);
    private static final MaterialRules.MaterialRule STONE = makeStateRule (Blocks.STONE);
    private static final MaterialRules.MaterialRule SANDSTONE = makeStateRule (Blocks.SANDSTONE);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water (-1, 0);
        MaterialRules.MaterialRule tridymiteSurface = MaterialRules.sequence (MaterialRules.condition (isAtOrAboveWaterLevel, TRIDYMITE), SANDSTONE);

        return MaterialRules.sequence (

                MaterialRules.sequence (MaterialRules.condition (MaterialRules.biome (ModBiomes.TRIDYMITE_BEACH),
                                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, TRIDYMITE)),
                        MaterialRules.condition (MaterialRules.STONE_DEPTH_CEILING, STONE)),

                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, tridymiteSurface)
        );

    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block (block.getDefaultState ());
    }

}
