package net.irrelevent.neardeadlythreat.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;


public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public static final float[] CHRODOMACH_DROP_CHANCE = new float[]{0.005f, 0.005111132f, 0.004999989f, 0.004888998f};

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate(){
        addDrop (ModBlocks.CHRODOMACH_BLOCK);
        addDrop (ModBlocks.DOMINITE_BLOCK);

        addDrop (ModBlocks.YELLOW_MERANTI_LOG);
        addDrop (ModBlocks.YELLOW_MERANTI_WOOD);
        addDrop (ModBlocks.STRIPPED_YELLOW_MERANTI_LOG);
        addDrop (ModBlocks.STRIPPED_YELLOW_MERANTI_WOOD);


        addDrop (ModBlocks.YELLOW_MERANTI_STAIRS);
        addDrop (ModBlocks.YELLOW_MERANTI_FENCE);
        addDrop (ModBlocks.YELLOW_MERANTI_FENCE_GATE);
        addDrop (ModBlocks.YELLOW_MERANTI_BUTTON);
        addDrop (ModBlocks.YELLOW_MERANTI_PRESSURE_PLATE);
        addDrop (ModBlocks.YELLOW_MERANTI_TRAPDOOR);
        addDrop (ModBlocks.YELLOW_MERANTI_SAPLING);

        addDrop (ModBlocks.YELLOW_MERANTI_DOOR, doorDrops (ModBlocks.YELLOW_MERANTI_DOOR));
        addDrop (ModBlocks.YELLOW_MERANTI_SLAB, slabDrops (ModBlocks.YELLOW_MERANTI_SLAB));

        addDrop (ModBlocks.YELLOW_MERANTI_PLANKS);

        addDrop(ModBlocks.YELLOW_MERANTI_LEAVES, leavesDrops (ModBlocks.YELLOW_MERANTI_LEAVES, ModBlocks.YELLOW_MERANTI_SAPLING, 0.005f));

        addDrop (ModBlocks.DOMINITE_ORE, copperLikeOreDrops(ModBlocks.DOMINITE_ORE, ModItems.DOMINITE));
        addDrop (ModBlocks.CHRODOMACH_ORE, copperLikeOreDrops(ModBlocks.CHRODOMACH_ORE, ModItems.RAW_CHRODOMACH));
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item, float ... chance) {
        ((LeafEntry.Builder)ItemEntry.builder(ModItems.RAW_CHRODOMACH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE).conditionally ((TableBonusLootCondition.builder(Enchantments.FORTUNE, CHRODOMACH_DROP_CHANCE))));
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop, ((LeafEntry.Builder)ItemEntry.builder(item).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
