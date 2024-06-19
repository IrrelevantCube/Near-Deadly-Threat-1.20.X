package net.irrelevent.neardeadlythreat.world.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(Identifier name, int weight) {
        super (name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube,
                    RegistryKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome(BiomeKeys.SPARSE_JUNGLE, ModBiomes.YELLOW_MERANTI_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(BiomeKeys.BEACH, ModBiomes.TRIDYMITE_BEACH);
            modifiedVanillaOverworldBuilder.replaceBiome (BiomeKeys.JUNGLE, ModBiomes.FRACTURED_YELLOW_MERANTI_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome (BiomeKeys.DEEP_COLD_OCEAN, ModBiomes.DESOLATE_OCEAN);
        });
    }
}
