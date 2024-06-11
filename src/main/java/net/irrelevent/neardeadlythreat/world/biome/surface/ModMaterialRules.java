package net.irrelevent.neardeadlythreat.world.biome.surface;

import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule DIRT = makeStateRule (Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule (Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule DEEPSLATE = makeStateRule (Blocks.DEEPSLATE);
    private static final MaterialRules.MaterialRule STONE = makeStateRule (Blocks.STONE);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water (-1, 2);
        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence (MaterialRules.condition (isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return MaterialRules.sequence (
                MaterialRules.sequence (MaterialRules.condition (MaterialRules.biome (ModBiomes.YELLOW_MERANTI_FOREST),
                                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, DEEPSLATE)),
                        MaterialRules.condition (MaterialRules.STONE_DEPTH_CEILING, STONE)),

                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );

    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block (block.getDefaultState ());
    }

}
