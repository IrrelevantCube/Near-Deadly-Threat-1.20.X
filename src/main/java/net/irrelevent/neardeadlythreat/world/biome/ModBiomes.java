package net.irrelevent.neardeadlythreat.world.biome;


import com.mojang.datafixers.kinds.IdF;
import com.mojang.datafixers.util.Either;
import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.entity.ModEntities;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryOwner;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ModBiomes {
    @Nullable
    private static final MusicSound DEFAULT_MUSIC = null;
    public static final RegistryKey<Biome> YELLOW_MERANTI_FOREST = RegistryKey.of (RegistryKeys.BIOME,
            new Identifier (NearDeadlyThreat.MOD_ID, "yellow_meranti_forest"));
    public static final RegistryKey<Biome> TRIDYMITE_BEACH = RegistryKey.of (RegistryKeys.BIOME,
            new Identifier (NearDeadlyThreat.MOD_ID, "tridymite_beach"));

    public static void bootstrap(Registerable<Biome> context) {
        context.register (YELLOW_MERANTI_FOREST, yellowMerantiForest (context));
        context.register (TRIDYMITE_BEACH, tridymiteBeach (context));
    }



    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addAmethystGeodes (builder);
        DefaultBiomeFeatures.addLandCarvers (builder);
        DefaultBiomeFeatures.addDungeons (builder);
        DefaultBiomeFeatures.addMineables (builder);
        DefaultBiomeFeatures.addSprings (builder);
        DefaultBiomeFeatures.addFossils (builder);

    }

    public static Biome yellowMerantiForest(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder ();

        spawnBuilder.spawn (SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry (EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals (spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters (spawnBuilder);
        DefaultBiomeFeatures.addJungleMobs (spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder (context.getRegistryLookup (RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup (RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration (biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks (biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres (biomeBuilder);
        DefaultBiomeFeatures.addJungleGrass (biomeBuilder);
        DefaultBiomeFeatures.addMangroveSwampFeatures (biomeBuilder);


        biomeBuilder.feature (GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers (biomeBuilder);
        DefaultBiomeFeatures.addVines (biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns (biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms (biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation (biomeBuilder);
        MusicSound musicSound = MusicType.createIngameMusic (SoundEvents.AMBIENT_NETHER_WASTES_MOOD);
        {


            return new Biome.Builder ()
                    .precipitation (true)
                    .downfall (0.9f)
                    .temperature (0.3f)
                    .generationSettings (biomeBuilder.build ())
                    .spawnSettings (spawnBuilder.build ())
                    .effects ((new BiomeEffects.Builder ())
                            .waterColor (0x7f8e72)
                            .waterFogColor (0x0c343d)
                            .skyColor (0x58634f)
                            .grassColor (0x78a27f)
                            .foliageColor (0x718307)
                            .fogColor (0x75b982)
                            .music (musicSound).build ())


                    .build ();


        }

    }
    public static Biome tridymiteBeach(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder ();

        spawnBuilder.spawn (SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry (ModEntities.BRACHYTUS, 1, 1, 2));
        spawnBuilder.spawn (SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry (ModEntities.BRACHYMITE, 2, 2, 4));


        DefaultBiomeFeatures.addBatsAndMonsters (spawnBuilder);
        DefaultBiomeFeatures.addDesertMobs (spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder (context.getRegistryLookup (RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup (RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration (biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks (biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres (biomeBuilder);
        DefaultBiomeFeatures.addSprings (biomeBuilder);

        DefaultBiomeFeatures.addDefaultVegetation (biomeBuilder);
        MusicSound musicSound = MusicType.createIngameMusic (SoundEvents.MUSIC_OVERWORLD_DESERT);
        {


            return new Biome.Builder ()
                    .precipitation (true)
                    .downfall (0.2f)
                    .temperature (1.2f)
                    .generationSettings (biomeBuilder.build ())
                    .spawnSettings (spawnBuilder.build ())
                    .effects ((new BiomeEffects.Builder ())
                            .waterColor (0xb2d1ff)
                            .waterFogColor (0x6a7d99)
                            .skyColor (0x66a3ff)
                            .grassColor (0x78a27f)
                            .foliageColor (0x718307)
                            .fogColor (0xa0bce5)
                            .music (musicSound).build ())


                    .build ();


        }

    }
}
