package net.irrelevent.neardeadlythreat;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.effect.ModEffects;
import net.irrelevent.neardeadlythreat.item.ModItemGroups;
import net.irrelevent.neardeadlythreat.item.ModItems;
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



		LOGGER.info("Hello Fabric world!");
	}
}