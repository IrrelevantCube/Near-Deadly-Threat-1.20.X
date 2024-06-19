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
    private static final MaterialRules.MaterialRule STONE = makeStateRule (Blocks.STONE);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water (-1, 0);
        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence (MaterialRules.condition (isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);
        MaterialRules.MaterialRule seaSurface = MaterialRules.sequence (MaterialRules.condition (isAtOrAboveWaterLevel, TRIDYMITE), DIRT);

        return MaterialRules.sequence (
                MaterialRules.sequence (MaterialRules.condition (MaterialRules.biome (ModBiomes.YELLOW_MERANTI_FOREST),
                                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, GRASS_BLOCK)),
                        MaterialRules.condition (MaterialRules.STONE_DEPTH_CEILING, STONE)),

                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, grassSurface),

                MaterialRules.sequence (MaterialRules.condition (MaterialRules.biome (ModBiomes.FRACTURED_YELLOW_MERANTI_FOREST),
                                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, GRASS_BLOCK)),
                        MaterialRules.condition (MaterialRules.STONE_DEPTH_CEILING, STONE)),

                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, grassSurface),

                MaterialRules.sequence (MaterialRules.condition (MaterialRules.biome (ModBiomes.TRIDYMITE_BEACH),
                                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, TRIDYMITE)),
                        MaterialRules.condition (MaterialRules.STONE_DEPTH_CEILING, SANDSTONE)),

                MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, grassSurface),

                MaterialRules.sequence (MaterialRules.condition (MaterialRules.biome (ModBiomes.DESOLATE_OCEAN),
                                MaterialRules.condition (MaterialRules.water (0, 128),
                                        MaterialRules.condition (MaterialRules.STONE_DEPTH_FLOOR, seaSurface))))

                );

    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block (block.getDefaultState ());
    }

}
