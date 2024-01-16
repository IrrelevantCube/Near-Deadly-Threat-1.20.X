package net.irrelevent.neardeadlythreat;

import net.fabricmc.api.ModInitializer;

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

		LOGGER.info("Hello Fabric world!");
	}
}