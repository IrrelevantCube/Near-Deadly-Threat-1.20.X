package net.irrelevent.neardeadlythreat.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.item.ModItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> DOMINITE_SMELTABLES = List.of(ModItems.DOMINITE);

    public ModRecipeProvider(FabricDataOutput output) {
        super (output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting (exporter, DOMINITE_SMELTABLES, RecipeCategory.MISC, ModItems.DOMINITE,
                0.7f, 500000, "dominite");
        offerBlasting (exporter, DOMINITE_SMELTABLES, RecipeCategory.MISC, ModItems.DOMINITE,
                0.75f, 450000, "dominite");


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




        }

}
