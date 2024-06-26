package net.irrelevent.neardeadlythreat.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ColorCode;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block DOMINITE_ORE = registerBlock ("dominite_ore",
            new ExperienceDroppingBlock (UniformIntProvider.create (5, 8), FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS).strength (3.5f, 4).instrument(Instrument.CHIME).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block ALUMINUM_ORE = registerBlock ("aluminum_ore",
            new ExperienceDroppingBlock (UniformIntProvider.create (5, 8), FabricBlockSettings.copyOf(Blocks.IRON_ORE).strength (1.5f, 2).instrument(Instrument.SNARE).sounds(BlockSoundGroup.STONE)));
    public static final Block RAW_ALUMINUM_BLOCK = registerBlock ("raw_aluminum_block",
            new Block (FabricBlockSettings.copyOf(Blocks.RAW_IRON_BLOCK).strength (1.5f, 5).instrument(Instrument.SNARE).sounds(BlockSoundGroup.COPPER)));
    public static final Block ALUMINUM_BLOCK = registerBlock ("aluminum_block",
            new Block (FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength (1.5f, 5).instrument(Instrument.SNARE).sounds(BlockSoundGroup.COPPER)));
    public static final Block IRON_II_ALUMINATE = registerBlock ("iron_ii_aluminate",
            new Block (FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength (5.5f, 20).instrument(Instrument.HAT).sounds(BlockSoundGroup.LANTERN)));
    public static final Block AMMONIUM_NITRATE = registerBlock ("ammonium_nitrate",
            new AmmoniumNitrateBlock (FabricBlockSettings.create ().mapColor(MapColor.YELLOW).breakInstantly ().sounds (BlockSoundGroup.GRASS).burnable ().solidBlock (Blocks::never)));
    public static final Block CHRODOMACH_ORE = registerBlock ("chrodomach_ore",
            new ExperienceDroppingBlock (UniformIntProvider.create (8, 12), FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS).strength (5.5f, 5).instrument(Instrument.IRON_XYLOPHONE).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block DOMINITE_BLOCK = registerBlock ("dominite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK).instrument(Instrument.CHIME).sounds (BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block YELLOW_MERANTI_PLANKS = registerBlock ("yellow_meranti_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));
    public static final Block YELLOW_MERANTI_LEAVES = registerBlock ("yellow_meranti_leaves",
            new LeavesBlock (FabricBlockSettings.copyOf(Blocks.JUNGLE_LEAVES).strength(1f).nonOpaque ()));
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

    public static final Block YELLOW_MERANTI_STAIRS = registerBlock ("yellow_meranti_stairs",
            new StairsBlock(ModBlocks.YELLOW_MERANTI_PLANKS.getDefaultState (), (FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ())));
    public static final Block YELLOW_MERANTI_SLAB = registerBlock ("yellow_meranti_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));

    public static final Block YELLOW_MERANTI_BUTTON = registerBlock ("yellow_meranti_button",
            new ButtonBlock(BlockSetType.JUNGLE, 20, FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));
    public static final ColoredFallingBlock TRIDYMITE = (ColoredFallingBlock) registerBlock ("tridymite",
            new ColoredFallingBlock (new ColorCode (14406560), FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.SNARE).strength(0.5f).sounds(BlockSoundGroup.SAND)));
    public static final Block YELLOW_MERANTI_PRESSURE_PLATE = registerBlock ("yellow_meranti_pressure_plate",
            new PressurePlateBlock (BlockSetType.JUNGLE, FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));

    public static final Block YELLOW_MERANTI_FENCE = registerBlock ("yellow_meranti_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));
    public static final Block YELLOW_MERANTI_FENCE_GATE = registerBlock ("yellow_meranti_fence_gate",
            new FenceGateBlock(WoodType.JUNGLE, FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));

    public static final Block YELLOW_MERANTI_DOOR = registerBlock ("yellow_meranti_door",
            new DoorBlock(BlockSetType.JUNGLE, FabricBlockSettings.copyOf(Blocks.JUNGLE_DOOR).strength(7f).pistonBehavior (PistonBehavior.BLOCK).sounds (BlockSoundGroup.WOOD).burnable ()));
    public static final Block YELLOW_MERANTI_SAPLING = registerBlock ("yellow_meranti_sapling",
            new SaplingBlock(ModSaplingGenerators.YELLOW_MERANTI, FabricBlockSettings.copyOf (Blocks.JUNGLE_SAPLING)));
    public static final Block FRACTURED_YELLOW_MERANTI_SAPLING = registerBlock ("fractured_yellow_meranti_sapling",
            new SaplingBlock(ModSaplingGenerators.FRACTURED_YELLOW_MERANTI, FabricBlockSettings.copyOf (Blocks.JUNGLE_SAPLING)));

    public static final Block YELLOW_MERANTI_TRAPDOOR = registerBlock ("yellow_meranti_trapdoor",
            new Block(FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).strength(7f).sounds (BlockSoundGroup.WOOD).nonOpaque().burnable ()));
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
