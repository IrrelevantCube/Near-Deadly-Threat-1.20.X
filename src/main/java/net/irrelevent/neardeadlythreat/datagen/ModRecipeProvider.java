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
        }

}
