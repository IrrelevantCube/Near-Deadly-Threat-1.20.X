package net.irrelevent.neardeadlythreat.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.irrelevent.neardeadlythreat.world.ModPlacedFeatures;
import net.irrelevent.neardeadlythreat.world.biome.ModBiomes;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature (BiomeSelectors.includeByKey (ModBiomes.YELLOW_MERANTI_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.YELLOW_MERANTI_PLACED_KEY);
    }

}
