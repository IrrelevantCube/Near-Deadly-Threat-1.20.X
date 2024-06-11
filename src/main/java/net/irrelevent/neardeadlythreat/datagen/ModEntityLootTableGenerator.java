package net.irrelevent.neardeadlythreat.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.entity.ModEntities;
import net.irrelevent.neardeadlythreat.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.data.server.loottable.vanilla.VanillaEntityLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;


public class ModEntityLootTableGenerator extends VanillaEntityLootTableGenerator {


    public ModEntityLootTableGenerator(FabricDataOutput dataOutput) {
        super();
    }

    @Override
    public void generate(){
        this.register(ModEntities.BRACHYTUS, LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(ModItems.BRACHYTUS_SHELL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)))))));
        this.register(ModEntities.BRACHYMITE, LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(ModItems.CRAB_LEGS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)))))));

    }

}
