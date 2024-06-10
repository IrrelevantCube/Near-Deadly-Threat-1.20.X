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

public class ModOverworldRegions extends Region {
    public ModOverworldRegions(Identifier name, RegionType type, int weight) {
        super (name, type, weight);
    }


    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        super.addBiomes (registry, mapper);
        this.addModifiedVanillaOverworldBiomes (mapper, modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome (BiomeKeys.MANGROVE_SWAMP, ModBiomes.YELLOW_MERANTI_FOREST);
                });
        this.addModifiedVanillaOverworldBiomes (mapper, modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome (BiomeKeys.BEACH, ModBiomes.TRIDYMITE_BEACH);
                });
    }

}
