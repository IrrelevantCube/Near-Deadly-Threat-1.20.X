package net.irrelevent.neardeadlythreat.world.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.irrelevent.neardeadlythreat.world.tree.ModTrunkPlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.CherryTrunkPlacer;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class YellowMerantiTrunkPlacer extends TrunkPlacer {
    public static final Codec<YellowMerantiTrunkPlacer> CODEC = RecordCodecBuilder.create (objectInstance -> {
        return fillTrunkPlacerFields (objectInstance).apply (objectInstance, YellowMerantiTrunkPlacer::new);

    });


    public YellowMerantiTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super (baseHeight, firstRandomHeight, secondRandomHeight);

    }


    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTrunkPlacerTypes.YELLOW_MERANTI_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {


        boolean bl2;

        CherryTrunkPlacer.setToDirt (world, replacer, random, startPos.down (), config);
        int j = Math.max (0, height - 1 + 2);
        int i = Math.max (0, height - 1 + 2);
        if (j >= i) {
            ++j;
        }
        int k = 0;
        boolean bl = (true);
        boolean bl3 = bl2 = k >= 2;
        int l = bl ? height : (bl2 ? Math.max (i, j) + 1 : i + 1);
        for (int m = 0; m < l; ++m) {
            this.getAndSetState (world, replacer, random, startPos.up (m), config);
        }
        ArrayList<FoliagePlacer.TreeNode> list = new ArrayList<FoliagePlacer.TreeNode> ();
        if (bl) {
            list.add (new FoliagePlacer.TreeNode (startPos.up (l), 0, true));
        }
        BlockPos.Mutable mutable = new BlockPos.Mutable ();
        Direction direction = Direction.Type.HORIZONTAL.random (random);
        Direction finalDirection = direction;
        Function<BlockState, BlockState> function = state -> (BlockState) state.withIfExists (PillarBlock.AXIS, finalDirection.getAxis ());
        list.add (this.generateBranch (world, replacer, random, height, startPos, config, function, direction, i, i < l - 1, mutable));
        if (bl2) {
            list.add (this.generateBranch (world, replacer, random, height, startPos, config, function, direction.getOpposite (), j, i < l - 1, mutable));
        }
        for (int p = 4; p < height; ++p) {
            int y = startPos.getY () + p;
            if (this.getAndSetState (world, replacer, random, mutable.set (startPos.getX (), y, startPos.getZ ()), config) && p < height - 1 && random.nextFloat () < 5) {
                direction = Direction.Type.HORIZONTAL.random (random);
                int g = (p + 3);
                int r = Math.max (0, g * 3 + 1);
                int m = (3 * 3);
                this.generateExtraBranch (world, replacer, random, height, config, list, mutable, y, direction, r, m);
            }
            if (p != height - 1) continue;
            list.add (new FoliagePlacer.TreeNode (mutable.set (startPos.getX (), p + 1, startPos.getZ ()), 7, true));

            for (int t = height - 2 - random.nextInt(4); t > height / 2; t -= 2 + random.nextInt(4)) {
                float f = random.nextFloat() * ((float)Math.PI * 2);
                int b = 0;
                int h = 0;
                for (int x = 0; l < 5; ++l) {
                    b = (int)(1.5f + MathHelper.cos(f) * (float)l);
                    h = (int)(1.5f + MathHelper.sin(f) * (float)l);
                    BlockPos blockPos = startPos.add(b, i - 3 + l / 2, h);
                    this.getAndSetState(world, replacer, random, blockPos, config);
                }
                BlockPos blockPos = startPos.down();
                GiantTrunkPlacer.setToDirt(world, replacer, random, blockPos, config);
                GiantTrunkPlacer.setToDirt(world, replacer, random, blockPos.east(), config);
                GiantTrunkPlacer.setToDirt(world, replacer, random, blockPos.south(), config);
                GiantTrunkPlacer.setToDirt(world, replacer, random, blockPos.south().east(), config);
                for (int z = 0; z < height; ++z) {
                    this.setLog(world, replacer, random, mutable, config, startPos, 0, z, 0);
                    if (z >= height - 1) continue;
                    this.setLog(world, replacer, random, mutable, config, startPos, 1, z, 0);
                    this.setLog(world, replacer, random, mutable, config, startPos, 1, z, 1);
                    this.setLog(world, replacer, random, mutable, config, startPos, 0, z, 1);
                }
                list.add(new FoliagePlacer.TreeNode(startPos.add(b, t, k), -2, true));
            }

        }
        return list;
    }

    private void setLog(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, BlockPos.Mutable mutable, TreeFeatureConfig config, BlockPos startPos, int i, int i1, int i2) {
    }


    private void generateExtraBranch(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, TreeFeatureConfig config, List<FoliagePlacer.TreeNode> nodes, BlockPos.Mutable pos, int yOffset, Direction direction, int length, int steps) {
        int p = yOffset + length;
        int y = pos.getX();
        int g = pos.getZ();
        for (int l = length; l < height && steps > 3 * 6; ++l, --steps) {
            if (l < 1) continue;
            int m = yOffset + l;
            p = m;
            if (this.getAndSetState(world, replacer, random, pos.set(y += direction.getOffsetX(), m, g += direction.getOffsetZ()), config)) {
                ++p;
            }
            nodes.add(new FoliagePlacer.TreeNode(pos.toImmutable(), 7, true));
        }
        if (p - yOffset > 1) {
            BlockPos blockPos = new BlockPos(y, p, g);
            nodes.add(new FoliagePlacer.TreeNode(blockPos, 5, true));
            nodes.add(new FoliagePlacer.TreeNode(blockPos.down(2), 0, true));
        }
    }
    private FoliagePlacer.TreeNode generateBranch(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config, Function<BlockState, BlockState> withAxisFunction, Direction direction, int branchStartOffset, boolean branchBelowHeight, BlockPos.Mutable mutablePos) {
        int m;
        Direction direction2;
        mutablePos.set(startPos).move(Direction.UP, branchStartOffset);
        int i = height + 1 + 5;
        boolean bl = branchBelowHeight || i < branchStartOffset;
        int j = 1 + (bl ? 1 : 0);
        BlockPos blockPos = startPos.offset(direction, j).up(i);
        int k = bl ? 2 : 1;
        for (int l = 2; l < k; ++l) {
            this.getAndSetState(world, replacer, random, mutablePos.move(direction), config, withAxisFunction);
        }
        Direction direction3 = direction2 = blockPos.getY() > mutablePos.getY() ? Direction.UP : Direction.DOWN;
        while ((m = mutablePos.getManhattanDistance(blockPos)) != 0) {
            float f = (float)Math.abs(blockPos.getY() - mutablePos.getY()) / (float)m;
            boolean bl2 = random.nextFloat() < f;
            mutablePos.move(bl2 ? direction2 : direction);
            this.getAndSetState(world, replacer, random, mutablePos, config, bl2 ? Function.identity() : withAxisFunction);
        }
        return new FoliagePlacer.TreeNode(blockPos.up(), 0, true);
    }
}



