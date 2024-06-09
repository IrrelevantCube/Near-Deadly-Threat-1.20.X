package net.irrelevent.neardeadlythreat.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super (output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder (BlockTags.PICKAXE_MINEABLE)
                .add (ModBlocks.DOMINITE_BLOCK)
                .add (ModBlocks.CHRODOMACH_BLOCK)
                .add (ModBlocks.DOMINITE_ORE)
                .add (ModBlocks.CHRODOMACH_ORE);
        getOrCreateTagBuilder (TagKey.of (RegistryKeys.BLOCK, new Identifier ("fabric", "needs_tool_level_4")))
                .add(ModBlocks.CHRODOMACH_BLOCK)
                .add(ModBlocks.DOMINITE_BLOCK)
                .add(ModBlocks.DOMINITE_ORE)
                .add(ModBlocks.CHRODOMACH_ORE);

        getOrCreateTagBuilder (BlockTags.LOGS_THAT_BURN)
                .add (ModBlocks.YELLOW_MERANTI_LOG)
                .add (ModBlocks.YELLOW_MERANTI_WOOD)
                .add (ModBlocks.STRIPPED_YELLOW_MERANTI_LOG)
                .add (ModBlocks.STRIPPED_YELLOW_MERANTI_WOOD);

        getOrCreateTagBuilder (BlockTags.FENCES)
                .add(ModBlocks.YELLOW_MERANTI_FENCE);

        getOrCreateTagBuilder (BlockTags.FENCE_GATES)
                .add(ModBlocks.YELLOW_MERANTI_FENCE_GATE);

        getOrCreateTagBuilder (BlockTags.SAND)
                .add (ModBlocks.TRIDYMITE);
    }
}
