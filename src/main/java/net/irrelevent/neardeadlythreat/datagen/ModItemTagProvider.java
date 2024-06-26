package net.irrelevent.neardeadlythreat.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.item.ModItems;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg){
        getOrCreateTagBuilder (ItemTags.TRIMMABLE_ARMOR)
                .add (ModItems.BRACHYTUS_STEEL_BOOTS, ModItems.BRACHYTUS_STEEL_CHESTPLATE, ModItems.BRACHYTUS_STEEL_HELMET, ModItems.BRACHYTUS_STEEL_LEGGINGS);
        getOrCreateTagBuilder (ItemTags.TRIMMABLE_ARMOR)
                .add (ModItems.ALUMINATE_BOOTS, ModItems.ALUMINATE_CHESTPLATE, ModItems.ALUMINATE_HELMET, ModItems.ALUMINATE_LEGGINGS);

        getOrCreateTagBuilder (ItemTags.PLANKS)
                .add (ModBlocks.YELLOW_MERANTI_PLANKS.asItem ());

        getOrCreateTagBuilder (ItemTags.LOGS_THAT_BURN)
                .add (ModBlocks.YELLOW_MERANTI_LOG.asItem ());
        getOrCreateTagBuilder (ItemTags.LOGS_THAT_BURN)
                .add (ModBlocks.YELLOW_MERANTI_WOOD.asItem ());
        getOrCreateTagBuilder (ItemTags.LOGS_THAT_BURN)
                .add (ModBlocks.STRIPPED_YELLOW_MERANTI_LOG.asItem ());
        getOrCreateTagBuilder (ItemTags.LOGS_THAT_BURN)
                .add (ModBlocks.STRIPPED_YELLOW_MERANTI_WOOD.asItem ());

    }
}
