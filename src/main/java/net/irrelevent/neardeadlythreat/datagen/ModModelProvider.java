package net.irrelevent.neardeadlythreat.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super (output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll (ModBlocks.DOMINITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll (ModBlocks.DOMINITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll (ModBlocks.CHRODOMACH_BLOCK);

        blockStateModelGenerator.registerLog(ModBlocks.YELLOW_MERANTI_LOG).log(ModBlocks.YELLOW_MERANTI_LOG).wood(ModBlocks.YELLOW_MERANTI_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_YELLOW_MERANTI_LOG).log(ModBlocks.STRIPPED_YELLOW_MERANTI_LOG).wood(ModBlocks.STRIPPED_YELLOW_MERANTI_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll (ModBlocks.YELLOW_MERANTI_LEAVES);
        BlockStateModelGenerator.BlockTexturePool yellowMerantiPool = blockStateModelGenerator.registerCubeAllModelTexturePool (ModBlocks.YELLOW_MERANTI_PLANKS);

        yellowMerantiPool.stairs (ModBlocks.YELLOW_MERANTI_STAIRS);
        yellowMerantiPool.fence (ModBlocks.YELLOW_MERANTI_FENCE);
        yellowMerantiPool.fenceGate (ModBlocks.YELLOW_MERANTI_FENCE_GATE);
        yellowMerantiPool.pressurePlate (ModBlocks.YELLOW_MERANTI_PRESSURE_PLATE);
        yellowMerantiPool.slab (ModBlocks.YELLOW_MERANTI_SLAB);
        yellowMerantiPool.button (ModBlocks.YELLOW_MERANTI_BUTTON);

        blockStateModelGenerator.registerDoor (ModBlocks.YELLOW_MERANTI_DOOR);
        blockStateModelGenerator.registerTrapdoor (ModBlocks.YELLOW_MERANTI_TRAPDOOR);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register (ModItems.DOMINITE, Models.GENERATED);
        itemModelGenerator.register (ModItems.REFINED_DOMINITE, Models.GENERATED);
        itemModelGenerator.register (ModItems.CRAB_LEGS, Models.GENERATED);
        itemModelGenerator.register (ModItems.COOKED_CRAB_LEGS, Models.GENERATED);
        itemModelGenerator.register (ModItems.RAW_CHRODOMACH, Models.GENERATED);
        itemModelGenerator.register (ModItems.REFINED_CHRODOMACH, Models.GENERATED);
        itemModelGenerator.register (ModItems.DOMINITE_ISOTOPE, Models.GENERATED);

    }
}
