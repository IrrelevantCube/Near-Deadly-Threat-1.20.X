package net.irrelevent.neardeadlythreat.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block DOMINITE_ORE = registerBlock ("dominite_ore",
            new ExperienceDroppingBlock (FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS).strength (3.5f, 2000).instrument(Instrument.CHIME).sounds(BlockSoundGroup.AMETHYST_BLOCK), UniformIntProvider.create (5, 8)));
    public static final Block CHRODOMACH_ORE = registerBlock ("chrodomach_ore",
            new ExperienceDroppingBlock (FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS).strength (3.5f, 2100).instrument(Instrument.PLING).sounds(BlockSoundGroup.AMETHYST_BLOCK), UniformIntProvider.create (7, 9)));
    public static final Block DOMINITE_BLOCK = registerBlock ("dominite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK).instrument(Instrument.CHIME).sounds (BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block YELLOW_MERANTI_PLANKS = registerBlock ("yellow_meranti_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));
    public static final Block YELLOW_MERANTI_LEAVES = registerBlock ("yellow_meranti_leaves",
            new LeavesBlock (FabricBlockSettings.copyOf(Blocks.JUNGLE_LEAVES).strength(1f).pistonBehavior (PistonBehavior.DESTROY).sounds (BlockSoundGroup.CHERRY_LEAVES).burnable ().nonOpaque ()));
    public static final Block YELLOW_MERANTI_LOG = registerBlock ("yellow_meranti_log",
            new PillarBlock (FabricBlockSettings.copyOf(Blocks.JUNGLE_LOG).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));
    public static final Block YELLOW_MERANTI_WOOD = registerBlock ("yellow_meranti_wood",
            new PillarBlock (FabricBlockSettings.copyOf(Blocks.JUNGLE_WOOD).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));
    public static final Block STRIPPED_YELLOW_MERANTI_LOG = registerBlock ("stripped_yellow_meranti_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_JUNGLE_LOG).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));
    public static final Block STRIPPED_YELLOW_MERANTI_WOOD = registerBlock ("stripped_yellow_meranti_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_JUNGLE_WOOD).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));
    public static final Block CHRODOMACH_BLOCK = registerBlock ("chrodomach_block",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).instrument(Instrument.CHIME).luminance (12).sounds (BlockSoundGroup.ANVIL)));
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
