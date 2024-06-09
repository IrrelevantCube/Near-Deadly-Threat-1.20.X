package net.irrelevent.neardeadlythreat;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.datagen.ModWorldGenerator;
import net.irrelevent.neardeadlythreat.effect.ModEffects;
import net.irrelevent.neardeadlythreat.entity.ModEntities;
import net.irrelevent.neardeadlythreat.entity.custom.BrachytusEntity;
import net.irrelevent.neardeadlythreat.item.ModItemGroups;
import net.irrelevent.neardeadlythreat.item.ModItems;
import net.irrelevent.neardeadlythreat.world.gen.ModWorldGeneration;
import net.irrelevent.neardeadlythreat.world.tree.ModTrunkPlacerTypes;
import net.irrelevent.neardeadlythreat.world.tree.custom.YellowMerantiTrunkPlacer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearDeadlyThreat implements ModInitializer {
	public static final String MOD_ID = "ndt";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems ();
		ModItemGroups.registerItemGroups ();
		ModBlocks.registerModBlocks();

		ModEffects.registerEffects ();

		FuelRegistry.INSTANCE.add(ModItems.DOMINITE_ISOTOPE, 1200);

		StrippableBlockRegistry.register (ModBlocks.YELLOW_MERANTI_LOG, ModBlocks.STRIPPED_YELLOW_MERANTI_LOG);
		StrippableBlockRegistry.register (ModBlocks.YELLOW_MERANTI_WOOD, ModBlocks.STRIPPED_YELLOW_MERANTI_WOOD);

		FlammableBlockRegistry.getDefaultInstance ().add(ModBlocks.YELLOW_MERANTI_LOG, 10, 25);
		FlammableBlockRegistry.getDefaultInstance ().add(ModBlocks.YELLOW_MERANTI_WOOD, 10, 15);
		FlammableBlockRegistry.getDefaultInstance ().add(ModBlocks.YELLOW_MERANTI_LEAVES, 50, 80);
		FlammableBlockRegistry.getDefaultInstance ().add(ModBlocks.STRIPPED_YELLOW_MERANTI_LOG, 10, 25);
		FlammableBlockRegistry.getDefaultInstance ().add(ModBlocks.STRIPPED_YELLOW_MERANTI_WOOD, 10, 15);
		FlammableBlockRegistry.getDefaultInstance ().add(ModBlocks.YELLOW_MERANTI_PLANKS, 10, 25);

		FabricDefaultAttributeRegistry.register (ModEntities.BRACHYTUS, BrachytusEntity.createBrachytusAttributes ());
		FabricDefaultAttributeRegistry.register (ModEntities.BRACHYMITE, BrachytusEntity.createBrachytusAttributes ());

		ModTrunkPlacerTypes.register ();

		ModWorldGeneration.generatedModWorldGen ();

		LOGGER.info("Hello Fabric world!");
	}
}