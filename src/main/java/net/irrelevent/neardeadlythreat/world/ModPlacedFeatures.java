package net.irrelevent.neardeadlythreat.world;

import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> DOMINITE_ORE_PLACED_KEY = registryKey ("dominite_ore_placed");
    public static final RegistryKey<PlacedFeature> CHRODOMACH_ORE_PLACED_KEY = registryKey ("chrodomach_ore_placed");
    public static final RegistryKey<PlacedFeature> YELLOW_MERANTI_PLACED_KEY = registryKey ("yellow_meranti_placed");
    public static final RegistryKey<PlacedFeature> FRACTURED_YELLOW_MERANTI_PLACED_KEY = registryKey ("fractured_yellow_meranti_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup (RegistryKeys.CONFIGURED_FEATURE);

        register(context, DOMINITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow (ModConfiguredFeatures.DOMINITE_ORE_KEY),
                ModOrePlacementClass.modifiersWithCount (2,
                        HeightRangePlacementModifier.trapezoid (YOffset.fixed (-60), YOffset.fixed (20))));
        register(context, CHRODOMACH_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow (ModConfiguredFeatures.CHRODOMACH_ORE_KEY),
                ModOrePlacementClass.modifiersWithCount (1,
                        HeightRangePlacementModifier.trapezoid (YOffset.fixed (-64), YOffset.fixed (-50))));
        register (context, YELLOW_MERANTI_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow (ModConfiguredFeatures.YELLOW_MERANTI_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive (PlacedFeatures.createCountExtraModifier (3, 0.2f, 4),
                        ModBlocks.YELLOW_MERANTI_SAPLING));
        register (context, FRACTURED_YELLOW_MERANTI_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow (ModConfiguredFeatures.FRACTURED_YELLOW_MERANTI_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive (PlacedFeatures.createCountExtraModifier (3, 0.2f, 4),
                        ModBlocks.FRACTURED_YELLOW_MERANTI_SAPLING));
    }

    public static RegistryKey<PlacedFeature> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier (NearDeadlyThreat.MOD_ID, name));

    }

    private static void register (Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                  List<PlacementModifier> modifiers) {
        context.register (key, new PlacedFeature (configuration, List.copyOf (modifiers)));
    }

}
