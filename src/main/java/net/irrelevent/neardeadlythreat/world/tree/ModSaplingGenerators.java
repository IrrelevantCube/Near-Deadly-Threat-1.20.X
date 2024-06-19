package net.irrelevent.neardeadlythreat.world.tree;


import net.irrelevent.neardeadlythreat.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator FRACTURED_YELLOW_MERANTI =
            new SaplingGenerator ("fractured_yellow_meranti", 0f, Optional.empty (),
                    Optional.empty (),
                    Optional.of (ModConfiguredFeatures.FRACTURED_YELLOW_MERANTI_KEY),
                    Optional.empty (),
                    Optional.empty (),
                    Optional.empty ());
    public static final SaplingGenerator YELLOW_MERANTI =
            new SaplingGenerator ("yellow_meranti", 0f, Optional.empty (),
                    Optional.empty (),
                    Optional.of (ModConfiguredFeatures.YELLOW_MERANTI_KEY),
                    Optional.empty (),
                    Optional.empty (),
                    Optional.empty ());

}




