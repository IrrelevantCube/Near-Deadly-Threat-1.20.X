package net.irrelevent.neardeadlythreat.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MISCLL = Registry.register (Registries.ITEM_GROUP, new Identifier (NearDeadlyThreat.MOD_ID, "miscll"),
            FabricItemGroup.builder ().displayName (Text.translatable ("itemgroup.miscll"))
                    .icon(() -> new ItemStack (ModItems.REFINED_DOMINITE)).entries ((displayContext, entries) -> {
                entries.add(ModItems.DOMINITE);
                entries.add(ModItems.DOMINITE_ISOTOPE);
                entries.add(ModItems.REFINED_DOMINITE);
                entries.add(ModItems.RAW_CHRODOMACH);
                entries.add(ModItems.REFINED_CHRODOMACH);
                entries.add(ModItems.MCLOCK);

                entries.add(ModItems.BRACHYTUS_SPAWN_EGG);
                entries.add(ModItems.BRACHYMITE_SPAWN_EGG);

                entries.add(ModBlocks.DOMINITE_ORE);
                entries.add(ModBlocks.TRIDYMITE);
                entries.add(ModBlocks.CHRODOMACH_ORE);
                entries.add(ModBlocks.DOMINITE_BLOCK);
                entries.add(ModBlocks.CHRODOMACH_BLOCK);

                entries.add(ModBlocks.YELLOW_MERANTI_LEAVES);
                entries.add(ModBlocks.YELLOW_MERANTI_LOG);
                entries.add(ModBlocks.YELLOW_MERANTI_PLANKS);
                entries.add(ModBlocks.YELLOW_MERANTI_WOOD);
                entries.add(ModBlocks.YELLOW_MERANTI_SAPLING);
                entries.add(ModBlocks.FRACTURED_YELLOW_MERANTI_SAPLING);

                entries.add(ModBlocks.YELLOW_MERANTI_DOOR);
                entries.add(ModBlocks.YELLOW_MERANTI_STAIRS);
                entries.add(ModBlocks.YELLOW_MERANTI_SLAB);
                entries.add(ModBlocks.YELLOW_MERANTI_FENCE);
                entries.add(ModBlocks.YELLOW_MERANTI_FENCE_GATE);
                entries.add(ModBlocks.YELLOW_MERANTI_BUTTON);
                entries.add(ModBlocks.YELLOW_MERANTI_PRESSURE_PLATE);

                entries.add(ModBlocks.STRIPPED_YELLOW_MERANTI_LOG);
                entries.add(ModBlocks.STRIPPED_YELLOW_MERANTI_WOOD);

                entries.add(ModItems.CRAB_LEGS);
                entries.add(ModItems.COOKED_CRAB_LEGS);
                entries.add(ModItems.TRIDYMITE_DUST);


    }).build ());

    public static void registerItemGroups() {
        NearDeadlyThreat.LOGGER.info("Registering Item Groups for " + NearDeadlyThreat.MOD_ID);
    }
}
