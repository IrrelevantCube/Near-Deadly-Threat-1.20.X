package net.irrelevent.neardeadlythreat.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> DOMINITE_SMELTABLES = List.of(ModItems.DOMINITE);
    private static final List<ItemConvertible> RAW_ALUMINUM_SMELTABLES = List.of(ModItems.RAW_ALUMINUM);
    private static final List<ItemConvertible> RAW_ALUMINUM_BLOCK_SMELTABLES = List.of(ModBlocks.RAW_ALUMINUM_BLOCK);

    public ModRecipeProvider(FabricDataOutput output) {
        super (output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting (exporter, DOMINITE_SMELTABLES, RecipeCategory.MISC, ModItems.DOMINITE,
                0.7f, 500000, "dominite");
        offerBlasting (exporter, DOMINITE_SMELTABLES, RecipeCategory.MISC, ModItems.DOMINITE,
                0.75f, 450000, "dominite");

        offerSmelting (exporter, RAW_ALUMINUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINUM_INGOT,
                0.5f, 35000, "aluminum_ingot");
        offerSmelting (exporter, RAW_ALUMINUM_BLOCK_SMELTABLES, RecipeCategory.MISC, ModBlocks.ALUMINUM_BLOCK,
                0.5f, 35000, "aluminum_block");
        offerBlasting (exporter, RAW_ALUMINUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINUM_INGOT,
                0.6f, 25000, "aluminum_ingot");
        offerBlasting (exporter, RAW_ALUMINUM_BLOCK_SMELTABLES, RecipeCategory.MISC, ModBlocks.ALUMINUM_BLOCK,
                0.6f, 25000, "aluminum_block");



        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DOMINITE, 3)
                .criterion(hasItem(ModBlocks.DOMINITE_BLOCK), conditionsFromItem (ModBlocks.DOMINITE_BLOCK))
                .offerTo (exporter, new Identifier (getRecipeName (ModItems.DOMINITE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.DOMINITE_BLOCK, 1)
                .pattern("ddd")
                .pattern("ddd")
                .pattern("ddd")
                .input ('d', ModItems.DOMINITE)
                .criterion (hasItem (ModItems.DOMINITE), conditionsFromItem (ModItems.DOMINITE))
                .offerTo (exporter, new Identifier (getRecipeName (ModBlocks.DOMINITE_BLOCK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.DOMINITE_BLOCK, 1)
                .pattern(" r ")
                .pattern("r r")
                .pattern(" r ")
                .input ('r', ModItems.REFINED_DOMINITE)
                .criterion (hasItem (ModItems.REFINED_DOMINITE), conditionsFromItem (ModItems.REFINED_DOMINITE))
                .offerTo (exporter, new Identifier (getRecipeName (ModBlocks.DOMINITE_ORE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_II_ALUMINATE, 1)
                .pattern("aaa")
                .pattern("iii")
                .pattern("aaa")
                .input ('a', ModItems.ALUMINUM_INGOT)
                .input ('i', Items.IRON_INGOT)
                .criterion (hasItem (ModItems.ALUMINUM_INGOT), conditionsFromItem (ModItems.ALUMINUM_INGOT))
                .criterion (hasItem (Items.IRON_INGOT), conditionsFromItem (Items.IRON_INGOT))
                .offerTo (exporter, new Identifier (getRecipeName (ModBlocks.IRON_II_ALUMINATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALUMINUM_BLOCK, 1)
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .input ('a', ModItems.ALUMINUM_INGOT)
                .criterion (hasItem (ModItems.ALUMINUM_INGOT), conditionsFromItem (ModItems.ALUMINUM_INGOT))
                .offerTo (exporter, new Identifier (getRecipeName (ModBlocks.ALUMINUM_BLOCK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_ALUMINUM_BLOCK, 1)
                .pattern(" r ")
                .pattern("r r")
                .pattern(" r ")
                .input ('r', ModItems.RAW_ALUMINUM)
                .criterion (hasItem (ModItems.RAW_ALUMINUM), conditionsFromItem (ModItems.RAW_ALUMINUM))
                .offerTo (exporter, new Identifier (getRecipeName (ModBlocks.RAW_ALUMINUM_BLOCK)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.YELLOW_MERANTI_PLANKS, 4)
                .criterion (hasItem(ModBlocks.YELLOW_MERANTI_LOG), conditionsFromItem (ModBlocks.YELLOW_MERANTI_LOG))
                .criterion (hasItem(ModBlocks.YELLOW_MERANTI_WOOD), conditionsFromItem (ModBlocks.YELLOW_MERANTI_WOOD))
                .criterion (hasItem(ModBlocks.STRIPPED_YELLOW_MERANTI_LOG), conditionsFromItem (ModBlocks.STRIPPED_YELLOW_MERANTI_LOG))
                .criterion (hasItem(ModBlocks.STRIPPED_YELLOW_MERANTI_WOOD), conditionsFromItem (ModBlocks.STRIPPED_YELLOW_MERANTI_WOOD))
                .offerTo (exporter, new Identifier (getRecipeName(ModBlocks.YELLOW_MERANTI_PLANKS)));

        ShapelessRecipeJsonBuilder.create (RecipeCategory.MISC, ModItems.BRACHYTUS_STEEL, 2)
                .criterion (hasItem(ModItems.BRACHYTUS_SHELL), conditionsFromItem (ModItems.BRACHYTUS_SHELL))
                .criterion (hasItem (ModItems.TRIDYMITE_DUST), conditionsFromItem (ModItems.TRIDYMITE_DUST))
                .criterion (hasItem(ModItems.DOMINITE), conditionsFromItem (ModItems.DOMINITE))
                .offerTo (exporter, new Identifier (getRecipeName (ModItems.BRACHYTUS_STEEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BRACHYTUS_STEEL_HELMET, 1)
                .pattern("rrr")
                .pattern("r r")
                .pattern("   ")
                .input ('r', ModItems.BRACHYTUS_STEEL)
                .criterion (hasItem (ModItems.BRACHYTUS_STEEL), conditionsFromItem (ModItems.BRACHYTUS_STEEL))
                .offerTo (exporter, new Identifier (getRecipeName (ModItems.BRACHYTUS_STEEL_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BRACHYTUS_STEEL_LEGGINGS, 1)
                .pattern("rrr")
                .pattern("r r")
                .pattern("r r")
                .input ('r', ModItems.BRACHYTUS_STEEL)
                .criterion (hasItem (ModItems.BRACHYTUS_STEEL), conditionsFromItem (ModItems.BRACHYTUS_STEEL))
                .offerTo (exporter, new Identifier (getRecipeName (ModItems.BRACHYTUS_STEEL_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BRACHYTUS_STEEL_CHESTPLATE, 1)
                .pattern("r r")
                .pattern("rrr")
                .pattern("rrr")
                .input ('r', ModItems.BRACHYTUS_STEEL)
                .criterion (hasItem (ModItems.BRACHYTUS_STEEL), conditionsFromItem (ModItems.BRACHYTUS_STEEL))
                .offerTo (exporter, new Identifier (getRecipeName (ModItems.BRACHYTUS_STEEL_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BRACHYTUS_STEEL_BOOTS, 1)
                .pattern("   ")
                .pattern("r r")
                .pattern("r r")
                .input ('r', ModItems.BRACHYTUS_STEEL)
                .criterion (hasItem (ModItems.BRACHYTUS_STEEL), conditionsFromItem (ModItems.BRACHYTUS_STEEL))
                .offerTo (exporter, new Identifier (getRecipeName (ModItems.BRACHYTUS_STEEL_BOOTS)));


        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.ALUMINATE_HELMET, 1)
                .pattern("iai")
                .pattern("a a")
                .pattern("   ")
                .input ('a', ModItems.BRACHYTUS_STEEL)
                .input ('i', Items.IRON_INGOT)
                .criterion (hasItem (ModItems.ALUMINUM_INGOT), conditionsFromItem (ModItems.ALUMINUM_INGOT))
                .offerTo (exporter, new Identifier (getRecipeName (ModItems.ALUMINATE_HELMET)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.ALUMINATE_LEGGINGS, 1)
                .pattern("iai")
                .pattern("a a")
                .pattern("i i")
                .input ('a', ModItems.BRACHYTUS_STEEL)
                .input ('i', Items.IRON_INGOT)
                .criterion (hasItem (ModItems.ALUMINUM_INGOT), conditionsFromItem (ModItems.ALUMINUM_INGOT))
                .offerTo (exporter, new Identifier (getRecipeName (ModItems.ALUMINATE_LEGGINGS)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.ALUMINATE_CHESTPLATE, 1)
                .pattern("i i")
                .pattern("aia")
                .pattern("iai")
                .input ('a', ModItems.BRACHYTUS_STEEL)
                .input ('i', Items.IRON_INGOT)
                .criterion (hasItem (ModItems.ALUMINUM_INGOT), conditionsFromItem (ModItems.ALUMINUM_INGOT))
                .offerTo (exporter, new Identifier (getRecipeName (ModItems.ALUMINATE_CHESTPLATE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.ALUMINATE_BOOTS, 1)
                .pattern("   ")
                .pattern("a a")
                .pattern("i i")
                .input ('a', ModItems.BRACHYTUS_STEEL)
                .input ('i', Items.IRON_INGOT)
                .criterion (hasItem (ModItems.ALUMINUM_INGOT), conditionsFromItem (ModItems.ALUMINUM_INGOT))
                .offerTo (exporter, new Identifier (getRecipeName (ModItems.ALUMINATE_BOOTS)));




        }

}
