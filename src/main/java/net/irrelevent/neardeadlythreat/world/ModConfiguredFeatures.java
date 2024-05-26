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
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> DOMINITE_ORE_KEY = registryKey ("dominite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CHRODOMACH_ORE_KEY = registryKey ("chrodomach_ore");


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
    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NearDeadlyThreat.MOD_ID, name));

    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register (key, new ConfiguredFeature<> (feature, configuration));
    }

}
