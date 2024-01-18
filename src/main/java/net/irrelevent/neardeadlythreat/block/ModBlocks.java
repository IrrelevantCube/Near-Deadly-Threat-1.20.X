package net.irrelevent.neardeadlythreat.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block DOMINITE_ORE = registerBlock ("dominite_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).instrument(Instrument.CHIME).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block DOMINITE_BLOCK = registerBlock ("dominite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK).instrument(Instrument.CHIME).sounds (BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block CHRODOMACH_BLOCK = registerBlock ("chrodomach_block",
            new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK).instrument(Instrument.CHIME).luminance (12).sounds (BlockSoundGroup.ANVIL)));
    private static Block registerBlock(String name, Block block) {
        registerBlockItem (name, block);
        return Registry.register (Registries.BLOCK, new Identifier (NearDeadlyThreat.MOD_ID, name), block);
    }
    private static Item registerBlockItem (String name, Block block) {
        return Registry.register (Registries.ITEM, new Identifier(NearDeadlyThreat.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks() {
        NearDeadlyThreat.LOGGER.info("Registering ModBlocks For " + NearDeadlyThreat.MOD_ID);
    }
}
