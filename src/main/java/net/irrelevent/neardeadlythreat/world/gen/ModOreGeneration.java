package net.irrelevent.neardeadlythreat.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.irrelevent.neardeadlythreat.world.ModPlacedFeatures;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generatedOres() {
        BiomeModifications.addFeature (BiomeSelectors.foundInOverworld (),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.DOMINITE_ORE_PLACED_KEY);
        BiomeModifications.addFeature (BiomeSelectors.foundInOverworld (),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.CHRODOMACH_ORE_PLACED_KEY);
        BiomeModifications.addFeature (BiomeSelectors.foundInOverworld (),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ALUMINUM_ORE_PLACED_KEY);
    }
}
