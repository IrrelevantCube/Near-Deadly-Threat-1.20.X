package net.irrelevent.neardeadlythreat.world.tree;

import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.mixin.TrunkPlacerTypeInvoker;
import net.irrelevent.neardeadlythreat.world.tree.custom.FracturedYellowMerantiTrunkPlacer;
import net.irrelevent.neardeadlythreat.world.tree.custom.YellowMerantiTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class ModTrunkPlacerTypes {
    public static final TrunkPlacerType<?> YELLOW_MERANTI_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("yellow_meranti_trunk_placer", YellowMerantiTrunkPlacer.CODEC);
    public static final TrunkPlacerType<?> FRACTURED_YELLOW_MERANTI_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("fractured_yellow_meranti_trunk_placer", FracturedYellowMerantiTrunkPlacer.CODEC);

    public static void register() {
        NearDeadlyThreat.LOGGER.info("Registering Trunk Placers for " + NearDeadlyThreat.MOD_ID);
    }

}
