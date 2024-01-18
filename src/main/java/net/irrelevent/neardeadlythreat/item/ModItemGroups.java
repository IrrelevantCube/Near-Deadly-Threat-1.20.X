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
                entries.add(ModItems.REFINED_DOMINITE);
                entries.add(ModItems.RAW_CHRODOMACH);
                entries.add(ModItems.REFINED_CHRODOMACH);

                entries.add(ModBlocks.DOMINITE_ORE);
                entries.add(ModBlocks.DOMINITE_BLOCK);
                entries.add(ModBlocks.CHRODOMACH_BLOCK);

    }).build ());

    public static void registerItemGroups() {
        NearDeadlyThreat.LOGGER.info("Registering Item Groups for " + NearDeadlyThreat.MOD_ID);
    }
}
