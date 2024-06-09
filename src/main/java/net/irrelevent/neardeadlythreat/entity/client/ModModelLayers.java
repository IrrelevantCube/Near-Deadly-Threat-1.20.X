package net.irrelevent.neardeadlythreat.entity.client;

import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {

    public static final EntityModelLayer BRACHYTUS =
            new EntityModelLayer (new Identifier (NearDeadlyThreat.MOD_ID, "brachytus"), "main");
    public static final EntityModelLayer BRACHYMITE =
            new EntityModelLayer (new Identifier (NearDeadlyThreat.MOD_ID, "brachymite"), "main");
}
