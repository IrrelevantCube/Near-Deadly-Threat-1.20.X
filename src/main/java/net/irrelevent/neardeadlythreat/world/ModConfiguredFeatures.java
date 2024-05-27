package net.irrelevent.neardeadlythreat.world;

import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;


import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> DOMINITE_ORE_KEY = registryKey ("dominite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CHRODOMACH_ORE_KEY = registryKey ("chrodomach_ore");


    public static final RegistryKey<ConfiguredFeature<?, ?>> YELLOW_MERANTI_KEY = registryKey("yellow_meranti_key");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest (BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest (BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldDominiteOres =
                List.of(OreFeatureConfig.createTarget (stoneReplacables, ModBlocks.DOMINITE_ORE.getDefaultState ()),
                    OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DOMINITE_ORE.getDefaultState ()));
        List<OreFeatureConfig.Target> overworldChrodomachOres =
                List.of(OreFeatureConfig.createTarget (stoneReplacables, ModBlocks.CHRODOMACH_ORE.getDefaultState ()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.CHRODOMACH_ORE.getDefaultState ()));


        register (context, DOMINITE_ORE_KEY, Feature.ORE, new OreFeatureConfig (overworldDominiteOres, 3));
        register (context, CHRODOMACH_ORE_KEY, Feature.ORE, new OreFeatureConfig (overworldDominiteOres, 1));

        register (context, YELLOW_MERANTI_KEY, Feature.TREE, new TreeFeatureConfig.Builder (
                BlockStateProvider.of (ModBlocks.YELLOW_MERANTI_LOG),
                new GiantTrunkPlacer (5, 4, 3),
                BlockStateProvider.of (ModBlocks.YELLOW_MERANTI_LEAVES),
                new BlobFoliagePlacer (ConstantIntProvider.create(6), ConstantIntProvider.create (4), 3),
                new ThreeLayersFeatureSize (2, 0, 3, 3, 6, OptionalInt.of (3))).build ());

    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NearDeadlyThreat.MOD_ID, name));

    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register (key, new ConfiguredFeature<> (feature, configuration));
    }

}
